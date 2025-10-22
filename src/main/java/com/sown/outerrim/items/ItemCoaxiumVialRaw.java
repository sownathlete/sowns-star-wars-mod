package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemCoaxiumVialRaw extends Item {

    private static final String TAG_VOL        = "Volatility";
    private static final String TAG_TICK       = "TickCounter";
    private static final String TAG_PAUSE      = "Paused";
    private static final String TAG_DIM        = "OriginDim";
    private static final String TAG_PROCESSING = "Processing";
    private static final String TAG_REFINE     = "RefineProgress";
    private static final String TAG_LAST_SYNC  = "LastSync";

    private static final int MAX_VOLATILITY     = 100;
    private static final int TICKS_PER_PERCENT  = 240;

    @SideOnly(Side.CLIENT) private IIcon iconLow;
    @SideOnly(Side.CLIENT) private IIcon iconMid;
    @SideOnly(Side.CLIENT) private IIcon iconHigh;

    public ItemCoaxiumVialRaw() {
        setUnlocalizedName("outerrim.vialCoaxiumRaw");
        setCreativeTab(OuterRim.tabMisc);
        setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    private void ensureNBT(ItemStack st) {
        if (!st.hasTagCompound()) {
            NBTTagCompound t = new NBTTagCompound();
            t.setInteger(TAG_VOL, 0);
            t.setInteger(TAG_TICK, 0);
            t.setBoolean(TAG_PAUSE, false);
            t.setInteger(TAG_DIM, 0);
            t.setLong(TAG_LAST_SYNC, 0L);
            st.setTagCompound(t);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        iconLow  = reg.registerIcon("outerrim:vial_coaxium_raw");
        iconMid  = reg.registerIcon("outerrim:vial_coaxium_mid");
        iconHigh = reg.registerIcon("outerrim:vial_coaxium_max");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack st, int pass) {
        ensureNBT(st);
        int v = st.getTagCompound().getInteger(TAG_VOL);
        return v < 25 ? iconLow : v < 75 ? iconMid : iconHigh;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack st) {
        return getIcon(st, 0);
    }

    @Override
    public void onCreated(ItemStack st, World w, EntityPlayer p) {
        initNBT(st, w);
    }

    @Override
    public void onUpdate(ItemStack st, World w, Entity holder, int slot, boolean isHeld) {
        if (w != null) initNBT(st, w);
        if (w == null || w.isRemote) return;

        NBTTagCompound n = st.getTagCompound();
        if (n == null) return;

        if (n.getBoolean(TAG_PAUSE)) {
            n.setBoolean(TAG_PAUSE, false);
            n.setInteger(TAG_TICK, 0);
            n.setLong(TAG_LAST_SYNC, w.getTotalWorldTime());
        }

        applyElapsedTicks(st, w, holder);
        if (!st.hasTagCompound()) return;

        if (holder instanceof EntityPlayer && n.getInteger(TAG_DIM) == 0)
            n.setInteger(TAG_DIM, holder.dimension);

        if (updateRefineryProgress(holder, n)) return;

        if (holder instanceof EntityPlayer && n.getInteger(TAG_DIM) != holder.dimension)
            explodeAndDelete(w, holder, st);
    }

    public static void tickInsideInventory(ItemStack st, World w, TileEntity te) {
        if (w.isRemote || !(st.getItem() instanceof ItemCoaxiumVialRaw)) return;

        NBTTagCompound n = st.getTagCompound();
        if (n == null) return;

        ItemCoaxiumVialRaw self = (ItemCoaxiumVialRaw) st.getItem();
        boolean inContainer = te instanceof TileEntityCoaxiumContainer;

        if (inContainer) {
            self.reverseOnePercentPerSecond(n);
            n.setLong(TAG_LAST_SYNC, w.getTotalWorldTime());
            return;
        }

        if (n.getBoolean(TAG_PAUSE)) {
            n.setBoolean(TAG_PAUSE, false);
            n.setInteger(TAG_TICK, 0);
            n.setLong(TAG_LAST_SYNC, w.getTotalWorldTime());
        }

        self.applyElapsedTicks(st, w, (Entity) null);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem ei) {
        onUpdate(ei.getEntityItem(), ei.worldObj, ei, 0, false);
        return false;
    }

    private void applyElapsedTicks(ItemStack st, World w, Entity ctx) {
        NBTTagCompound n = st.getTagCompound();
        long now  = w.getTotalWorldTime();
        long last = n.getLong(TAG_LAST_SYNC);

        if (last == 0L) { n.setLong(TAG_LAST_SYNC, now); return; }

        long delta = now - last;
        if (delta <= 0L) { n.setLong(TAG_LAST_SYNC, now); return; }

        int ticks = n.getInteger(TAG_TICK) + (int) delta;
        int add   = ticks / TICKS_PER_PERCENT;
        int rem   = ticks % TICKS_PER_PERCENT;

        int vol = n.getInteger(TAG_VOL) + add;
        if (vol >= MAX_VOLATILITY) {
            explodeAndDelete(w, ctx, st);
            return;
        }

        n.setInteger(TAG_VOL,  vol);
        n.setInteger(TAG_TICK, rem);
        n.setLong(TAG_LAST_SYNC, now);
    }

    private void reverseOnePercentPerSecond(NBTTagCompound n) {
        int t = n.getInteger(TAG_TICK) + 1;
        if (t >= TICKS_PER_PERCENT) {
            n.setInteger(TAG_TICK, 0);
            n.setInteger(TAG_VOL, Math.max(0, n.getInteger(TAG_VOL) - 1));
        } else {
            n.setInteger(TAG_TICK, t);
        }
    }

    private boolean updateRefineryProgress(Entity holder, NBTTagCompound n) {
        if (!n.getBoolean(TAG_PROCESSING)) return false;

        boolean inRefinery = false;
        if (holder instanceof EntityPlayer) {
            Container c = ((EntityPlayer) holder).openContainer;
            if (c != null && c.getClass().getSimpleName().contains("CoaxiumRefinery"))
                inRefinery = true;
        }

        if (!inRefinery) {
            n.setBoolean(TAG_PROCESSING, false);
            n.setInteger(TAG_REFINE, 0);
            return false;
        }

        n.setInteger(TAG_REFINE, n.getInteger(TAG_REFINE) + 1);
        return true;
    }

    private void explodeAndDelete(World w, Entity ctx, ItemStack st) {
        if (ctx != null) {
            w.createExplosion(null, ctx.posX, ctx.posY, ctx.posZ, 4F, true);

            if (ctx instanceof EntityItem) {
                ((EntityItem) ctx).setDead();
            } else if (ctx instanceof EntityPlayer) {
                ((EntityPlayer) ctx).inventory.consumeInventoryItem(this);
            }
        }

        st.setTagCompound(null);
    }

    private void initNBT(ItemStack st, World w) {
        if (st.hasTagCompound()) return;

        NBTTagCompound t = new NBTTagCompound();
        t.setInteger(TAG_VOL, 0);
        t.setInteger(TAG_TICK, 0);
        t.setBoolean(TAG_PAUSE, false);
        t.setInteger(TAG_DIM, w.provider.dimensionId);
        t.setLong(TAG_LAST_SYNC, w.getTotalWorldTime());
        st.setTagCompound(t);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack st, EntityPlayer p, List tip, boolean adv) {
        if (!st.hasTagCompound()) return;
        NBTTagCompound n = st.getTagCompound();

        if (n.getBoolean(TAG_PROCESSING)) {
            int pct = Math.min(100, n.getInteger(TAG_REFINE) * 100 / 12000);
            tip.add(EnumChatFormatting.AQUA + "Processing: " + pct + "%");
            return;
        }

        int v = n.getInteger(TAG_VOL);
        EnumChatFormatting c = v < 25 ? EnumChatFormatting.GREEN
                           : v < 75 ? EnumChatFormatting.YELLOW
                                    : EnumChatFormatting.RED;
        tip.add(c + "Volatility: " + v + "%");
    }
}
