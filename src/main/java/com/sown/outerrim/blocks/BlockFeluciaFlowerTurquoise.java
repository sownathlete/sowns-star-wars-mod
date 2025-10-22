package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;
import com.sown.util.block.ORBlockContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockFeluciaFlowerTurquoise extends ORBlockContainer implements IPlantable {
    private static final float MIN = 0.28125F, MAX = 0.71875F, HEIGHT = 0.75F;
    private static final long POISON_COOLDOWN = 20L * 30;

    public BlockFeluciaFlowerTurquoise(String name, Material material, float hardness,
                                       String toolType, int harvestLevel,
                                       SoundType stepSound, boolean isMultiSided) {
        super(name, material);
        setCreativeTab(OuterRim.tabDeco);
        setHardness(hardness);
        setHarvestLevel(toolType, harvestLevel);
        setStepSound(stepSound);
        setLightLevel(0.0F);
        setBlockBounds(MIN, 0.0F, MIN, MAX, HEIGHT, MAX);
        setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityFeluciaFlowerTurquoise();
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        if (!super.canPlaceBlockAt(world, x, y, z)) return false;
        return world.getBlock(x, y - 1, z)
            .canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!canBlockStay(world, x, y, z)) {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z)
            .canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
        TileEntityFeluciaFlowerTurquoise te =
            (TileEntityFeluciaFlowerTurquoise)world.getTileEntity(x, y, z);
        int rot = MathHelper.floor_double(
            placer.rotationYaw * 4.0F / 360.0F + 0.5D
        ) & 3;
        te.setFacing(rot);
        te.scheduleNextParticles(world.getTotalWorldTime());
    }

    @Override public boolean isOpaqueCube()           { return false; }
    @Override public boolean renderAsNormalBlock()    { return false; }
    @Override public int getRenderType()              { return -1; }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(
            World world, int x, int y, int z) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(
            World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(
            x + MIN, y,
            z + MIN,
            x + MAX, y + HEIGHT, z + MAX
        );
    }

    @Override
    public void onEntityCollidedWithBlock(World world,
                                          int x, int y, int z,
                                          Entity entity) {
        if (!(entity instanceof EntityLivingBase)) return;

        TileEntityFeluciaFlowerTurquoise te =
            (TileEntityFeluciaFlowerTurquoise)world.getTileEntity(x, y, z);
        long now = world.getTotalWorldTime();

        if (now - te.getLastPoisonTime() < POISON_COOLDOWN) return;

        if (!world.isRemote) {
            int duration = 20 + world.rand.nextInt(20 * 15);
            ((EntityLivingBase)entity).addPotionEffect(
                new PotionEffect(Potion.poison.id, duration, 0)
            );
            te.setLastPoisonTime(now);
            te.markDirty();
        }

        if (world.isRemote) {
            te.setLastPoisonTime(now);
        }

        if (world.isRemote) {
            Random rand = world.rand;
            for (int i = 0; i < 4; i++) {
                double px = x + MIN + rand.nextDouble() * (MAX - MIN);
                double py = y + rand.nextDouble() * HEIGHT;
                double pz = z + MIN + rand.nextDouble() * (MAX - MIN);
                EntitySmokeFX fx = new EntitySmokeFX(
                    world, px, py, pz,
                    0, 0.02, 0
                );
                fx.multipleParticleScaleBy(1.2F);
                fx.setRBGColorF(0F, 1F, 0F);
                Minecraft.getMinecraft()
                         .effectRenderer
                         .addEffect(fx);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world,
                                  int x, int y, int z,
                                  Random rand) {
        TileEntityFeluciaFlowerTurquoise te =
            (TileEntityFeluciaFlowerTurquoise)world.getTileEntity(x, y, z);
        long now = world.getTotalWorldTime();
        if (now >= te.getNextParticleTime()) {
            for (int i = 0; i < 4; i++) {
                double px = x + MIN + rand.nextDouble() * (MAX - MIN);
                double py = y + rand.nextDouble() * HEIGHT;
                double pz = z + MIN + rand.nextDouble() * (MAX - MIN);
                EntitySmokeFX fx = new EntitySmokeFX(
                    world, px, py, pz,
                    0, 0.02, 0
                );
                fx.multipleParticleScaleBy(1.2F);
                fx.setRBGColorF(0F, 1F, 0F);
                Minecraft.getMinecraft()
                         .effectRenderer
                         .addEffect(fx);
            }
            te.scheduleNextParticles(now);
        }
    }

    @Override public EnumPlantType getPlantType(IBlockAccess w,int x,int y,int z) {
        return EnumPlantType.Plains;
    }
    @Override public net.minecraft.block.Block getPlant(IBlockAccess w,int x,int y,int z){
        return this;
    }
    @Override public int getPlantMetadata(IBlockAccess w,int x,int y,int z){
        return w.getBlockMetadata(x,y,z);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("outerrim:icon_felucia_flower_tall_turquoise");
    }
}
