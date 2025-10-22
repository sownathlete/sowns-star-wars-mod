package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCustomRedstoneTorch extends BlockRedstoneTorch {

    private IIcon iconOn;
    private IIcon iconOff;
    private String textureOn;
    private String textureOff;

    public BlockCustomRedstoneTorch(String name, String textureOn, String textureOff) {
        super(true);
        this.setBlockName(name);
        this.textureOn = textureOn;
        this.textureOff = textureOff;
        this.setCreativeTab(OuterRim.tabUtil);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.iconOn = reg.registerIcon("outerrim:" + textureOn);
        this.iconOff = reg.registerIcon("outerrim:" + textureOff);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return meta == 0 ? iconOn : iconOff;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return world.getBlockMetadata(x, y, z) == 0 ? 15 : 0;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (isPowered && meta == 0) {
            world.scheduleBlockUpdate(x, y, z, this, 2);
        } else if (!isPowered && meta == 1) {
            world.scheduleBlockUpdate(x, y, z, this, 2);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        int meta = world.getBlockMetadata(x, y, z);
        boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);

        if (isPowered && meta == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            world.markBlockForUpdate(x, y, z);
        } else if (!isPowered && meta == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            world.markBlockForUpdate(x, y, z);
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 0 ? 7 : 0;
    }
}
