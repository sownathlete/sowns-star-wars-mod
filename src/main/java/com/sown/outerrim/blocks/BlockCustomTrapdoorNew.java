package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCustomTrapdoorNew extends BlockTrapDoor {

    private IIcon icon;
    private String textureName;

    public BlockCustomTrapdoorNew(String name, String texture, float hardness, String toolType, int harvestLevel, SoundType stepSound) {
        super(Material.wood);

        this.textureName = texture;
        this.setBlockName(name);
        this.setHardness(hardness);
        this.setHarvestLevel(toolType, harvestLevel);
        this.setStepSound(stepSound);
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
