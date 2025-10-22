package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomGlassLayer extends BlockCustom {

    @SideOnly(Side.CLIENT)
    private IIcon[] connectedIcons;

    @SideOnly(Side.CLIENT)
    private int[] textureMap;

    public BlockCustomGlassLayer(String name, Material material, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound) {
        super(name, material, hardness, harvestTool, harvestLevel, stepSound);
        this.setBlockBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setCreativeTab(OuterRim.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        connectedIcons = new IIcon[34];
        textureMap = new int[256];

        for (int i = 0; i < 34; ++i) {
            connectedIcons[i] = iconRegister.registerIcon("outerrim:connected/" + this.name + "/" + this.name + "_" + i);
        }

        for (int meta = 0; meta < 16; ++meta) {
            for (int diagonalMeta = 0; diagonalMeta < 16; ++diagonalMeta) {
                int combinedMeta = (diagonalMeta << 4) | meta;
                int textureIndex = meta;

                if ((meta & 1) > 0 && (meta & 4) > 0 && (diagonalMeta & 1) == 0) textureIndex = 16;
                else if ((meta & 2) > 0 && (meta & 4) > 0 && (diagonalMeta & 2) == 0) textureIndex = 17;
                else if ((meta & 1) > 0 && (meta & 8) > 0 && (diagonalMeta & 4) == 0) textureIndex = 18;
                else if ((meta & 2) > 0 && (meta & 8) > 0 && (diagonalMeta & 8) == 0) textureIndex = 19;

                if ((meta & 2) > 0 && (meta & 4) > 0 && (diagonalMeta & 2) > 0) textureIndex = (meta & (2 | 4));
                if ((meta & 1) > 0 && (meta & 4) > 0 && (diagonalMeta & 1) > 0) textureIndex = (meta & (1 | 4));
                if ((meta & 2) > 0 && (meta & 8) > 0 && (diagonalMeta & 8) > 0) textureIndex = (meta & (2 | 8));
                if ((meta & 1) > 0 && (meta & 8) > 0 && (diagonalMeta & 4) > 0) textureIndex = (meta & (1 | 8));

                if (meta == 0b1111 && diagonalMeta == 0) textureIndex = 24;

                if ((combinedMeta & 0b11111110) == 0b11111110) textureIndex = 20;
                if ((combinedMeta & 0b11111101) == 0b11111101) textureIndex = 21;
                if ((combinedMeta & 0b11111011) == 0b11111011) textureIndex = 22;
                if ((combinedMeta & 0b11110111) == 0b11110111) textureIndex = 23;

                textureMap[combinedMeta] = textureIndex;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return connectedIcons[0];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = 0, diagonalMeta = 0;

        if (world.getBlock(x - 1, y, z) == this) meta |= 1;
        if (world.getBlock(x + 1, y, z) == this) meta |= 2;
        if (world.getBlock(x, y, z - 1) == this) meta |= 4;
        if (world.getBlock(x, y, z + 1) == this) meta |= 8;

        if (world.getBlock(x - 1, y, z - 1) == this) diagonalMeta |= 1;
        if (world.getBlock(x + 1, y, z - 1) == this) diagonalMeta |= 2;
        if (world.getBlock(x - 1, y, z + 1) == this) diagonalMeta |= 4;
        if (world.getBlock(x + 1, y, z + 1) == this) diagonalMeta |= 8;

        if (side == 1 || side == 0) {
            int idx = getTextureIndexForMeta(meta, diagonalMeta);

            if (meta == 0b1111) {
                if (diagonalMeta == 0b1110) idx = 20;
                if (diagonalMeta == 0b1101) idx = 21;
                if (diagonalMeta == 0b1011) idx = 22;
                if (diagonalMeta == 0b0111) idx = 23;
            }

            if (meta == 0b1111 && diagonalMeta == 0) idx = 25;

            if (meta == 0b1111) {
                if (diagonalMeta == 0b1001) idx = 26;
                if (diagonalMeta == 0b0101) idx = 27;
                if (diagonalMeta == 0b0010) idx = 28;
                if (diagonalMeta == 0b0001) idx = 29;
            }

            if (meta == 0b1111) {
                if (diagonalMeta == 0b1100) idx = 30;
                if (diagonalMeta == 0b0011) idx = 31;
                if (diagonalMeta == 0b1000) idx = 32;
                if (diagonalMeta == 0b0100) idx = 33;
            }

            return connectedIcons[idx];
        }

        return connectedIcons[0];
    }

    @SideOnly(Side.CLIENT)
    public int getTextureIndexForMeta(int meta, int diagonalMeta) {
        if ((meta & 1) > 0 && (meta & 4) > 0 && (diagonalMeta & 1) == 0) return 16;
        if ((meta & 2) > 0 && (meta & 4) > 0 && (diagonalMeta & 2) == 0) return 17;
        if ((meta & 1) > 0 && (meta & 8) > 0 && (diagonalMeta & 4) == 0) return 18;
        if ((meta & 2) > 0 && (meta & 8) > 0 && (diagonalMeta & 8) == 0) return 19;
        return meta % 16;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block adj = world.getBlock(x, y, z);
        return adj == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) & 15;
        float f = (l + 1) / 16.0F;
        return AxisAlignedBB.getBoundingBox(
            x + this.minX,
            y + 1.0F - f,
            z + this.minZ,
            x + this.maxX,
            y + 1.0F,
            z + this.maxZ
        );
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        world.markBlockForUpdate(x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) & 15;
        float f = (l + 1) / 16.0F;
        this.setBlockBounds(
            0.0F,
            1.0F - f,
            0.0F,
            1.0F,
            1.0F,
            1.0F
        );
    }
}
