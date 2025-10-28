package com.sown.outerrim.handlers;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.WeakHashMap;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.blocks.BlockMovingLight;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LightsaberLightHandler {

    private static final boolean DEBUG = true;
    private static final Logger LOG = LogManager.getLogger("OuterRim-LSLight");

    /** Lightsaber brightness (metadata 0..15). */
    private static final int SABER_LIGHT_META = 10;

    /** Minimum ticks between moving the light to a new block (reduces jitter). */
    private static final int MOVE_COOLDOWN_TICKS = 3;

    /** Keepalive must be < BlockMovingLight.TTL_TICKS. */
    private static final int KEEPALIVE_PERIOD_TICKS = 10;

    /** Extra safety: explicitly re-schedule the block expiry every refresh. */
    private static final int RESCHEDULE_TICKS = 10; // < KEEPALIVE and << TTL

    private static final int STICKY_RANGE_TAXICAB = 1;
    private static final double RAY_REACH = 2.8D;
    private static final int TUBE_STEPS = 3;

    private static final List<String> LEGENDS_MODIDS = Arrays.asList("legends", "starwarsmod", "starwars");
    private static final List<String> SABER_CLASS_NAMES = Arrays.asList(
            "com.tihyo.starwars.items.weapons.ItemLightsaberBase",
            "com.tihyo.legends.items.weapons.ItemLightsaberBase");

    private static volatile boolean reflectionInitTried = false;
    private static volatile boolean legendsPresent = false;
    private static volatile Class<?> saberBaseClass = null;
    private static volatile Method saberIsActive = null;

    private static void ensureReflectionInit() {
        if (reflectionInitTried) return;
        reflectionInitTried = true;

        for (String id : LEGENDS_MODIDS) {
            if (Loader.isModLoaded(id)) { legendsPresent = true; break; }
        }
        if (!legendsPresent) return;

        for (String cn : SABER_CLASS_NAMES) {
            try {
                Class<?> c = Class.forName(cn);
                Method m = c.getMethod("isActive", ItemStack.class);
                saberBaseClass = c;
                saberIsActive = m;
                break;
            } catch (Throwable ignored) { }
        }
        if (DEBUG) LOG.info("[init] legendsPresent={}, saberBaseClass={}, saberIsActive={}",
                legendsPresent, (saberBaseClass != null), (saberIsActive != null));
    }

    private static boolean isLightsaberOn(ItemStack stack) {
        if (stack == null) return false;
        ensureReflectionInit();
        if (!legendsPresent || saberBaseClass == null || saberIsActive == null) return false;

        try {
            if (!saberBaseClass.isInstance(stack.getItem())) {
                final String itCn = stack.getItem().getClass().getName().toLowerCase();
                if (!itCn.contains("lightsaber")) return false;
            }
            Object r = saberIsActive.invoke(null, stack);
            return (r instanceof Boolean) && ((Boolean) r);
        } catch (Throwable t) {
            if (DEBUG) LOG.warn("[isLightsaberOn] reflection failed: {}", t.toString());
            return false;
        }
    }

    private static final class LastLight { int x, y, z; int lastSetTick; boolean has; }
    private static final WeakHashMap<EntityLivingBase, LastLight> memo = new WeakHashMap<EntityLivingBase, LastLight>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        if (e.entity == null || !(e.entity instanceof EntityLivingBase)) return;
        final EntityLivingBase ent = (EntityLivingBase) e.entity;
        if (ent.worldObj == null || ent.worldObj.isRemote) return;

        final ItemStack held = ent.getHeldItem();
        if (!isLightsaberOn(held)) return;

        final int worldTime = (int) (ent.worldObj.getTotalWorldTime() & 0x7FFFFFFF);
        LastLight last = memo.get(ent);
        if (last == null) { last = new LastLight(); memo.put(ent, last); }

        final int headBlockY = MathHelper.floor_double(ent.posY + ent.getEyeHeight());
        CandidateList candidates = buildCandidates(ent, headBlockY);

        Target chosen = null;
        for (Target t : candidates.set) {
            if (t.y >= headBlockY) t = new Target(t.x, headBlockY - 1, t.z);
            if (isPlaceable(ent, t.x, t.y, t.z)) { chosen = t; break; }
        }
        if (chosen == null) { if (DEBUG) LOG.debug("[{}] no valid candidates this tick", ent.getCommandSenderName()); return; }

        if (last.has) {
            final Block lb = ent.worldObj.getBlock(last.x, last.y, last.z);
            final int lm = ent.worldObj.getBlockMetadata(last.x, last.y, last.z);
            int dist1 = manhattan(last.x, last.y, last.z, chosen.x, chosen.y, chosen.z);

            if (lb == BlockRegister.MOVING_LIGHT && lm == SABER_LIGHT_META && dist1 <= STICKY_RANGE_TAXICAB) {
                if ((worldTime - last.lastSetTick) >= KEEPALIVE_PERIOD_TICKS) {
                    ent.worldObj.setBlock(last.x, last.y, last.z, BlockRegister.MOVING_LIGHT, SABER_LIGHT_META, 2);
                    // Explicitly push the block’s timer forward
                    ent.worldObj.scheduleBlockUpdate(last.x, last.y, last.z, BlockRegister.MOVING_LIGHT, RESCHEDULE_TICKS);
                    last.lastSetTick = worldTime;
                    if (DEBUG) LOG.debug("[{}] keepalive at {},{},{} meta={}",
                            ent.getCommandSenderName(), last.x, last.y, last.z, SABER_LIGHT_META);
                }
                return;
            }
        }

        final Block cb = ent.worldObj.getBlock(chosen.x, chosen.y, chosen.z);
        final int cm  = ent.worldObj.getBlockMetadata(chosen.x, chosen.y, chosen.z);
        boolean targetIsOurLightWrongMeta = (cb == BlockRegister.MOVING_LIGHT && cm != SABER_LIGHT_META);

        if (last.has && !targetIsOurLightWrongMeta) {
            if ((worldTime - last.lastSetTick) < MOVE_COOLDOWN_TICKS) {
                final Block lb = ent.worldObj.getBlock(last.x, last.y, last.z);
                final int lm = ent.worldObj.getBlockMetadata(last.x, last.y, last.z);
                if (lb == BlockRegister.MOVING_LIGHT && lm == SABER_LIGHT_META
                        && (worldTime - last.lastSetTick) >= KEEPALIVE_PERIOD_TICKS) {
                    ent.worldObj.setBlock(last.x, last.y, last.z, BlockRegister.MOVING_LIGHT, SABER_LIGHT_META, 2);
                    ent.worldObj.scheduleBlockUpdate(last.x, last.y, last.z, BlockRegister.MOVING_LIGHT, RESCHEDULE_TICKS);
                    last.lastSetTick = worldTime;
                    if (DEBUG) LOG.debug("[{}] cooldown keepalive at {},{},{}",
                            ent.getCommandSenderName(), last.x, last.y, last.z);
                }
                return;
            }
        }

        // Final place/refresh + explicit reschedule
        ent.worldObj.setBlock(chosen.x, chosen.y, chosen.z, BlockRegister.MOVING_LIGHT, SABER_LIGHT_META, 2);
        ent.worldObj.scheduleBlockUpdate(chosen.x, chosen.y, chosen.z, BlockRegister.MOVING_LIGHT, RESCHEDULE_TICKS);

        if (DEBUG) LOG.debug("[{}] set light at {},{},{} meta={}",
                ent.getCommandSenderName(), chosen.x, chosen.y, chosen.z, SABER_LIGHT_META);
        last.x = chosen.x; last.y = chosen.y; last.z = chosen.z;
        last.has = true; last.lastSetTick = worldTime;
    }

    // ---------- Candidate building ----------

    private static final class Target { final int x,y,z; Target(int x,int y,int z){this.x=x;this.y=y;this.z=z;} }
    private static final class CandidateList { final LinkedHashSet<Target> set = new LinkedHashSet<Target>(32); }

    private static CandidateList buildCandidates(EntityLivingBase ent, int headBlockY) {
        CandidateList list = new CandidateList();
        Target face = faceCellViaRay(ent);
        if (face != null) list.set.add(face);
        if (face != null) addRing(list, face.x, face.y, face.z);
        addTube(list, ent);
        int eyeY = MathHelper.floor_double(ent.posY + ent.getEyeHeight());
        int px = MathHelper.floor_double(ent.posX);
        int pz = MathHelper.floor_double(ent.posZ);
        addRing(list, px, eyeY, pz);
        addRing2(list, px, eyeY, pz);
        final int feetY = MathHelper.floor_double(ent.boundingBox.minY + 0.001D);
        Target frontFeet = frontOfFeet(ent);
        if (frontFeet != null) list.set.add(frontFeet);
        list.set.add(new Target(px, feetY, pz));
        addRing(list, px, feetY, pz);
        addRing2(list, px, feetY, pz);

        CandidateList filtered = new CandidateList();
        for (Target t : list.set) {
            int y = t.y >= headBlockY ? headBlockY - 1 : t.y;
            filtered.set.add(new Target(t.x, y, t.z));
        }
        return filtered;
    }

    private static Target faceCellViaRay(EntityLivingBase ent) {
        try {
            MovingObjectPosition mop = ent.rayTrace(RAY_REACH, 1.0F);
            if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int bx = mop.blockX, by = mop.blockY, bz = mop.blockZ;
                int side = mop.sideHit;
                int ox = -Facing.offsetsXForSide[side];
                int oy = -Facing.offsetsYForSide[side];
                int oz = -Facing.offsetsZForSide[side];
                return new Target(bx + ox, by + oy, bz + oz);
            }
        } catch (Throwable ignored) { }
        Vec3 eye = ent.getPosition(1.0F).addVector(0, ent.getEyeHeight(), 0);
        Vec3 look = ent.getLookVec();
        double step = 0.9D;
        int x = MathHelper.floor_double(eye.xCoord + look.xCoord * step);
        int y = MathHelper.floor_double(eye.yCoord + look.yCoord * step);
        int z = MathHelper.floor_double(eye.zCoord + look.zCoord * step);
        return new Target(x, y, z);
    }

    private static void addRing(CandidateList list, int x, int y, int z) {
        list.set.add(new Target(x + 1, y, z));
        list.set.add(new Target(x - 1, y, z));
        list.set.add(new Target(x, y + 1, z));
        list.set.add(new Target(x, y - 1, z));
        list.set.add(new Target(x, y, z + 1));
        list.set.add(new Target(x, y, z - 1));
    }

    private static void addRing2(CandidateList list, int x, int y, int z) {
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.max(Math.abs(dx), Math.abs(dz)) != 2) continue;
                list.set.add(new Target(x + dx, y, z + dz));
            }
        }
    }

    private static void addTube(CandidateList list, EntityLivingBase ent) {
        Vec3 eye = ent.getPosition(1.0F).addVector(0, ent.getEyeHeight(), 0);
        Vec3 look = ent.getLookVec();
        double step = 0.8D;
        for (int i = 1; i <= TUBE_STEPS; i++) {
            int x = MathHelper.floor_double(eye.xCoord + look.xCoord * (step * i));
            int y = MathHelper.floor_double(eye.yCoord + look.yCoord * (step * i));
            int z = MathHelper.floor_double(eye.zCoord + look.zCoord * (step * i));
            list.set.add(new Target(x, y, z));
            addRing(list, x, y, z);
        }
    }

    private static Target frontOfFeet(EntityLivingBase ent) {
        double lookX = ent.getLookVec().xCoord;
        double lookZ = ent.getLookVec().zCoord;
        if (Math.abs(lookX) > Math.abs(lookZ)) lookZ = 0.0; else lookX = 0.0;
        int feetY = MathHelper.floor_double(ent.boundingBox.minY + 0.001D);
        int fx = MathHelper.floor_double(ent.posX + (lookX >= 0 ? 0.999 : -0.999));
        int fz = MathHelper.floor_double(ent.posZ + (lookZ >= 0 ? 0.999 : -0.999));
        return new Target(fx, feetY, fz);
    }

    private static int manhattan(int x1, int y1, int z1, int x2, int y2, int z2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
    }

    private static boolean isPlaceable(EntityLivingBase ent, int x, int y, int z) {
        final Block b = ent.worldObj.getBlock(x, y, z);
        if (b == BlockRegister.MOVING_LIGHT) return true;
        if (b == null) return false;
        if (b.getMaterial() != null && b.getMaterial().isLiquid()) return false;
        if (b.isAir(ent.worldObj, x, y, z)) return true;
        if (b.isReplaceable(ent.worldObj, x, y, z)) return true;
        return false;
    }
}
