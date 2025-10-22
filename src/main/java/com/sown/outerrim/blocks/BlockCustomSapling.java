package com.sown.outerrim.blocks;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCustomSapling extends BlockSapling {

    private final String saplingName;
    private WorldGenAbstractTree treeGenerator;

    public BlockCustomSapling(String name, WorldGenAbstractTree generator) {
        super();
        this.saplingName = name;
        this.treeGenerator = generator;
        this.setBlockName(name);
        this.setBlockTextureName("outerrim:" + name);
        this.setStepSound(soundTypeGrass);
    }

    public void setTreeGenerator(WorldGenAbstractTree generator) {
        this.treeGenerator = generator;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, random);
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) {
                this.func_149878_d(world, x, y, z, random);
            }
        }
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random) {
        if (treeGenerator == null) return;

        world.setBlockToAir(x, y, z);

        if (!treeGenerator.generate(world, random, x, y, z)) {
            world.setBlock(x, y, z, this);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName());
    }
}
