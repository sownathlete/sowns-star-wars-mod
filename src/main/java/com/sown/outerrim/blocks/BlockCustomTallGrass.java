package com.sown.outerrim.blocks;

import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomTallGrass extends BlockBush {

    private static final int TOP_BIT = 8;

    private final String baseName; // for registry/unlocal name
    private final int variantCount; // 3 for your setup
    private final Set<Block> extraValidGround = new HashSet<Block>();

    @SideOnly(Side.CLIENT)
    private IIcon[] bottomIcons;

    @SideOnly(Side.CLIENT)
    private IIcon[] topIcons;

    public BlockCustomTallGrass(String baseName, int variantCount) {
        super(Material.plants);
        this.baseName = baseName;
        this.variantCount = Math.max(1, Math.min(3, variantCount)); // you said 3 (0..2)

        setBlockName(baseName);
        setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabDecorations);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        setTickRandomly(true);

        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }

    public BlockCustomTallGrass addValidGround(Block block) {
        if (block != null) extraValidGround.add(block);
        return this;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        if (block == null) return false;

        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == Blocks.mycelium)
            return true;

        return extraValidGround.contains(block);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        // Need space for BOTH halves
        if (!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false;
        if (!world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z)) return false;

        Block below = world.getBlock(x, y - 1, z);
        return canPlaceBlockOn(below);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        if (world.isRemote) return;

        // Choose variant and place bottom+top
        int v = world.rand.nextInt(variantCount) & 3;

        world.setBlock(x, y, z, this, v, 2);
        world.setBlock(x, y + 1, z, this, v | TOP_BIT, 2);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (world.isRemote) return;

        int meta = world.getBlockMetadata(x, y, z);
        boolean isTop = (meta & TOP_BIT) != 0;

        if (isTop) {
            // Top must have bottom below
            if (world.getBlock(x, y - 1, z) != this) {
                world.setBlockToAir(x, y, z);
            }
        } else {
            // Bottom must have valid ground AND top above
            Block below = world.getBlock(x, y - 1, z);
            if (!canPlaceBlockOn(below) || world.getBlock(x, y + 1, z) != this) {
                // remove both safely
                world.func_147480_a(x, y, z, true);
                if (world.getBlock(x, y + 1, z) == this) {
                    world.setBlockToAir(x, y + 1, z);
                }
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        // When either half breaks, remove the other half.
        boolean isTop = (meta & TOP_BIT) != 0;
        if (isTop) {
            if (world.getBlock(x, y - 1, z) == this) {
                world.setBlockToAir(x, y - 1, z);
            }
        } else {
            if (world.getBlock(x, y + 1, z) == this) {
                world.setBlockToAir(x, y + 1, z);
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    // Biome grass tint
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return net.minecraft.world.ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return net.minecraft.world.ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        // bottom: tall_grass_bottom.png, tall_grass_bottom1.png, tall_grass_bottom2.png
        // top:    tall_grass_top.png,    tall_grass_top1.png,    tall_grass_top2.png
        bottomIcons = new IIcon[variantCount];
        topIcons = new IIcon[variantCount];

        for (int i = 0; i < variantCount; i++) {
            String suffix = (i == 0) ? "" : String.valueOf(i);
            bottomIcons[i] = reg.registerIcon("outerrim:tall_grass_bottom" + suffix);
            topIcons[i] = reg.registerIcon("outerrim:tall_grass_top" + suffix);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int v = meta & 3;
        boolean isTop = (meta & TOP_BIT) != 0;

        if (v < 0) v = 0;
        v %= variantCount;

        return isTop ? topIcons[v] : bottomIcons[v];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return 1; // cross
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
