package com.sown.outerrim.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.MathHelper;

/**
 * Ephemeral invisible light source.
 * - Metadata (0..15) = emitted light level.
 * - Auto-removes itself after a short delay unless re-placed (refreshed) by a handler.
 * - Non-solid, replaceable, no drops, no render.
 */
public class BlockMovingLight extends Block {

    /** How many ticks the light stays if not refreshed by being set again. */
    public static final int TTL_TICKS = 60; // 3.0s @20 TPS so handler can comfortably keep it alive

    public BlockMovingLight() {
        super(Material.circuits);
        setBlockName("moving_light");
        setTickRandomly(true);
        setLightOpacity(0);
        setBlockUnbreakable();
        setResistance(6000000.0F);
        disableStats();
        setStepSound(soundTypeCloth);
        setCreativeTab(null);
        setBlockBounds(0, 0, 0, 0, 0, 0);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return MathHelper.clamp_int(meta, 0, 15);
    }

    @Override
    public int getLightValue() { return 0; }

    @Override
    public boolean renderAsNormalBlock() { return false; }

    @Override
    public boolean isOpaqueCube() { return false; }

    @Override
    public int getRenderType() { return -1; }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) { return null; }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) { return true; }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) { return true; }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) { return false; }

    @Override
    public int quantityDropped(Random rand) { return 0; }

    @Override
    public void registerBlockIcons(IIconRegister reg) { this.blockIcon = null; }

    // --- Self-expiry (auto-cleanup) ---
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        world.scheduleBlockUpdate(x, y, z, this, TTL_TICKS);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!world.isRemote) {
            world.scheduleBlockUpdate(x, y, z, this, TTL_TICKS);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            world.setBlockToAir(x, y, z);
        }
    }
}
