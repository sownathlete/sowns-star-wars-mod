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

public class BlockCustomTallGrass extends BlockBush {

    private static final int TOP_BIT = 8;   // marks top half
    private static final int VAR_MASK = 7;  // store variant in low bits (0..7)

    private final String baseName;   // "tall_grass"
    private final int variantCount;  // 3 for your setup
    private final Set<Block> extraValidGround = new HashSet<Block>();

    // Set from ClientProxy after registering renderer
    public static int RENDER_ID = 1;

    @SideOnly(Side.CLIENT)
    private IIcon[] bottomIcons;

    @SideOnly(Side.CLIENT)
    private IIcon[] topIcons;

    public BlockCustomTallGrass(String baseName, int variantCount) {
        super(Material.plants);
        this.baseName = baseName;
        this.variantCount = Math.max(1, Math.min(8, variantCount)); // up to 8 in low bits

        setBlockName(baseName);
        setCreativeTab(CreativeTabs.tabDecorations);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        setTickRandomly(true);

        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }

    public int getVariantCount() {
        return variantCount;
    }

    public BlockCustomTallGrass addValidGround(Block block) {
        if (block != null) extraValidGround.add(block);
        return this;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        if (block == null) return false;
        if (block == this) return false;

        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand || block == Blocks.mycelium)
            return true;

        return extraValidGround.contains(block);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        // Must have room for both halves BEFORE placement
        if (!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false;
        if (!world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z)) return false;

        return canPlaceBlockOn(world.getBlock(x, y - 1, z));
    }

    // When the item places the bottom half, we spawn the top half here.
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        if (world.isRemote) return;

        // if something made y+1 not replaceable between checks, just bail and remove bottom cleanly
        if (!world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z)) {
            world.setBlockToAir(x, y, z);
            return;
        }

        int v = world.rand.nextInt(variantCount) & VAR_MASK;

        // set both halves WITHOUT neighbor spam
        world.setBlock(x, y, z, this, v, 2);
        world.setBlock(x, y + 1, z, this, v | TOP_BIT, 2);

        // now notify/update once both exist
        world.markBlockForUpdate(x, y, z);
        world.markBlockForUpdate(x, y + 1, z);
        world.notifyBlocksOfNeighborChange(x, y, z, this);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
    }

    // Safety: if bottom gets placed by setBlock (worldgen) and top is missing, create it.
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (world.isRemote) return;

        int meta = world.getBlockMetadata(x, y, z);
        boolean isTop = (meta & TOP_BIT) != 0;

        if (!isTop) {
            // bottom must ensure top exists
            if (world.getBlock(x, y + 1, z) != this && world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z)) {
                int v = meta & VAR_MASK;
                if (v >= variantCount) v = world.rand.nextInt(variantCount) & VAR_MASK;
                world.setBlock(x, y + 1, z, this, v | TOP_BIT, 2);
            }
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        boolean isTop = (meta & TOP_BIT) != 0;

        if (isTop) {
            // top must have this block as bottom below (and bottom must not be marked top)
            if (world.getBlock(x, y - 1, z) != this) return false;
            return (world.getBlockMetadata(x, y - 1, z) & TOP_BIT) == 0;
        } else {
            // bottom must have valid ground + top above
            if (!canPlaceBlockOn(world.getBlock(x, y - 1, z))) return false;
            if (world.getBlock(x, y + 1, z) != this) return false;
            return (world.getBlockMetadata(x, y + 1, z) & TOP_BIT) != 0;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(world, x, y, z, neighbor);

        if (world.isRemote) return;

        if (!canBlockStay(world, x, y, z)) {
            int meta = world.getBlockMetadata(x, y, z);
            boolean isTop = (meta & TOP_BIT) != 0;

            // Drop only once from bottom
            if (isTop) {
                if (world.getBlock(x, y - 1, z) == this) {
                    world.func_147480_a(x, y - 1, z, true); // drop from bottom
                }
                world.setBlockToAir(x, y, z);
            } else {
                world.func_147480_a(x, y, z, true); // drop from bottom
                if (world.getBlock(x, y + 1, z) == this) world.setBlockToAir(x, y + 1, z);
            }
        }
    }

    // When broken, remove the other half (avoid dupe drops: only bottom drops)
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        boolean isTop = (meta & TOP_BIT) != 0;

        if (isTop) {
            if (world.getBlock(x, y - 1, z) == this) {
                // remove bottom without additional drop (top should not be dropping anyway)
                world.setBlockToAir(x, y - 1, z);
            }
        } else {
            if (world.getBlock(x, y + 1, z) == this) {
                world.setBlockToAir(x, y + 1, z);
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    // Tint like vanilla double_plant/tallgrass
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
        // tall_grass_bottom.png, tall_grass_bottom_1.png, tall_grass_bottom_2.png
        // tall_grass_top.png,    tall_grass_top_1.png,    tall_grass_top_2.png
        bottomIcons = new IIcon[variantCount];
        topIcons = new IIcon[variantCount];

        for (int i = 0; i < variantCount; i++) {
            String suffix = (i == 0) ? "" : "_" + i;
            bottomIcons[i] = reg.registerIcon("outerrim:" + baseName + "_bottom" + suffix);
            topIcons[i] = reg.registerIcon("outerrim:" + baseName + "_top" + suffix);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int v = meta & VAR_MASK;
        boolean isTop = (meta & TOP_BIT) != 0;

        if (v >= variantCount) v = 0;

        return isTop ? topIcons[v] : bottomIcons[v];
    }

    @Override
    public int getRenderType() {
        return RENDER_ID; // custom renderer offsets MODEL
    }

    @Override public boolean isOpaqueCube() { return false; }
    @Override public boolean renderAsNormalBlock() { return false; }
}
