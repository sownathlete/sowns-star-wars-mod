package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.tileentities.TileEntityCarbonite;
import com.sown.outerrim.tileentities.TileEntityHoloProjector;
import com.sown.outerrim.tileentities.TileEntityKaminoDoorLarge;
import com.sown.util.block.ORBlockContainer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockKaminoDoorLarge extends ORBlockContainer {

    public BlockKaminoDoorLarge(String name, Material material, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material);
        this.setCreativeTab(OuterRim.tabUtil);
        this.setHardness(hardness);
        this.setHarvestLevel(toolType, harvestLevel);
        this.setStepSound(stepSound);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityKaminoDoorLarge();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (entity == null)
            return;

        TileEntityKaminoDoorLarge tile = (TileEntityKaminoDoorLarge) world.getTileEntity(x, y, z);

        int direction = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        switch (direction) {
            case 0:
                tile.setFacing(0);
                break;
            case 1:
                tile.setFacing(1);
                break;
            case 2:
                tile.setFacing(2);
                break;
            case 3:
                tile.setFacing(3);
                break;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    	TileEntityKaminoDoorLarge tile = (TileEntityKaminoDoorLarge) world.getTileEntity(x, y, z);
        if (tile == null) return super.getCollisionBoundingBoxFromPool(world, x, y, z);

        int direction = tile.getFacing();

        switch (direction) {
            case 0:
                return AxisAlignedBB.getBoundingBox(x, y, z + 0.5, x + 1, y + 2, z + 1);
            case 1:
                return AxisAlignedBB.getBoundingBox(x, y, z, x + 0.5, y + 2, z + 1);
            case 2:
                return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 2, z + 0.5);
            case 3:
                return AxisAlignedBB.getBoundingBox(x + 0.5, y, z, x + 1, y + 2, z + 1);
            default:
                return super.getCollisionBoundingBoxFromPool(world, x, y, z);
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
}
