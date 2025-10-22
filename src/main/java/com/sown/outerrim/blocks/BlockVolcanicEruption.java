package com.sown.outerrim.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVolcanicEruption extends BlockCustomSolid {
    
    public BlockVolcanicEruption(String name, Material material, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material, hardness, harvestTool, harvestLevel, stepSound, isMultiSided);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        for (int i = 0; i < 20; i++) {
            double d0 = x + 0.5D + ((random.nextDouble() - 0.5D) * 0.5D);
            double d1 = y + 1.1D;
            double d2 = z + 0.5D + ((random.nextDouble() - 0.5D) * 0.5D);
            double d3 = (random.nextDouble() - 0.5D) * 2.0D;
            double d4 = random.nextDouble();
            double d5 = (random.nextDouble() - 0.5D) * 2.0D;

            if (random.nextBoolean()) {
                EntitySmokeFX particle = new EntitySmokeFX(world, d0, d1, d2, d3, d4, d5);
                particle.multipleParticleScaleBy(1.5F);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
            } else {
                EntityLavaFX particle = new EntityLavaFX(world, d0, d1, d2);
                particle.motionX = d3;
                particle.motionY = d4;
                particle.motionZ = d5;
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
            }
        }
    }
    
    private ThreadLocal<BlockPos> lastQueriedPosition = ThreadLocal.withInitial(() -> new BlockPos(0, 0, 0));

    public static class BlockPos {
        public int x, y, z;

        public BlockPos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        double radius = 2.0D;
        List<Entity> entitiesWithinAABB = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

        for (Entity nearbyEntity : entitiesWithinAABB) {
            if (nearbyEntity instanceof EntityLivingBase && !nearbyEntity.isImmuneToFire()) {
                nearbyEntity.setFire(5);
            }
        }
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        lastQueriedPosition.get().x = x;
        lastQueriedPosition.get().y = y;
        lastQueriedPosition.get().z = z;
        return super.colorMultiplier(world, x, y, z);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null; 
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return Blocks.air.getIcon(side, 0);
    }
}