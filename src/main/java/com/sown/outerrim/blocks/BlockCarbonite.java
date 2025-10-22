package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.tileentities.TileEntityCarbonite;
import com.sown.util.block.ORBlockContainer;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCarbonite extends ORBlockContainer {
    MinecraftServer server = MinecraftServer.getServer();

    public BlockCarbonite(String name, Material material, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super(name, material);
        this.setCreativeTab(OuterRim.tabDeco);
        this.setHardness(hardness);
        this.setHarvestLevel(toolType, harvestLevel);
        this.setStepSound(stepSound);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCarbonite();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "." + stack.getDisplayName();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("outerrim:iconCarbonite");
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (entity == null)
            return;

        TileEntityCarbonite tile = (TileEntityCarbonite) world.getTileEntity(x, y, z);

        // Check if the block was placed by a player and set the frozen player's name
        if (itemStack != null && itemStack.hasDisplayName()) {
            tile.setFrozenPlayerName(itemStack.getDisplayName().replace("Frozen ", ""));
        }

        int direction = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        switch (direction) {
            case 0: // South
                tile.setFacing(0);
                break;
            case 1: // West
                tile.setFacing(1);
                break;
            case 2: // North
                tile.setFacing(2);
                break;
            case 3: // East
                tile.setFacing(3);
                break;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        TileEntityCarbonite tile = (TileEntityCarbonite) world.getTileEntity(x, y, z);
        if (tile == null) return super.getCollisionBoundingBoxFromPool(world, x, y, z);

        int direction = tile.getFacing();

        switch (direction) {
            case 0: // South
                return AxisAlignedBB.getBoundingBox(x, y, z + 0.5, x + 1, y + 2, z + 1);
            case 1: // West
                return AxisAlignedBB.getBoundingBox(x, y, z, x + 0.5, y + 2, z + 1);
            case 2: // North
                return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 2, z + 0.5);
            case 3: // East
                return AxisAlignedBB.getBoundingBox(x + 0.5, y, z, x + 1, y + 2, z + 1);
            default:
                return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
    }
}
