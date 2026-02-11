package com.sown.outerrim.blocks;

import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomGrassPlant extends BlockBush {

    private final String name;
    private final int variantCount;
    private final Set<Block> extraValidGround = new HashSet<Block>();

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockCustomGrassPlant(String name, int variantCount) {
        super(Material.plants);
        this.name = name;
        this.variantCount = Math.max(1, Math.min(15, variantCount)); // meta limit safety

        setBlockName(name);
        setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabDecorations);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        setTickRandomly(true);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public BlockCustomGrassPlant addValidGround(Block block) {
        if (block != null) extraValidGround.add(block);
        return this;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        if (block == null) return false;

        // Vanilla-ish ground
        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == Blocks.mycelium)
            return true;

        // Your custom allowed blocks
        return extraValidGround.contains(block);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        // Must be replaceable at target and must have valid ground below
        if (!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false;
        Block below = world.getBlock(x, y - 1, z);
        return canPlaceBlockOn(below);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        // When first placed, pick a random variant and store in metadata
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta < 0 || meta >= variantCount) {
                int v = world.rand.nextInt(variantCount);
                world.setBlockMetadataWithNotify(x, y, z, v, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!canBlockStay(world, x, y, z)) {
            world.func_147480_a(x, y, z, true); // drop + remove
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block below = world.getBlock(x, y - 1, z);
        return canPlaceBlockOn(below);
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
        // textures: short_grass.png, short_grass1.png ...
        icons = new IIcon[variantCount];
        for (int i = 0; i < variantCount; i++) {
            String suffix = (i == 0) ? "" : String.valueOf(i);
            icons[i] = reg.registerIcon("outerrim:" + name + suffix);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (icons == null || icons.length == 0) return null;
        int m = meta;
        if (m < 0) m = 0;
        m %= icons.length;
        return icons[m];
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
