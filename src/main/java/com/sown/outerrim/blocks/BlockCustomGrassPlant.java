package com.sown.outerrim.blocks;

import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomGrassPlant extends BlockBush {

    private final String name;
    private final int variantCount;
    private final Set<Block> extraValidGround = new HashSet<Block>();

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    // Set from ClientProxy after registering renderer
    public static int RENDER_ID = 1;

    public BlockCustomGrassPlant(String name, int variantCount) {
        super(Material.plants);
        this.name = name;
        this.variantCount = Math.max(1, Math.min(16, variantCount)); // meta 0..15

        setBlockName(name);
        setCreativeTab(CreativeTabs.tabDecorations);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        setTickRandomly(true);

        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public int getVariantCount() {
        return variantCount;
    }

    public BlockCustomGrassPlant addValidGround(Block block) {
        if (block != null) extraValidGround.add(block);
        return this;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        if (block == null) return false;
        if (block == this) return false; // prevents stacking on itself

        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == Blocks.mycelium)
            return true;

        return extraValidGround.contains(block);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        if (!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false;
        return canPlaceBlockOn(world.getBlock(x, y - 1, z));
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockOn(world.getBlock(x, y - 1, z));
    }

    // Random variant on player placement
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        if (world.isRemote) return;
        world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(variantCount), 2);
    }

    // Random variant if placed by worldgen/setBlock without onBlockPlacedBy
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (world.isRemote) return;
        int meta = world.getBlockMetadata(x, y, z);
        if (meta < 0 || meta >= variantCount) {
            world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(variantCount), 2);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(world, x, y, z, neighbor);
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    // Tint like vanilla tallgrass
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
        // short_grass.png, short_grass1.png, short_grass2.png...
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
        int m = meta % icons.length;
        if (m < 0) m = 0;
        return icons[m];
    }

    @Override
    public int getRenderType() {
        return RENDER_ID; // custom renderer does the MODEL offset
    }

    @Override public boolean isOpaqueCube() { return false; }
    @Override public boolean renderAsNormalBlock() { return false; }
}
