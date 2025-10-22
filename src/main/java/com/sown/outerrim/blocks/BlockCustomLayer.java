package com.sown.outerrim.blocks;

import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomLayer extends BlockCustom {
    private boolean selfDropped = false;
    private Block blockDropped;
    private Item itemDropped;
    private boolean isMultiSided = false;
    private IIcon[] field_150158_M;
    private IIcon field_150159_N;
    private IIcon field_150160_O;
    private static final String[] field_150156_b = new String[]{"front"};
    private String textureName;

    public BlockCustomLayer(String name, Material material, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound) {
        super(name, material, hardness, harvestTool, harvestLevel, stepSound);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setCreativeTab(OuterRim.tabBlock);
    }

    public String getTextureName() {
        return this.textureName;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        if (isMultiSided) {
            this.field_150158_M = new IIcon[field_150156_b.length];
            for (int i = 0; i < this.field_150158_M.length; ++i) {
                this.field_150158_M[i] = par1IconRegister.registerIcon("outerrim:" + this.name + "_" + field_150156_b[i]);
            }
            this.field_150159_N = par1IconRegister.registerIcon("outerrim:" + this.name + "_top");
            this.field_150160_O = par1IconRegister.registerIcon("outerrim:" + this.name + "_bottom");
        } else {
            this.icons = new IIcon[1];
            this.icons[0] = par1IconRegister.registerIcon("outerrim:" + this.name);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (isMultiSided) {
            if (side != 1 && (side != 0 || meta != 1 && meta != 2)) {
                if (side == 0) {
                    return this.field_150160_O;
                }
                if (meta < 0 || meta >= this.field_150158_M.length) {
                    meta = 0;
                }
                return this.field_150158_M[meta];
            }
            return this.field_150159_N;
        } else {
            return this.icons[0];
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) & 15;
        float f = (float)(1 + l) / 16.0F;
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)((float)y + f), (double)z + this.maxZ);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) & 15;
        float f = (float)(1 + l) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        super.onNeighborBlockChange(world, x, y, z, neighborBlock);

        if (world.isAirBlock(x, y - 1, z)) {
            world.setBlockToAir(x, y, z);
        }

        if (world.getBlock(x + 1, y, z).getMaterial() == Material.water || 
            world.getBlock(x - 1, y, z).getMaterial() == Material.water ||
            world.getBlock(x, y + 1, z).getMaterial() == Material.water || 
            world.getBlock(x, y - 1, z).getMaterial() == Material.water || 
            world.getBlock(x, y, z + 1).getMaterial() == Material.water || 
            world.getBlock(x, y, z - 1).getMaterial() == Material.water) {
            world.setBlockToAir(x, y, z);
        }
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
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer || !(entity instanceof EntityPlayer)) {
            boolean isSneaking = entity instanceof EntityPlayer && ((EntityPlayer) entity).isSneaking();
            
            if (!isSneaking && this.getUnlocalizedName().toLowerCase().contains("salt")) {
                double entityPosX = entity.posX;
                double entityPosZ = entity.posZ;
                
                double blockCenterX = x + 0.5;
                double blockCenterZ = z + 0.5;
                double distanceToBlock = Math.sqrt((blockCenterX - entityPosX) * (blockCenterX - entityPosX)
                        + (blockCenterZ - entityPosZ) * (blockCenterZ - entityPosZ));
                
                if (distanceToBlock <= 0.5) {
                    world.setBlockToAir(x, y, z);
                }
            }
        }
    }
}
