package com.sown.outerrim.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;

public class BlockCustomSolid extends BlockCustom {
    private boolean selfDropped = false;
    private Block blockDropped;
    private Item itemDropped;
    private boolean isMultiSided = false;
    private IIcon[] field_150158_M;
    private IIcon field_150159_N;
    private IIcon field_150160_O;
    private static final String[] field_150156_b = new String[]{"front"};
    private String textureName;
    private boolean isTranslucent = false;

    private Item droppedItem = null;
    private int droppedMeta = 0;
    private Block droppedBlock = null;
    private int minDrops = 1;
    private int maxDrops = 1;
    private float experience = 0;

    public BlockCustomSolid(String name, Material material, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material, hardness, harvestTool, harvestLevel, stepSound);
        this.isMultiSided = isMultiSided;
        this.textureName = name;
        this.setCreativeTab(OuterRim.tabBlock);

        if (name.toLowerCase().contains("ilum") && name.toLowerCase().contains("crystal")) {
            this.isTranslucent = true;
        }
        if (name.toLowerCase().contains("ice")) {
            this.slipperiness = 0.98F;
        }
    }
    
    public void setItemDropped(Item item, int metadata) {
        this.droppedItem = item;
        this.droppedMeta = metadata;
    }

    public String getTextureName() {
        return this.textureName;
    }
    
    public void setMinMaxDrops(int min, int max) {
        this.minDrops = min;
        this.maxDrops = max;
    }

    public void setExperience(float exp) {
        this.experience = exp;
    }
    
    public void setExperienceDropped(float experience) {
        this.experience = experience;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        if (isMultiSided) {
            this.field_150158_M = new IIcon[field_150156_b.length];
            for (int i = 0; i < this.field_150158_M.length; ++i) {
                this.field_150158_M[i] = par1IconRegister.registerIcon("outerrim:" + this.name + "_" + field_150156_b[i]);
            }
            this.field_150159_N = par1IconRegister.registerIcon("outerrim:" + this.name + "_top");
            this.field_150160_O = par1IconRegister.registerIcon("outerrim:" + this.name + "_bottom");
        } else {
            this.icons = new IIcon[1];
            this.icons[0] = par1IconRegister.registerIcon("outerrim:" + this.name);
        }
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        if (selfDropped) {
            return Item.getItemFromBlock(this);
        }
        if (droppedBlock != null) {
            return Item.getItemFromBlock(droppedBlock);
        }
        return droppedItem;
    }

    @Override
    public int damageDropped(int metadata) {
        return this.droppedMeta;
    }

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        Random rand = new Random();
        return this.experience > 0 ? Math.round(this.experience + rand.nextFloat() * fortune) : 0;
    }

    @Override
    public int quantityDropped(Random random) {
        return minDrops + random.nextInt(maxDrops - minDrops + 1);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            int bonus = random.nextInt(fortune + 2) - 1;
            if (bonus < 0) {
                bonus = 0;
            }
            return this.quantityDropped(random) * (bonus + 1);
        }
        return this.quantityDropped(random);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (isMultiSided) {
            if (side != 1 && (side != 0 || meta != 1 && meta != 2)) {
                if (side == 0) {
                    return this.field_150160_O;
                }
                if (meta < 0 || meta >= this.field_150158_M.length) {
                    meta = 0;
                }
                return this.field_150158_M[meta];
            }
            return this.field_150159_N;
        } else {
            return this.icons[0];
        }
    }

    public void setSelfDropped(boolean selfDropped) {
        this.selfDropped = selfDropped;
    }

    public void setBlockDropped(Block blockDropped) {
        this.blockDropped = blockDropped;
    }

    public void setItemDropped(Item itemDropped) {
        this.itemDropped = itemDropped;
    }

    // Only make blocks translucent if their name contains "ilum" and "crystal"
    @Override
    public boolean isOpaqueCube() {
        return !isTranslucent; // Only return false for translucent blocks
    }

    @Override
    public boolean renderAsNormalBlock() {
        return !isTranslucent; // Only return false for translucent blocks
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return isTranslucent ? 1 : 0; // Use render pass 1 for translucence only if block is translucent
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int rotation = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (rotation) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 2); // North
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 2); // East
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 2); // South
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 2); // West
                break;
        }
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        Block blockAbove = world.getBlock(x, y + 1, z);
        Block blockBelow = world.getBlock(x, y - 1, z);

        // IlumRock behavior
        if (this == BlockRegister.getRegisteredBlock("ilumRock")) {
            if (blockAbove == Blocks.snow_layer || blockAbove == Blocks.snow) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("ilumRockSnowy"), 0, 2);
            }
        } else if (this == BlockRegister.getRegisteredBlock("ilumRockSnowy")) {
            if (blockAbove == BlockRegister.getRegisteredBlock("ilumRockSnowy")) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("ilumRock"), 0, 2);
            }
            if (blockBelow == BlockRegister.getRegisteredBlock("ilumRockSnowy")) {
                world.setBlock(x, y - 1, z, BlockRegister.getRegisteredBlock("ilumRock"), 0, 2);
            }
        }

        // ZeffoRock behavior
        if (this == BlockRegister.getRegisteredBlock("zeffoRock")) {
            if (blockAbove == Blocks.snow_layer || blockAbove == Blocks.snow) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoRockSnowy"), 0, 2);
            }
        } else if (this == BlockRegister.getRegisteredBlock("zeffoRockSnowy")) {
            if (blockAbove == BlockRegister.getRegisteredBlock("zeffoRockSnowy")) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoRock"), 0, 2);
            }
            if (blockBelow == BlockRegister.getRegisteredBlock("zeffoRockSnowy")) {
                world.setBlock(x, y - 1, z, BlockRegister.getRegisteredBlock("zeffoRock"), 0, 2);
            }
        } else if (this == BlockRegister.getRegisteredBlock("zeffoRockGrassy")) {
            if (blockAbove == Blocks.snow_layer || blockAbove == Blocks.snow) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoRockSnowy"), 0, 2);
            }
            if (blockAbove == BlockRegister.getRegisteredBlock("zeffoRockGrassy")) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoRock"), 0, 2);
            }
        }

        // ZeffoSlate behavior
        if (this == BlockRegister.getRegisteredBlock("zeffoSlate")) {
            if (blockAbove == Blocks.grass || blockAbove == Blocks.dirt) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoSlateGrassy"), 0, 2);
            }
            if (blockAbove == Blocks.snow_layer || blockAbove == Blocks.snow) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoSlateSnowy"), 0, 2);
            }
        } else if (this == BlockRegister.getRegisteredBlock("zeffoSlateGrassy")) {
            if (blockAbove == BlockRegister.getRegisteredBlock("zeffoSlateGrassy")) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoSlate"), 0, 2);
            }
            if (blockAbove == Blocks.snow_layer || blockAbove == Blocks.snow) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoSlateSnowy"), 0, 2);
            }
        } else if (this == BlockRegister.getRegisteredBlock("zeffoSlateSnowy")) {
            if (blockAbove == BlockRegister.getRegisteredBlock("zeffoSlateSnowy")) {
                world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("zeffoSlate"), 0, 2);
            }
            if (blockBelow == BlockRegister.getRegisteredBlock("zeffoSlateSnowy")) {
                world.setBlock(x, y - 1, z, BlockRegister.getRegisteredBlock("zeffoSlate"), 0, 2);
            }
        }

        super.onNeighborBlockChange(world, x, y, z, neighbor);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.getBlock(x, y, z);
        
        // Don't render internal faces if the adjacent block is the same and it's translucent
        if (block == this && isTranslucent) {
            return false;
        }
        
        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<>();
        int dropCount = world.rand.nextInt((maxDrops - minDrops) + 1) + minDrops;

        if (selfDropped) {
            // Drop the block itself
            drops.add(new ItemStack(Item.getItemFromBlock(this), dropCount, metadata));
        } else if (itemDropped != null) {
            // Drop the specified item with the correct metadata
            drops.add(new ItemStack(itemDropped, dropCount, droppedMeta));
        } else if (blockDropped != null) {
            // Drop the specified block
            drops.add(new ItemStack(Item.getItemFromBlock(blockDropped), dropCount, 0));
        }

        return drops;
    }

    // Optionally adjust light opacity for translucent blocks
    @Override
    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        return isTranslucent ? 1 : super.getLightOpacity(world, x, y, z);
    }
}
