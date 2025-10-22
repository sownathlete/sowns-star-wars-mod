package com.sown.outerrim.blocks;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockCoaxiumContainer extends ORBlockContainer {
    private static final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);

    public BlockCoaxiumContainer(String name, Material material, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super("coaxium_container", Material.iron);
        this.setCreativeTab(OuterRim.tabUtil);
        this.setHardness(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setResistance(50F);
    }

    @Override public int getRenderType() { return -1; }
    @Override public boolean isOpaqueCube() { return false; }
    @Override public boolean renderAsNormalBlock() { return false; }

    @Override public TileEntity createNewTileEntity(World w, int m) { return new TileEntityCoaxiumContainer(); }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int s, float hx, float hy, float hz) {
        if (w.isRemote) return true;
        p.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_COAXIUM_CONTAINER, w, x, y, z);
        return true;
    }

    @Override
    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        if (w.isBlockIndirectlyGettingPowered(x, y, z)) {
            explodeInstant(w, x, y, z);
            w.setBlockToAir(x, y, z);
        }
    }

    private void explodeInstant(World w, int x, int y, int z) {
        if (!w.isRemote) {
            w.createExplosion(null, x + .5, y + .5, z + .5, 4f, true);
        } else {
            spawnExplosionParticles(w, x, y, z, w.rand);
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnExplosionParticles(World w, int x, int y, int z, Random r) {
        for (int i = 0; i < 40; i++) {
            double px = x + 0.5 + (r.nextDouble() - 0.5);
            double py = y + 1.1;
            double pz = z + 0.5 + (r.nextDouble() - 0.5);
            double mx = (r.nextDouble() - 0.5) * 4.0;
            double my = r.nextDouble();
            double mz = (r.nextDouble() - 0.5) * 4.0;
            if (r.nextBoolean()) {
                EntitySmokeFX fx = new EntitySmokeFX(w, px, py, pz, mx, my, mz);
                fx.multipleParticleScaleBy(1.5f);
                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            } else {
                EntityLavaFX fx = new EntityLavaFX(w, px, py, pz);
                fx.motionX = mx;
                fx.motionY = my;
                fx.motionZ = mz;
                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            }
        }
    }

    @Override
    public void breakBlock(World w, int x, int y, int z, Block b, int m) {
        if (!w.isRemote) {
            TileEntity te = w.getTileEntity(x, y, z);
            if (te instanceof IInventory) {
                IInventory inv = (IInventory) te;
                int v = 0;
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack st = inv.getStackInSlot(i);
                    if (st != null && st.getItem() instanceof ItemCoaxiumVialRaw) v += st.stackSize;
                }
                if (v > 0) {
                    float pwr = 2f + v * 1.5f;
                    w.removeTileEntity(x, y, z);
                    w.setBlockToAir(x, y, z);
                    w.createExplosion(null, x + .5, y + .5, z + .5, pwr, true);
                    return;
                }
            }
        }
        super.breakBlock(w, x, y, z, b, m);
    }

    @Override public void harvestBlock(World w, EntityPlayer p, int x, int y, int z, int m) {}
    @Override public boolean canDropFromExplosion(Explosion e) { return false; }

    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack s) {
        super.onBlockPlacedBy(w, x, y, z, e, s);
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileEntityCoaxiumContainer) {
            int r = MathHelper.floor_double(e.rotationYaw * 4f / 360f + .5d) & 3;
            ((TileEntityCoaxiumContainer) te).setFacing(r);
        }
    }

    @Override
    public java.util.ArrayList<ItemStack> getDrops(World w, int x, int y, int z, int m, int f) {
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof IInventory) {
            for (int i = 0; i < ((IInventory) te).getSizeInventory(); i++) {
                ItemStack st = ((IInventory) te).getStackInSlot(i);
                if (st != null && st.getItem() instanceof ItemCoaxiumVialRaw) return new java.util.ArrayList<>();
            }
        }
        return super.getDrops(w, x, y, z, m, f);
    }

    @SideOnly(Side.CLIENT)
    @Override public void registerBlockIcons(IIconRegister r) { blockIcon = r.registerIcon("outerrim:iconCoaxiumRack"); }
}