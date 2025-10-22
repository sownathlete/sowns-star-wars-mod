package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCustomTorch extends BlockTorch {

    private IIcon icon;
    private String textureName;

    public BlockCustomTorch(String name, String texture) {
        this.setBlockName(name);
        this.textureName = texture;
        this.setCreativeTab(OuterRim.tabUtil);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon("outerrim:" + textureName);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icon;
    }
}
