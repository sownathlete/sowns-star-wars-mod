package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustomLog extends BlockLog {
    private IIcon topIcon;
    private IIcon frontIcon;

    public BlockCustomLog(String name, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound) {
        super();
        setBlockName(name);
        setBlockTextureName("outerrim:" + name);
        setHardness(hardness);
        setHarvestLevel(harvestTool, harvestLevel);
        setStepSound(stepSound);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.topIcon = iconRegister.registerIcon("outerrim:" + getUnlocalizedName().substring(5) + "_top");
        this.frontIcon = iconRegister.registerIcon("outerrim:" + getUnlocalizedName().substring(5) + "_front");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int orientation = meta & 12;

        if (orientation == 0) {
            if (side == 0 || side == 1) {
                return this.topIcon;
            }
            return this.frontIcon;
        } else if (orientation == 4) {
            if (side == 4 || side == 5) {
                return this.topIcon;
            }
            return this.frontIcon;
        } else if (orientation == 8) {
            if (side == 2 || side == 3) {
                return this.topIcon;
            }
            return this.frontIcon;
        }

        return this.frontIcon;
    }

    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        return super.rotateBlock(world, x, y, z, axis);
    }
}
