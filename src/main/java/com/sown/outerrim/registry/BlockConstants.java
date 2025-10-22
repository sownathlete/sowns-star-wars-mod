package com.sown.outerrim.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockConstants {

	public static final float HARDNESS_ANVIL = 5.0F;
	public static final float HARDNESS_BEDROCK = -1.0F;
	public static final float HARDNESS_CONCRETE = 1.8F;
	public static final float HARDNESS_DIRT = 0.5F;
	public static final float HARDNESS_END_STONE = 3.0F;
	public static final float HARDNESS_GLASS = 0.3F;
	public static final float HARDNESS_GRAVEL = 0.6F;
	public static final float HARDNESS_ICE = 0.5F;
	public static final float HARDNESS_LADDER = 0.4F;
	public static final float HARDNESS_METAL = 5.0F;
	public static final float HARDNESS_NETHERRACK = 0.4F;
	public static final float HARDNESS_OBSIDIAN = 50.0F;
	public static final float HARDNESS_ORE = 3.0F;
	public static final float HARDNESS_PISTON = 0.5F;
	public static final float HARDNESS_SAND = 0.6F;
	public static final float HARDNESS_SNOW = 0.2F;
	public static final float HARDNESS_SOUL_SAND = 0.5F;
	public static final float HARDNESS_STONE = 1.5F;
	public static final float HARDNESS_WOOD = 2.0F;
    
	public static final Material MATERIAL_AIR = Material.air;
	public static final Material MATERIAL_GRASS = Material.grass;
	public static final Material MATERIAL_GROUND = Material.ground;
	public static final Material MATERIAL_WOOD = Material.wood;
	public static final Material MATERIAL_ROCK = Material.rock;
	public static final Material MATERIAL_IRON = Material.iron;
	public static final Material MATERIAL_ANVIL = Material.anvil;
	public static final Material MATERIAL_WATER = Material.water;
	public static final Material MATERIAL_LAVA = Material.lava;
	public static final Material MATERIAL_LEAVES = Material.leaves;
	public static final Material MATERIAL_PLANTS = Material.plants;
	public static final Material MATERIAL_VINE = Material.vine;
	public static final Material MATERIAL_SPONGE = Material.sponge;
	public static final Material MATERIAL_CLOTH = Material.cloth;
	public static final Material MATERIAL_FIRE = Material.fire;
	public static final Material MATERIAL_SAND = Material.sand;
	public static final Material MATERIAL_CIRCUITS = Material.circuits;
	public static final Material MATERIAL_CARPET = Material.carpet;
	public static final Material MATERIAL_GLASS = Material.glass;
	public static final Material MATERIAL_REDSTONE_LIGHT = Material.redstoneLight;
	public static final Material MATERIAL_TNT = Material.tnt;
	public static final Material MATERIAL_CORAL = Material.coral;
	public static final Material MATERIAL_ICE = Material.ice;
	public static final Material MATERIAL_PACKED_ICE = Material.packedIce;
	public static final Material MATERIAL_SNOW = Material.snow;
	public static final Material MATERIAL_CRAFTED_SNOW = Material.craftedSnow;
	public static final Material MATERIAL_CACTUS = Material.cactus;
	public static final Material MATERIAL_CLAY = Material.clay;
	public static final Material MATERIAL_GOURD = Material.gourd;
	public static final Material MATERIAL_DRAGON_EGG = Material.dragonEgg;
	public static final Material MATERIAL_PORTAL = Material.portal;
	public static final Material MATERIAL_CAKE = Material.cake;
	public static final Material MATERIAL_WEB = Material.web;
	
    public static final int HARVEST_NONE = 0;  // No tool required
    public static final int HARVEST_WOOD = 0;  // Wooden tools
    public static final int HARVEST_STONE = 1; // Stone tools
    public static final int HARVEST_IRON = 2;  // Iron tools
    public static final int HARVEST_DIAMOND = 3; // Diamond tools
    public static final int HARVEST_NETHERITE = 4; // Netherite tools
    
    // Tool types
    public static final String TOOL_PICKAXE = "pickaxe";
    public static final String TOOL_SHOVEL = "shovel";
    public static final String TOOL_AXE = "axe";
    public static final String TOOL_HOE = "hoe";
    public static final String TOOL_SWORD = "sword";

    public static final float LIGHT_NONE = 0.0F;
    public static final float LIGHT_DIM = 0.3F;  // Example: End Portal Frame
    public static final float LIGHT_LOW = 0.5F;  // Example: Redstone Torch
    public static final float LIGHT_TORCH = 1.0F;  // Example: Torch
    public static final float LIGHT_GLOWSTONE = 15.0F;  // Example: Glowstone
    public static final float LIGHT_LANTERN = 14.0F;  // Example: Lantern
    public static final float LIGHT_FIRE = 15.0F;  // Example: Fire
    public static final float LIGHT_PORTAL = 11.0F;  // Example: Nether Portal
    public static final float LIGHT_SEA_LANTERN = 15.0F;  // Example: Sea Lantern
    
    public static final boolean DROPS_ITSELF = true;
    public static final boolean DOES_NOT_DROP_ITSELF = false;

    public static final boolean MULTI_SIDED = true;
    public static final boolean SINGLE_SIDED = false;

    public static final boolean HAS_ADDITIONAL_BLOCKS = true;
    public static final boolean NO_ADDITIONAL_BLOCKS = false;

    public static final boolean HAS_REDSTONE_BLOCKS = true;  // Indicates if the block has an interactive component (e.g., button, trapdoor)
    public static final boolean NO_REDSTONE_BLOCKS = false;

    public static final boolean DECORATIVE = true;
    public static final boolean NOT_DECORATIVE = false;

    public static final boolean INCLUDES_FENCE = true;
    public static final boolean NO_FENCE = false;

    public static final boolean INCLUDES_WALL = true;
    public static final boolean NO_WALL = false;

    public static final Block NO_BLOCK_DROPPED = null; // Defaults to dropping itself if null
    public static final Item NO_ITEM_DROPPED = null;   // Defaults to dropping itself if null
 
    public static final SoundType SOUND_ANVIL = Block.soundTypeAnvil;
    public static final SoundType SOUND_CLOTH = Block.soundTypeCloth;
    public static final SoundType SOUND_GLASS = Block.soundTypeGlass;
    public static final SoundType SOUND_GRASS = Block.soundTypeGrass;
    public static final SoundType SOUND_GRAVEL = Block.soundTypeGravel;
    public static final SoundType SOUND_LADDER = Block.soundTypeLadder;
    public static final SoundType SOUND_METAL = Block.soundTypeMetal;
    public static final SoundType SOUND_PISTON = Block.soundTypePiston;
    public static final SoundType SOUND_SAND = Block.soundTypeSand;
    public static final SoundType SOUND_SNOW = Block.soundTypeSnow;
    public static final SoundType SOUND_STONE = Block.soundTypeStone;
    public static final SoundType SOUND_WOOD = Block.soundTypeWood;
    
    public static final float DEFAULT_HARDNESS = HARDNESS_STONE;
    public static final int DEFAULT_HARVEST_LEVEL = HARVEST_STONE;
    public static final float DEFAULT_LIGHT_LEVEL = LIGHT_NONE;
    
    public static final float HARDNESS_PLANT = 0.0F;  // Plants and saplings should have no hardness
    public static final String TOOL_NONE = "";  // For blocks that don't require a tool
    
    public static final float LIGHT_REDSTONE = 7.0F;  // Example: Active Redstone components
    public static final float HARDNESS_COMMAND = -1.0F;  // Example: Command Blocks (unbreakable)
    public static final float LIGHT_FULL = 15.0F;  // Example: Glowstone or fully lit blocks
    public static final Material MATERIAL_DECORATION = Material.circuits;  // Best match for decoration
    public static final float HARDNESS_NONE = 0.0F;  // Example: Air or soft decorative blocks
    public static final SoundType SOUND_WATER = Block.soundTypeCloth;  // Closest alternative for water-like sounds
    public static final String TOOL_SHEARS = "shears";
    public static final float HARDNESS_SOFT = 0.2F;  // Example: Wool, carpets, sponges
    public static final float LIGHT_BEAM = 10.0F;  // Example: A light-emitting block (adjust as needed)

    
}
