package com.sown.outerrim.blocks;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRack;
import com.sown.util.block.ORBlockContainer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockCoaxiumRack extends ORBlockContainer {
	private static final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
    public BlockCoaxiumRack(String name, Material material, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super("coaxiumRack", Material.iron);
        this.setCreativeTab(OuterRim.tabUtil);
        this.setHardness(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlockToAir(x, y, z);
        }
    }
    @SideOnly(Side.CLIENT)
    private void spawnExplosionParticles(World world, int x, int y, int z, Random random) {
        for (int i = 0; i < 40; i++) {  // Increased loop count
            double d0 = x + 0.5D + ((random.nextDouble() - 0.5D) * 1.0D); // Doubled the spread in x-direction
            double d1 = y + 1.1D;
            double d2 = z + 0.5D + ((random.nextDouble() - 0.5D) * 1.0D); // Doubled the spread in z-direction
            double d3 = (random.nextDouble() - 0.5D) * 4.0D; // Doubled the velocity in x-direction
            double d4 = random.nextDouble();
            double d5 = (random.nextDouble() - 0.5D) * 4.0D; // Doubled the velocity in z-direction

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
    // Override other methods as before

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCoaxiumRack();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("outerrim:iconCoaxiumRack");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityCoaxiumRack) {
            TileEntityCoaxiumRack tileEntityCoaxiumRack = (TileEntityCoaxiumRack) tileEntity;
            int rotation = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0f / 360.0f + 0.5) & 3;
            tileEntityCoaxiumRack.setFacing(rotation);
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
