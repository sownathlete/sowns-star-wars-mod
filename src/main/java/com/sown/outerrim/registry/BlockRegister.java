/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.tileentity.TileEntity
 */
package com.sown.outerrim.registry;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.blocks.BlockCarbonite;
import com.sown.outerrim.blocks.BlockCoaxiumContainer;
import com.sown.outerrim.blocks.BlockCoaxiumDeposit;
import com.sown.outerrim.blocks.BlockCoaxiumDepositEmpty;
import com.sown.outerrim.blocks.BlockCoaxiumDepositVolatile;
import com.sown.outerrim.blocks.BlockCoaxiumPump;
import com.sown.outerrim.blocks.BlockCoaxiumRack;
import com.sown.outerrim.blocks.BlockCoaxiumRefinery;
import com.sown.outerrim.blocks.BlockCustomBush;
import com.sown.outerrim.blocks.BlockCustomSolid;
import com.sown.outerrim.blocks.BlockMoistureVaporator;
import com.sown.outerrim.blocks.BlockPortableCoaxiumPump;
import com.sown.outerrim.blocks.BlockVenatorBridgeChair;
import com.sown.outerrim.blocks.BlockVenatorBridgeDoor;
import com.sown.outerrim.blocks.BlockVenatorBridgeMechanicalTable;
import com.sown.outerrim.blocks.BlockVenatorHoloTable;
import com.sown.outerrim.blocks.BlockVenatorScreen;
import com.sown.outerrim.blocks.BlockVolcanicEruption;
import com.sown.outerrim.blocks.BlockCustomButton;
import com.sown.outerrim.blocks.BlockCustomDeadBush;
import com.sown.outerrim.blocks.BlockCustomFence;
import com.sown.outerrim.blocks.BlockCustomGlass;
import com.sown.outerrim.blocks.BlockCustomGlassLayer;
import com.sown.outerrim.blocks.BlockCustomLayer;
import com.sown.outerrim.blocks.BlockCustomLeaves;
import com.sown.outerrim.blocks.BlockCustomLog;
import com.sown.outerrim.blocks.BlockCustomPressurePlate;
import com.sown.outerrim.blocks.BlockCustomRedstoneTorch;
import com.sown.outerrim.blocks.BlockCustomSapling;
import com.sown.outerrim.blocks.BlockCustomSlab;
import com.sown.outerrim.blocks.BlockCustomStairs;
import com.sown.outerrim.blocks.BlockCustomTorch;
import com.sown.outerrim.blocks.BlockCustomTrapdoor;
import com.sown.outerrim.blocks.BlockCustomTrapdoorNew;
import com.sown.outerrim.blocks.BlockCustomWall;
import com.sown.outerrim.blocks.BlockFeluciaFlowerTurquoise;
import com.sown.outerrim.blocks.BlockHoloProjector;
import com.sown.outerrim.blocks.BlockKaminoDoorLarge;
import com.sown.outerrim.blocks.BlockKaminoDoorSmall;
import com.sown.outerrim.blocks.BlockKaminoRailing;
import com.sown.outerrim.fluids.BlockFluidCustom;
import com.sown.outerrim.items.ItemBlockCustomSlab;
import com.sown.outerrim.items.ItemBlockCustomSlabDouble;
import com.sown.outerrim.tileentities.TileEntityCarbonite;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRack;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;
import com.sown.outerrim.tileentities.TileEntityHoloProjector;
import com.sown.outerrim.tileentities.TileEntityKaminoDoorLarge;
import com.sown.outerrim.tileentities.TileEntityKaminoDoorSmall;
import com.sown.outerrim.tileentities.TileEntityKaminoRailing;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityVenatorBridgeChair;
import com.sown.outerrim.tileentities.TileEntityVenatorBridgeDoor;
import com.sown.outerrim.tileentities.TileEntityVenatorBridgeMechanicalTable;
import com.sown.outerrim.tileentities.TileEntityVenatorHoloTable;
import com.sown.outerrim.tileentities.TileEntityVenatorScreen;
import com.sown.outerrim.world.gen.WorldGenDarkTree;
import com.sown.outerrim.world.gen.WorldGenJaporIvoryTree;
import com.sown.util.block.ORBlockContainer;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;

public class BlockRegister {
	public static Block coaxiumDepositVolatile;
	public static Block coaxiumDepositFull;
	public static Block coaxiumDepositEmpty;
	//public static Block coaxiumContainer;
	public static Block coaxiumRefinery;
	public static Block coaxiumPump;
	public static Block coaxiumVolatile() { return coaxiumDepositVolatile; }
	static Map<String, Block> registeredBlocks = new HashMap<>();
	public static List<Block> solidBlocks = new ArrayList<Block>();
	public static List<Block> slabBlocks = new ArrayList<Block>();
	public static List<Block> stairsBlocks = new ArrayList<Block>();
	public static List<Block> wallBlocks = new ArrayList<Block>();
	public static List<Block> fenceBlocks = new ArrayList<Block>();
	public static List<Block> pressurePlateBlocks = new ArrayList<Block>();
	public static List<Block> buttonBlocks = new ArrayList<Block>();
	public static List<Block> trapdoorBlocks = new ArrayList<Block>();
	public static List<Block> decorationBlocks = new ArrayList<Block>();
	public static List<Block> leavesBlocks = new ArrayList<Block>();
	public static List<Block> utilBlocks = new ArrayList<Block>();
	public static List<Block> redstoneComponentsList = new ArrayList<Block>();

	public static void registerAll() {
		coaxiumDepositVolatile = new BlockCoaxiumDepositVolatile();
		GameRegistry.registerBlock(coaxiumDepositVolatile, "coaxiumDepositVolatile");

		coaxiumDepositFull  = new BlockCoaxiumDeposit();
		coaxiumDepositEmpty = new BlockCoaxiumDepositEmpty();
		GameRegistry.registerBlock(coaxiumDepositFull,  "coaxiumDeposit");
		GameRegistry.registerBlock(coaxiumDepositEmpty, "coaxiumDepositEmpty");

		//coaxiumContainer = new BlockCoaxiumContainer();
		//coaxiumRefinery  = new BlockCoaxiumRefinery();
		//GameRegistry.registerBlock(coaxiumContainer, "coaxiumContainer");
		//GameRegistry.registerBlock(coaxiumRefinery,  "coaxiumRefinery");

		coaxiumPump = new BlockCoaxiumPump();
		GameRegistry.registerBlock(coaxiumPump, "coaxiumPump");		
		GameRegistry.registerTileEntity(TileEntityCoaxiumPump.class,         "OR_CoaxiumPump");
//		GameRegistry.registerTileEntity(TileEntityCoaxiumPortablePump.class, "OR_CoaxiumPortablePump");
		//GameRegistry.registerTileEntity(TileEntityCoaxiumContainer.class,    "OR_CoaxiumContainer");
		//GameRegistry.registerTileEntity(TileEntityCoaxiumRefinery.class,     "OR_CoaxiumRefinery");

		registerBlock(BlockCustomSolid.class, "archive_bookshelf", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "archive_bookshelf_2", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "archive_bookshelf_3", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "ashBlack", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_GRAVEL,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "ashWhite", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_GRAVEL,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		// registerBlock(BlockCustomSolid.class, "beskar_block",
		// BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
		// BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
		// BlockConstants.SOUND_METAL,
		// BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
		// BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
		// BlockConstants.SINGLE_SIDED, BlockConstants.HAS_ADDITIONAL_BLOCKS,
		// BlockConstants.HAS_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
		// BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		// registerBlock(BlockCustomSolid.class, "beskarOre",
		// BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
		// BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON,
		// BlockConstants.SOUND_STONE,
		// BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
		// BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
		// BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
		// BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
		// BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "boganoGrass", BlockConstants.MATERIAL_GRASS,
				BlockConstants.HARDNESS_GLASS, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_GRASS, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "boganoRockDark", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS,
				BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE, BlockConstants.INCLUDES_WALL);
		registerBlock(BlockCustomSolid.class, "boganoRockLight", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS,
				BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE, BlockConstants.INCLUDES_WALL);
		registerBlock(BlockCustomSolid.class, "braccaCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.INCLUDES_WALL);
		registerBlock(BlockCustomSolid.class, "braccaRock", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_STONE,
				BlockConstants.LIGHT_NONE, BlockRegister.getRegisteredBlock("braccaCobblestone"),
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DOES_NOT_DROP_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS,
				BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE, BlockConstants.INCLUDES_WALL);
		registerBlock(BlockCustomSolid.class, "braccaRubble1", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "braccaRubble2", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "braccaScrap", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlockWithTileEntity(BlockCarbonite.class, TileEntityCarbonite.class, "carbonite_block",
				BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_OBSIDIAN, BlockConstants.TOOL_PICKAXE,
				BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
				BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
				BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
				decorationBlocks);
		registerBlock(BlockCustomSolid.class, "coaxiumOre2", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON, BlockConstants.SOUND_STONE,
				BlockConstants.LIGHT_LOW, BlockConstants.NO_BLOCK_DROPPED, ItemRegister.getRegisteredItem("coaxiumRaw"),
				BlockConstants.DOES_NOT_DROP_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlockWithTileEntity(BlockCoaxiumContainer.class, TileEntityCoaxiumContainer.class, "coaxium_container",
				BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_PICKAXE,
				BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
				BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
				BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
				decorationBlocks);
		registerBlockWithTileEntity(BlockCoaxiumContainer.class, TileEntityCoaxiumContainer.class, "coaxium_rack",
				BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_PICKAXE,
				BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
				BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
				BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
				decorationBlocks);
		registerBlockWithTileEntity(BlockPortableCoaxiumPump.class, TileEntityPortableCoaxiumPump.class, "portable_coaxium_pump",
			    BlockConstants.MATERIAL_IRON, 4.0F, BlockConstants.TOOL_PICKAXE, 1, BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE,
			    BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
			    BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
			    decorationBlocks);
		registerBlockWithTileEntity(BlockCoaxiumRefinery.class, TileEntityCoaxiumRefinery.class, "coaxium_refinery",
				BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_PICKAXE,
				BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
				BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
				BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
				decorationBlocks);
		// registerBlock(BlockCustomSolid.class, "christophsisRoad",
		// BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
		// BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
		// BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, true,
		// BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		// registerBlock(BlockCustomSolid.class, "christophsisCrystal",
		// BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
		// BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
		// BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DOES_NOT_DROP_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
		// BlockConstants.NO_WALL);
		registerTree("darkTree");
		registerBlock(BlockCustomSolid.class, "dathomirBricks", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "dathomirCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "dathomirDirt", BlockConstants.MATERIAL_GROUND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_GRAVEL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "dathomirRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockRegister.getRegisteredBlock("dathomirCobblestone"),
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DOES_NOT_DROP_ITSELF, BlockConstants.MULTI_SIDED, true, BlockConstants.HAS_REDSTONE_BLOCKS,
				false, false, true);
		registerBlock(BlockCustomSolid.class, "dathomirSlate", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerDeadBush("dead_bush");
		registerBlock(BlockCustomSolid.class, "deathStarFloorPanel", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				false, true);
		registerBlock(BlockCustomSolid.class, "deathStarWallPanel1", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_TORCH, null, null, true, false, false, false, true,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "deathStarWallPanel2", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_TORCH, null, null, true, false, false, false, true,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "deathStarWallPanel3", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "deathStarWallPanel4", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "dullMud", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_GRAVEL,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.HAS_ADDITIONAL_BLOCKS, false,
				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		// This block is causing an error when run on servers due to the particles its making?
		/* registerBlockWithTileEntity(BlockFeluciaFlowerTurquoise.class, TileEntityFeluciaFlowerTurquoise.class, "felucia_flower_tall_turquoise",
		        BlockConstants.MATERIAL_PLANTS, BlockConstants.HARDNESS_PLANT, BlockConstants.TOOL_SHEARS,
		        BlockConstants.HARVEST_NONE, BlockConstants.SOUND_GRASS, BlockConstants.LIGHT_NONE,
		        BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
		        BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		        decorationBlocks);*/
		registerBlock(BlockCustomSolid.class, "fortInquisFloorPanel", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "fortInquisWallPanel4", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_TORCH, null, null, true, false, false, false, true,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "fortInquisWallPanel3", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_TORCH, null, null, true, false, false, false, true,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "fortInquisWallPanel1", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "fortInquisWallPanel2", BlockConstants.MATERIAL_IRON,
				BlockConstants.HARDNESS_METAL, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_METAL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "geonosisCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "geonosisGravel", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_GLASS, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_GRAVEL, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "geonosisRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE,
				BlockRegister.getRegisteredBlock("geonosisCobblestone"), null, false, false, true, true, false, false,
				true);
		registerBlock(BlockCustomSolid.class, "geonosisSand", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_SAND, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "ilumCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "ilumIce", BlockConstants.MATERIAL_ICE, BlockConstants.HARDNESS_GLASS,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_GLASS,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, false, true);
		registerBlock(BlockCustomSolid.class, "ilumRock", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_STONE,
				BlockConstants.LIGHT_NONE, BlockRegister.getRegisteredBlock("ilumCobblestone"), null, false, false,
				true, true, false, false, true);
//		registerBlock(BlockCustomSolid.class, "ilumCoalOre", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
//				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_STONE,
//				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, Items.coal, false, false, false, false,
//				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumDiamondOre", BlockConstants.MATERIAL_ROCK,
//				BlockConstants.HARDNESS_ORE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON,
//				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, Items.diamond,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumEmeraldOre", BlockConstants.MATERIAL_ROCK,
//				BlockConstants.HARDNESS_ORE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON,
//				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, Items.emerald,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumGoldOre", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
//				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON, BlockConstants.SOUND_STONE,
//				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
//				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
//				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
//				BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumIronOre", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
//				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE,
//				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
//				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
//				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
//				BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumLapisOre", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
//				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_STONE, BlockConstants.SOUND_STONE,
//				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
//				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
//				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
//				BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumQuartzOre", BlockConstants.MATERIAL_ROCK,
//				BlockConstants.HARDNESS_ORE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON,
//				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, Items.quartz,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "ilumRedstoneOre", BlockConstants.MATERIAL_ROCK,
//				BlockConstants.HARDNESS_ORE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON,
//				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, Items.redstone,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);

		// Ilum Ores
		registerOre(BlockCustomSolid.class, "ilumCoalOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 0, Block.soundTypeStone, 0.0F, true, Items.coal, 0, null, 1, 3, 0.1F, false);

		registerOre(BlockCustomSolid.class, "ilumDiamondOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.diamond, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "ilumEmeraldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.emerald, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "ilumGoldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, false, null, 0, BlockRegister.getRegisteredBlock("ilumGoldOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "ilumIronOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, 0, BlockRegister.getRegisteredBlock("ilumIronOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "ilumLapisOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("ilumLapisOre"), 1, 1, 0.2F, false);

		registerOre(BlockCustomSolid.class, "ilumQuartzOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.quartz, 0, null, 1, 1, 0.2F, false);

		registerOre(BlockCustomSolid.class, "ilumRedstoneOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.redstone, 0, null, 4, 8, 0.7F, false);
		
		registerBlock(BlockCustomSolid.class, "ilumRockSnowy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "ilumSlate", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, BlockConstants.SOUND_STONE,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.HAS_ADDITIONAL_BLOCKS, false,
				false, false, true);
		registerTree("japorIvory");
		registerBlock(BlockCustomSolid.class, "karnite_block", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				BlockConstants.SOUND_STONE, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, true, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "karniteOre", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_ORE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_IRON, BlockConstants.SOUND_STONE,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "karnitePillar", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, true, true, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselAcidicRock1", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselAcidicRock2", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselDirt1", BlockConstants.MATERIAL_GROUND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeGravel, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselDirt2", BlockConstants.MATERIAL_GROUND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeGravel, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselMachineryGlow1", BlockConstants.MATERIAL_IRON, 5.0F,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeMetal, 1.0F, null, null, true,
				false, false, false, true, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselMachineryGlow2", BlockConstants.MATERIAL_IRON, 5.0F,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeMetal, 1.0F, null, null, true,
				false, false, false, true, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselMachinery1", BlockConstants.MATERIAL_IRON, 5.0F,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeMetal,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselMachinery2", BlockConstants.MATERIAL_IRON, 5.0F,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeMetal,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
				BlockConstants.NO_REDSTONE_BLOCKS, true, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselMachineryPanel", BlockConstants.MATERIAL_IRON, 5.0F,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeMetal,
				BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
				BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED, BlockConstants.HAS_ADDITIONAL_BLOCKS,
				BlockConstants.HAS_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE, false, true);
		registerBlock(BlockCustomSolid.class, "kesselMud", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, Block.soundTypeGravel, 0.0F, null, null, true,
				false, true, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "kesselRock1", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, true, false, false, true);
//		registerBlock(BlockCustomSolid.class, "kesselCoalOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone, 0.0F, null, Items.coal,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "coaxiumOre1", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.1F, null, null, true, false, false, false,
//				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselDiamondOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, BlockConstants.LIGHT_NONE, null, Items.diamond,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselEmeraldOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, BlockConstants.LIGHT_NONE, null, Items.emerald,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselGoldOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, null, null, true, false, false, false,
//				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselIronOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, null, null, true, false, false, false,
//				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselLapisOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, null, null, true, false, false, false,
//				false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselQuartzOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, BlockConstants.LIGHT_NONE, null, Items.quartz,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "kesselRedstoneOre", BlockConstants.MATERIAL_ROCK, 3.0F,
//				BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, BlockConstants.LIGHT_NONE, null, Items.redstone,
//				false, false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		
		registerOre(BlockCustomSolid.class, "coaxiumOre1", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.1F, true, ItemRegister.getRegisteredItem("coaxiumRaw"), 0, null, 1, 1, 1.2F, false);

		registerOre(BlockCustomSolid.class, "kesselCoalOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 0, Block.soundTypeStone, 0.0F, true, Items.coal, 0, null, 1, 3, 0.1F, false);

		registerOre(BlockCustomSolid.class, "kesselDiamondOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.diamond, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "kesselEmeraldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.emerald, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "kesselGoldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("kesselGoldOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "kesselIronOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("kesselIronOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "kesselLapisOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("kesselLapisOre"), 1, 1, 0.2F, false); // Lapis Lazuli

		registerOre(BlockCustomSolid.class, "kesselQuartzOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.quartz, 0, null, 1, 1, 0.2F, false);

		registerOre(BlockCustomSolid.class, "kesselRedstoneOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.redstone, 0, null, 4, 8, 0.7F, false);
		
		registerBlock(BlockCustomSolid.class, "kesselRock2", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, true, false, false, true);
		registerGlassLayerBlock("forceLayer", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeGlass);
		registerBlock(BlockCustomSolid.class, "malachorGravel", BlockConstants.MATERIAL_SAND, 0.6F,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, Block.soundTypeGravel, 0.0F, null, null, true,
				false, false, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "malachorRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, true, false, false, true);
		registerBlock(BlockCustomSolid.class, "malachorRoughRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "malachorRubble", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mandaloreMud", BlockConstants.MATERIAL_GROUND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeGravel, 0.0F, null, null, true, false, true, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mandaloreRubble1", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mandaloreRubble2", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mandaloreSand", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeSand, 0.0F, null, null, true, false, true, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mandaloreSandstone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED, true, false,
				false, false, true);
		registerBlock(BlockCustomSolid.class, "massassiCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "massassiRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, BlockConstants.HAS_REDSTONE_BLOCKS, false, false, true);
		registerBlock(BlockCustomSolid.class, "massassiBricks", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "massassiBricksMossy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlockWithTileEntity(BlockMoistureVaporator.class, TileEntityMoistureVaporator.class,
				"moisture_vaporator", BlockConstants.MATERIAL_IRON, 50.0F, BlockConstants.TOOL_PICKAXE, 2,
				Block.soundTypeMetal, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, false, false, false, utilBlocks);
		registerBlock(BlockCustomSolid.class, "mud", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, Block.soundTypeGravel,
				BlockConstants.LIGHT_NONE, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mustafarCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "mustafarMagma", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.5F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "mustafarRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, BlockRegister.getRegisteredBlock("mustafarCobblestone"), null, false, true,
				true, BlockConstants.HAS_REDSTONE_BLOCKS, false, false, true);
		registerBlock(BlockCustomSolid.class, "nurRock", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone,
				BlockConstants.LIGHT_NONE, BlockRegister.getRegisteredBlock("nurCobblestone"), null, false, false, true,
				BlockConstants.HAS_REDSTONE_BLOCKS, false, false, true);
		registerBlock(BlockCustomSolid.class, "nurCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, null, null, true, false, true, false, false, false,
				true);
		registerBlock(BlockCustomSolid.class, "nurSoil", BlockConstants.MATERIAL_GROUND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE, Block.soundTypeGravel, 0.0F, null, null, true,
				false, true, false, false, BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "permacrete", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone, 0.0F, null, null, true,
				false, true, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "redSalt", BlockConstants.MATERIAL_SAND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeSand,
				BlockConstants.LIGHT_NONE, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "rylothRock", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone, 0.0F, null, null, true,
				false, true, BlockConstants.HAS_REDSTONE_BLOCKS, false, false, true);
		registerBlock(BlockCustomSolid.class, "rylothDirt", BlockConstants.MATERIAL_GROUND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeGravel, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "salt", BlockConstants.MATERIAL_SAND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeSand,
				BlockConstants.LIGHT_NONE, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerLayerBlock("salt_layer", BlockConstants.MATERIAL_SAND, 0.15F, BlockConstants.TOOL_PICKAXE,
				BlockConstants.HARVEST_NONE, Block.soundTypeSand);
		registerBlock(BlockCustomSolid.class, "snowyStone", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone,
				BlockConstants.LIGHT_NONE, Blocks.stone, null, false, true, false, false, false,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooine_awning", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_AXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeCloth, 0.0F, null, null, true, false, true, false, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineCobblestone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.HAS_ADDITIONAL_BLOCKS, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "tatooine_crate", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineRock", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, BlockRegister.getRegisteredBlock("tatooineCobblestone"), null, false, false,
				true, BlockConstants.HAS_REDSTONE_BLOCKS, false, false, true);
		
		registerOre(BlockCustomSolid.class, "tatooineCoalOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 0, Block.soundTypeStone, 0.0F, true, Items.coal, 0, null, 1, 3, 0.1F, false);

		registerOre(BlockCustomSolid.class, "tatooineDiamondOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.diamond, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "tatooineEmeraldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.emerald, 0, null, 1, 1, 1.0F, false);

		registerOre(BlockCustomSolid.class, "tatooineGoldOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("tatooineGoldOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "tatooineIronOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("tatooineIronOre"), 1, 1, 0.0F, false);

		registerOre(BlockCustomSolid.class, "tatooineLapisOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 1, Block.soundTypeStone, 0.0F, false, null, null, BlockRegister.getRegisteredBlock("tatooineLapisOre"), 1, 1, 0.2F, false); // Lapis Lazuli

		registerOre(BlockCustomSolid.class, "tatooineQuartzOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.quartz, 0, null, 1, 1, 0.2F, false);

		registerOre(BlockCustomSolid.class, "tatooineRedstoneOre", BlockConstants.MATERIAL_ROCK, 3.0F, BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeStone, 0.0F, true, Items.redstone, 0, null, 4, 8, 0.7F, false);
		
		registerBlock(BlockCustomSolid.class, "tatooineSand1", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeSand, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineSand2", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeSand, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineSand3", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeSand, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineSand4", BlockConstants.MATERIAL_SAND,
				BlockConstants.HARDNESS_DIRT, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
				Block.soundTypeSand, 0.0F, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "tatooineSandstone", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED, true, false,
				false, false, true);
		registerBlock(BlockCustomSolid.class, "tatooine_wall", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "temple_brick", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, false, true, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "trinitite", BlockConstants.MATERIAL_SAND, BlockConstants.HARDNESS_DIRT,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeSand,
				BlockConstants.LIGHT_NONE, null, null, true, false, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "zeffoRock", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone, 0.0F, null, null, true,
				false, true, true, false, false, true);
		registerBlock(BlockCustomSolid.class, "zeffoRockGrassy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "zeffoRockSnowy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, true, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "zeffoSlate", BlockConstants.MATERIAL_ROCK, BlockConstants.HARDNESS_STONE,
				BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE, Block.soundTypeStone, 0.0F, null, null, true,
				false, true, false, false, false, true);
		registerBlock(BlockCustomSolid.class, "zeffoSlateGrassy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.MULTI_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "zeffoSlateMossy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
				BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS, BlockConstants.NOT_DECORATIVE,
				BlockConstants.NO_FENCE, BlockConstants.NO_WALL);
		registerBlock(BlockCustomSolid.class, "zeffoSlateSnowy", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, 0.0F, null, null, true, true, false, false, false, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		registerBlock(BlockVolcanicEruption.class, "volcanicEruption", BlockConstants.MATERIAL_ROCK,
				BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
				Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
				BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
				BlockConstants.NO_WALL);
		
		
		// VC_MC Blocks
		
//		registerBlock(BlockCustomSolid.class, "hardened_clay_stained_taupe", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "tech_block_1", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "flintstone", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.INCLUDES_WALL);
//		registerBlock(BlockCustomSolid.class, "tech_block_2", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "tech_block_3", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "hardened_clay_stained_dark_gray", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "adobe", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "hardened_clay_stained_iris_blue", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "durasteel_3", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "hardened_clay_stained_beige", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "durasteel_2", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "durasteel_5", BlockConstants.MATERIAL_CLAY,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "tech_block_4", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerTrapdoorNew("black_iron_trapdoor", "black_iron_trapdoor", 5.0F, "pickaxe", 2, Block.soundTypeMetal, registeredBlocks);
//		registerBlock(BlockCustomSolid.class, "hardened_clay_stained_light_brown", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "durasteel_1", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.INCLUDES_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "durasteel_4", BlockConstants.MATERIAL_WOOD,
//		        BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_AXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeWood, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.INCLUDES_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "cut_sandstone", BlockConstants.MATERIAL_WOOD,
//		        BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_AXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeWood, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "pourstone", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "chalkstone", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "aged_planks", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, true, false, true, BlockConstants.INCLUDES_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "beige_sand", BlockConstants.MATERIAL_SAND,
//		        BlockConstants.HARDNESS_SAND, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeSand, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "beige_sandstone_carved", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, true, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "beige_sandstone_normal", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, true, true, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "beige_sandstone_smooth", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, true, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "tech_block_5", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerTorch("plamsa_lamp", "plasma_lamp_on", registeredBlocks);
//		registerBlock(BlockCustomSolid.class, "grain_sand", BlockConstants.MATERIAL_SAND,
//		        BlockConstants.HARDNESS_SAND, BlockConstants.TOOL_SHOVEL, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeSand, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "glow_panel", BlockConstants.MATERIAL_GLASS,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeGlass, BlockConstants.LIGHT_FULL, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "hardened_stone", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "voidstone", BlockConstants.MATERIAL_ROCK,
//		        BlockConstants.HARDNESS_STONE, BlockConstants.TOOL_PICKAXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeStone, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);
//		registerBlock(BlockCustomSolid.class, "synthwool", BlockConstants.MATERIAL_WOOD,
//		        BlockConstants.HARDNESS_WOOD, BlockConstants.TOOL_AXE, BlockConstants.HARVEST_NONE,
//		        Block.soundTypeWood, BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
//		        BlockConstants.NO_ITEM_DROPPED, false, false, false, false, true, BlockConstants.NO_FENCE,
//		        BlockConstants.NO_WALL);

		// registerBlockWithTileEntity(BlockVenatorScreen.class,
		// TileEntityVenatorScreen.class, "venator_screen",
		// BlockConstants.MATERIAL_IRON, 50.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, false, false, false, decorationBlocks);
		// registerBlockWithTileEntity(BlockKaminoDoorLarge.class,
		// TileEntityKaminoDoorLarge.class, "kamino_door_large",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);
		// registerBlockWithTileEntity(BlockKaminoDoorSmall.class,
		// TileEntityKaminoDoorSmall.class, "kamino_door_small",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);
		// registerBlockWithTileEntity(BlockKaminoRailing.class,
		// TileEntityKaminoRailing.class, "kamino_railing",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);
		// registerBlockWithTileEntity(BlockVenatorBridgeChair.class,
		// TileEntityVenatorBridgeChair.class, "venator_bridge_chair",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);
		// registerBlockWithTileEntity(BlockVenatorBridgeDoor.class,
		// TileEntityVenatorBridgeDoor.class, "venator_bridge_door",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);
		// registerBlockWithTileEntity(BlockVenatorBridgeMechanicalTable.class,
		// TileEntityVenatorBridgeMechanicalTable.class,
		// "venator_bridge_mechanical_table", BlockConstants.MATERIAL_IRON, 5.0F,
		// BlockConstants.TOOL_PICKAXE, 2, Block.soundTypeMetal,
		// BlockConstants.LIGHT_NONE, BlockConstants.NO_BLOCK_DROPPED,
		// BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
		// BlockConstants.SINGLE_SIDED, BlockConstants.NO_ADDITIONAL_BLOCKS,
		// BlockConstants.NO_REDSTONE_BLOCKS, decorationBlocks);
		// registerBlockWithTileEntity(BlockVenatorHoloTable.class,
		// TileEntityVenatorHoloTable.class, "venator_holo_table",
		// BlockConstants.MATERIAL_IRON, 5.0F, BlockConstants.TOOL_PICKAXE, 2,
		// Block.soundTypeMetal, BlockConstants.LIGHT_NONE,
		// BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED,
		// BlockConstants.DROPS_ITSELF, BlockConstants.SINGLE_SIDED,
		// BlockConstants.NO_ADDITIONAL_BLOCKS, BlockConstants.NO_REDSTONE_BLOCKS,
		// decorationBlocks);

		registerOreDictionaryEntriesAutomatically();
	}

	public static <T extends BlockCustomSolid> T registerBlock(Class<T> blockClass, String name, Material material,
			float hardness, String toolType, int harvestLevel, SoundType stepSound, float lightLevel,
			Block blockDropped, Item itemDropped, boolean dropItself, boolean isMultiSided, boolean additionalBlocks,
			boolean redstoneBlocks, boolean isDecorative, boolean includeFence, boolean includeWall) {
		// Create an instance of the block using reflection.
		T block;
		try {
			Constructor<T> constructor = blockClass.getConstructor(String.class, Material.class, float.class,
					String.class, int.class, SoundType.class, boolean.class);
			block = constructor.newInstance(name, material, hardness, toolType, harvestLevel, stepSound, isMultiSided);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		block.setHardness(hardness);
		block.setHarvestLevel(toolType, harvestLevel);
		block.setStepSound(stepSound);
		block.setLightLevel(lightLevel);

		// Handle custom drop logic. Requires implementation in BlockCustomSolid.
		if (dropItself) {
			block.setSelfDropped(true);
		} else {
			if (blockDropped != null) {
				block.setBlockDropped(blockDropped);
			} else if (itemDropped != null) {
				block.setItemDropped(itemDropped);
			}
		}

		GameRegistry.registerBlock(block, name);

		if (isDecorative) {
			decorationBlocks.add(block);
			block.setCreativeTab(OuterRim.tabDeco);
		} else {
			solidBlocks.add(block);
		}

		// Store the registered block in the HashMap using its name as the key
		registeredBlocks.put(name, block);

		if (additionalBlocks) {
			// Register single and double slabs
			Item singleSlabItem = Item.getItemFromBlock(BlockRegister.getRegisteredBlock(name + "_slab"));

			// Create instances of single and double slabs
			BlockCustomSlab singleSlabInstance = new BlockCustomSlab(false, block, singleSlabItem);
			BlockCustomSlab doubleSlabInstance = new BlockCustomSlab(true, block, singleSlabItem);

			GameRegistry.registerBlock(singleSlabInstance, ItemBlockCustomSlab.class, name + "_slab",
					singleSlabInstance, doubleSlabInstance, false);
			GameRegistry.registerBlock(doubleSlabInstance, ItemBlockCustomSlabDouble.class, name + "_slab_double");
			slabBlocks.add(singleSlabInstance);
			// slabBlocks.add(doubleSlabInstance);

			// Add crafting recipes for slabs
			GameRegistry.addRecipe(new ItemStack(singleSlabInstance, 6), "XXX", 'X', block);

			// Register stairs
			BlockCustomStairs stairs = new BlockCustomStairs(block);
			GameRegistry.registerBlock(stairs, name + "_stairs");
			registeredBlocks.put(name + "_stairs", stairs);
			stairsBlocks.add(stairs);

			// Crafting recipe for stairs
			GameRegistry.addRecipe(new ItemStack(stairs, 4), "  X", " XX", "XXX", 'X', block);
		}

		if (includeWall) {
			// Register wall
			BlockCustomWall wall = new BlockCustomWall(block);
			GameRegistry.registerBlock(wall, name + "_wall");
			registeredBlocks.put(name + "_wall", wall);
			wallBlocks.add(wall);

			// Crafting recipe for wall
			GameRegistry.addRecipe(new ItemStack(wall, 6), "XXX", "XXX", "   ", 'X', block);
		}

		if (includeFence) { // Register fence if the flag is true
		    BlockCustomFence fence = new BlockCustomFence(block);
		    GameRegistry.registerBlock(fence, name + "_fence");
		    registeredBlocks.put(name + "_fence", fence);
		    fenceBlocks.add(fence);

		    // Crafting recipe for fence
		    GameRegistry.addRecipe(new ItemStack(fence, 3), "X X", "XXX", 'X', block);
		}

		if (redstoneBlocks) {
			// Create and register trapdoor, button, and pressure plate of the same texture
			// Here we use 'block' as the base block for your custom blocks to share
			// textures with
			// BlockCustomTrapdoor trapdoor = new BlockCustomTrapdoor(name + "_trapdoor",
			// block, hardness, toolType,
			// harvestLevel, stepSound, isMultiSided);
			BlockCustomButton button = new BlockCustomButton(name + "_button", block, hardness, toolType, harvestLevel,
					stepSound, isMultiSided);
			BlockCustomPressurePlate pressurePlate = new BlockCustomPressurePlate(name + "_pressure_plate", block,
					hardness, toolType, harvestLevel, stepSound, isMultiSided);

			// GameRegistry.registerBlock(trapdoor, name + "_trapdoor");
			GameRegistry.registerBlock(button, name + "_button");
			GameRegistry.registerBlock(pressurePlate, name + "_pressure_plate");

			// registeredBlocks.put(name + "_trapdoor", trapdoor);
			registeredBlocks.put(name + "_button", button);
			registeredBlocks.put(name + "_pressure_plate", pressurePlate);
			// trapdoorBlocks.add(trapdoor);
			buttonBlocks.add(button);
			pressurePlateBlocks.add(pressurePlate);

			// Crafting recipes for trapdoor, button, and pressure plate
			// GameRegistry.addRecipe(new ItemStack(trapdoor), " ", "XXX", "XXX", 'X',
			// block);
			GameRegistry.addShapelessRecipe(new ItemStack(button), block);
			GameRegistry.addRecipe(new ItemStack(pressurePlate), "XX", 'X', block);
		}

		return block;
	}

	public static <T extends BlockCustomSolid> T registerOre(
		    Class<T> blockClass,
		    String name,
		    Material material,
		    float hardness,
		    String toolType,
		    int harvestLevel,
		    SoundType stepSound,
		    float lightLevel,
		    boolean dropsItem,
		    Item itemDropped,
		    Integer itemDroppedMeta, // Metadata support for dropped items
		    Block blockDropped,
		    int minDrop,
		    int maxDrop,
		    float experience,
		    boolean isMultiSided
		) {
		    T block;
		    try {
		        Constructor<T> constructor = blockClass.getConstructor(String.class, Material.class, float.class, String.class, int.class, SoundType.class, boolean.class);
		        block = constructor.newInstance(name, material, hardness, toolType, harvestLevel, stepSound, isMultiSided);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }

		    // Set basic block properties
		    block.setHardness(hardness);
		    block.setHarvestLevel(toolType, harvestLevel);
		    block.setStepSound(stepSound);
		    block.setLightLevel(lightLevel);

			if (dropsItem) {
				block.setItemDropped(itemDropped);
			} else {
				block.setSelfDropped(true);
				block.setBlockDropped(blockDropped);
			}

		    // Register block
		    GameRegistry.registerBlock(block, name);

		    // Handle experience drops
		    if (experience > 0) {
		        block.setExperience(experience); // Ensure BlockCustomSolid has this method
		    }

		    // Store in registered blocks map
		    registeredBlocks.put(name, block);

		    // Configure drop amounts
		    block.setMinMaxDrops(minDrop, maxDrop);

		    return block;
		}

	public static BlockCustomDeadBush registerDeadBush(String name) {
		// Create an instance of the custom bush block
		BlockCustomDeadBush bush = new BlockCustomDeadBush(name);

		// Register the block
		GameRegistry.registerBlock(bush, name);

		// Add the block to the decoration blocks list (or another relevant list)
		decorationBlocks.add(bush);

		// Store the registered block in the HashMap for later retrieval
		registeredBlocks.put(name, bush);

		return bush;
	}

	public static BlockCustomBush registerBush(String name) {
		// Create an instance of the custom bush block
		BlockCustomBush bush = new BlockCustomBush(name);

		// Register the block
		GameRegistry.registerBlock(bush, name);

		// Add the block to the decoration blocks list (or another relevant list)
		decorationBlocks.add(bush);

		// Store the registered block in the HashMap for later retrieval
		registeredBlocks.put(name, bush);

		return bush;
	}

	public static BlockCustomLeaves registerLeaves(String name) {
		// Assuming properties like hardness and tool might vary, they could be
		// parameters or configured here
		Material material = BlockConstants.MATERIAL_LEAVES;
		float hardness = 0.2F; // Suitable for leaves
		String toolType = "shears";
		int harvestLevel = 0;
		Block.SoundType stepSound = Block.soundTypeGrass;
		float lightLevel = 0.0F;

		// Direct instantiation without using reflection
		BlockCustomLeaves block = new BlockCustomLeaves(name, hardness, toolType, harvestLevel, stepSound);
		block.setCreativeTab(CreativeTabs.tabDecorations); // More appropriate for leaves typically
		block.setLightOpacity(1); // Leaves are semi-transparent
		block.setHardness(hardness);
		block.setStepSound(stepSound);
		block.setHarvestLevel(toolType, harvestLevel);

		GameRegistry.registerBlock(block, name);

		// Store the registered block in a HashMap if you have a tracking system
		registeredBlocks.put(name, block);
		leavesBlocks.add(block);

		return block;
	}

	public static BlockCustomLog registerLog(String name) {
		// Default properties for a log block
		Material material = BlockConstants.MATERIAL_WOOD;
		float hardness = 2.0F;
		String toolType = "axe";
		int harvestLevel = 0;
		Block.SoundType stepSound = Block.soundTypeWood;
		float lightLevel = 0.0F;

		// Create an instance of the BlockCustomLog.
		BlockCustomLog block;
		try {
			Constructor<BlockCustomLog> constructor = BlockCustomLog.class.getConstructor(String.class, float.class,
					String.class, int.class, Block.SoundType.class);
			block = constructor.newInstance(name, hardness, toolType, harvestLevel, stepSound);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// Setting the block properties
		block.setHardness(hardness);
		block.setHarvestLevel(toolType, harvestLevel);
		block.setStepSound(stepSound);
		block.setLightLevel(lightLevel);

		// Register the block
		GameRegistry.registerBlock(block, name);

		// Store the registered block in the HashMap
		registeredBlocks.put(name, block);
		solidBlocks.add(block);

		return block;
	}

	public static void registerTree(String treeName) {
		// Register leaves
		BlockCustomLeaves leaves = registerLeaves(treeName + "Leaves");

		// Register log
		BlockCustomLog log = registerLog(treeName + "Log");

		// Register planks
		BlockCustomSolid planks = registerBlock(BlockCustomSolid.class, treeName + "Planks",
				BlockConstants.MATERIAL_WOOD, 2.0F, "axe", 0, Block.soundTypeWood, BlockConstants.LIGHT_NONE,
				BlockConstants.NO_BLOCK_DROPPED, BlockConstants.NO_ITEM_DROPPED, BlockConstants.DROPS_ITSELF,
				BlockConstants.SINGLE_SIDED, BlockConstants.HAS_ADDITIONAL_BLOCKS, BlockConstants.HAS_REDSTONE_BLOCKS, false, true, false);

		// Recipe: Log to Planks
		GameRegistry.addRecipe(new ItemStack(planks, 4), "X", 'X', log);

		// Recipe: Planks to Sticks
		GameRegistry.addRecipe(new ItemStack(Items.stick, 4), "X", "X", 'X', planks);

		// Recipe: Planks to Crafting Table
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table, 1), "XX", "XX", 'X', planks);

		// Register Ore Dictionary entries
		OreDictionary.registerOre("logWood", log);
		OreDictionary.registerOre("treeLeaves", leaves);
		OreDictionary.registerOre("plankWood", planks);
		// Assuming slabs and stairs are also registered somewhere in your mod:
		Block slab = BlockRegister.getRegisteredBlock(treeName + "Planks_slab");
		Block stairs = BlockRegister.getRegisteredBlock(treeName + "Planks_stairs");
		OreDictionary.registerOre("slabWood", slab);
		OreDictionary.registerOre("stairWood", stairs);
	}

	public static <T extends ORBlockContainer, E extends TileEntity> T registerBlockWithTileEntity(Class<T> blockClass,
			Class<E> tileEntityClass, String name, Material material, float hardness, String toolType, int harvestLevel,
			SoundType stepSound, float lightLevel, Block blockDropped, Item itemDropped, boolean dropItself,
			boolean isMultiSided, boolean additionalBlocks, boolean redstoneBlocks, List<Block> blockListToAdd // New
																												// parameter
	) {
		T block;
		try {
			Constructor<T> constructor = blockClass.getConstructor(String.class, Material.class, float.class,
					String.class, int.class, SoundType.class, boolean.class);
			block = constructor.newInstance(name, material, hardness, toolType, harvestLevel, stepSound, isMultiSided);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		block.setHardness(hardness);
		block.setHarvestLevel(toolType, harvestLevel);
		block.setStepSound(stepSound);
		block.setLightLevel(lightLevel);

		GameRegistry.registerBlock(block, name);

		// Register the tile entity class with the block
		GameRegistry.registerTileEntity(tileEntityClass, name);

		registeredBlocks.put(name, block);

		// Add the block to the provided list
		blockListToAdd.add(block);

		return block;
	}

	public static BlockCustomLayer registerLayerBlock(String name, Material material, float hardness, String toolType,
			int harvestLevel, Block.SoundType stepSound) {
		// Create an instance of the block.
		BlockCustomLayer block = new BlockCustomLayer(name, material, hardness, toolType, harvestLevel, stepSound);

		// Set block properties
		block.setHardness(hardness);
		block.setHarvestLevel(toolType, harvestLevel);
		block.setStepSound(stepSound);

		// Register the block
		GameRegistry.registerBlock(block, name);

		// Set the creative tab, for example
		block.setCreativeTab(OuterRim.tabBlock);

		// Store the registered block in the HashMap using its name as the key
		registeredBlocks.put(name, block);
		solidBlocks.add(block);

		return block;
	}

	public static BlockCustomGlassLayer registerGlassLayerBlock(String name, Material material, float hardness,
			String toolType, int harvestLevel, Block.SoundType stepSound) {
		// Create an instance of the block.
		BlockCustomGlassLayer block = new BlockCustomGlassLayer(name, material, hardness, toolType, harvestLevel,
				stepSound);

		// Set block properties
		block.setHardness(hardness);
		block.setHarvestLevel(toolType, harvestLevel);
		block.setStepSound(stepSound);

		// Register the block
		GameRegistry.registerBlock(block, name);

		// Set the creative tab, for example
		block.setCreativeTab(OuterRim.tabBlock);

		// Store the registered block in the HashMap using its name as the key
		registeredBlocks.put(name, block);
		solidBlocks.add(block);

		return block;
	}
	
	public static void registerTorch(String name, String texture, Map<String, Block> registeredBlocks) {
	    BlockCustomTorch torch = new BlockCustomTorch(name, texture);
	    GameRegistry.registerBlock(torch, name);
	    registeredBlocks.put(name, torch);
	}

	public static void registerRedstoneTorch(String name, String textureOn, String textureOff, Map<String, Block> registeredBlocks) {
	    BlockCustomRedstoneTorch torch = new BlockCustomRedstoneTorch(name, textureOn, textureOff);
	    GameRegistry.registerBlock(torch, name);
	    registeredBlocks.put(name, torch);
	}

	public static Block registerBlockFluidCustom(Fluid fluid, String name, Material material) {
		// Create and register the corresponding block.
		BlockFluidCustom blockFluid = new BlockFluidCustom(fluid, material);
		blockFluid.setBlockName(name); // Make sure this is unique.
		GameRegistry.registerBlock(blockFluid, name);

		// Store the registered block in the HashMap using its name as the key
		registeredBlocks.put(name, blockFluid);

		return blockFluid;
	}

	public static Block getRegisteredBlock(String name) {
		return registeredBlocks.get(name);
	}
	
	public static void registerTrapdoorNew(String name, String texture, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, Map<String, Block> registeredBlocks) {
	    BlockCustomTrapdoorNew trapdoor = new BlockCustomTrapdoorNew(name, texture, hardness, toolType, harvestLevel, stepSound);
	    GameRegistry.registerBlock(trapdoor, name);
	    registeredBlocks.put(name, trapdoor);
	}

	public static void registerOreDictionaryEntriesAutomatically() {
	    Map<List<String>, String> multiKeywordToOreDict = new HashMap<>();
	    Map<String, String> keywordToOreDict = new LinkedHashMap<>(); // Preserve order

	    // Define a set of keywords to exclude
	    Set<String> excludedKeywords = new HashSet<>(Arrays.asList(
	        "stair", "trapdoor", "slab", "wall", "button", "fence", "door", "gate"
	    ));

	    // Multi-keyword matches (requires ALL words to be present in the block name)
	    multiKeywordToOreDict.put(Arrays.asList("diamond", "ore"), "oreDiamond");
	    multiKeywordToOreDict.put(Arrays.asList("gold", "ore"), "oreGold");
	    multiKeywordToOreDict.put(Arrays.asList("iron", "ore"), "oreIron");
	    multiKeywordToOreDict.put(Arrays.asList("lapis", "ore"), "oreLapis");
	    multiKeywordToOreDict.put(Arrays.asList("redstone", "ore"), "oreRedstone");
	    multiKeywordToOreDict.put(Arrays.asList("emerald", "ore"), "oreEmerald");
	    multiKeywordToOreDict.put(Arrays.asList("quartz", "ore"), "oreQuartz");
	    multiKeywordToOreDict.put(Arrays.asList("coal", "ore"), "oreCoal");
	    multiKeywordToOreDict.put(Arrays.asList("coaxium", "ore"), "oreCoaxium");
	    multiKeywordToOreDict.put(Arrays.asList("karnite", "ore"), "oreKarnite");
	    multiKeywordToOreDict.put(Arrays.asList("beskar", "ore"), "oreBeskar");

	    multiKeywordToOreDict.put(Arrays.asList("diamond", "block"), "blockDiamond");
	    multiKeywordToOreDict.put(Arrays.asList("gold", "block"), "blockGold");
	    multiKeywordToOreDict.put(Arrays.asList("iron", "block"), "blockIron");
	    multiKeywordToOreDict.put(Arrays.asList("lapis", "block"), "blockLapis");
	    multiKeywordToOreDict.put(Arrays.asList("redstone", "block"), "blockRedstone");
	    multiKeywordToOreDict.put(Arrays.asList("emerald", "block"), "blockEmerald");
	    multiKeywordToOreDict.put(Arrays.asList("quartz", "block"), "blockQuartz");
	    multiKeywordToOreDict.put(Arrays.asList("coal", "block"), "blockCoal");
	    multiKeywordToOreDict.put(Arrays.asList("karnite", "block"), "blockKarnite");
	    multiKeywordToOreDict.put(Arrays.asList("beskar", "block"), "blockBeskar");

	    // Prioritized Single-word keyword matches
	    keywordToOreDict.put("cobblestone", "cobblestone");
	    keywordToOreDict.put("sandstone", "sandstone");
	    keywordToOreDict.put("sand", "sand");
	    keywordToOreDict.put("mud", "mud");
	    keywordToOreDict.put("ash", "blockAsh");
	    keywordToOreDict.put("salt", "blockSalt");
	    keywordToOreDict.put("log", "logWood");
	    keywordToOreDict.put("plank", "plankWood");
	    keywordToOreDict.put("sapling", "treeSapling");
	    keywordToOreDict.put("leaves", "treeLeaves");
	    keywordToOreDict.put("glass", "blockGlass");
	    keywordToOreDict.put("obsidian", "obsidian");
	    keywordToOreDict.put("rock", "stone");
	    keywordToOreDict.put("slate", "stone");
	    keywordToOreDict.put("stone", "stone"); // Keep this last so "cobblestone" gets priority

	    // Iterate over registered blocks and categorize them
	    for (String blockName : registeredBlocks.keySet()) {
	        String lowerName = blockName.toLowerCase();

	        // Check if the block should be excluded
	        if (excludedKeywords.stream().anyMatch(lowerName::contains)) {
	            continue; // Skip this block if it contains an excluded keyword
	        }

	        boolean registered = false;

	        // First, check for multi-keyword matches (ALL words must be in the block name)
	        for (Map.Entry<List<String>, String> entry : multiKeywordToOreDict.entrySet()) {
	            List<String> keywords = entry.getKey();
	            if (keywords.stream().allMatch(lowerName::contains)) { // Ensure all words are present
	                Block block = BlockRegister.getRegisteredBlock(blockName);
	                if (block != null) {
	                    OreDictionary.registerOre(entry.getValue(), block);
	                    registered = true;
	                }
	                break; // Stop further checks for this block after registering
	            }
	        }

	        // If no multi-keyword match, try single-word matches
	        if (!registered) {
	            for (Map.Entry<String, String> entry : keywordToOreDict.entrySet()) {
	                if (lowerName.contains(entry.getKey())) {
	                    Block block = BlockRegister.getRegisteredBlock(blockName);
	                    if (block != null) {
	                        OreDictionary.registerOre(entry.getValue(), block);
	                    }
	                    break; // Stop after first match
	                }
	            }
	        }
	    }
	}


}
