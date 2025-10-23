package com.sown.outerrim.registry;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeRegister {
    public static void registerAll() {
        registerShapedRecipes();
        registerSmeltingRecipes();
        registerShapelessRecipes();
    }

    // Shaped recipes
    private static void registerShapedRecipes() {
    	
    	// Coaxium Update
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.getRegisteredItem("vialEmpty"), 1), " A ", " B ", " A ", 'A', Items.iron_ingot, 'B', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.getRegisteredItem("portable_coaxium_pump"), 1), "IRI", " V ", "IRI", 'I', Items.iron_ingot, 'R', Items.redstone, 'V', ItemRegister.getRegisteredItem("vialEmpty"));
    	
        //GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("beskar_block"), 1), new Object[]{"AAA", "AAA", "AAA", 'A', ItemRegister.getRegisteredItem("beskarIngot")});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("karnite_block"), 1), new Object[]{"AAA", "AAA", "AAA", 'A', ItemRegister.getRegisteredItem("karniteIngot")});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("karnitePillar"), 2), new Object[]{"A", "A", 'A', BlockRegister.getRegisteredBlock("karnite_block")});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.getRegisteredItem("karniteIngot"), 9), new Object[]{"A", 'A', BlockRegister.getRegisteredBlock("karnite_block")});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("mandaloreSandstone")), new Object[]{"##", "##", '#', BlockRegister.getRegisteredBlock("mandaloreSand")});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("massassiBricks"), 4), new Object[]{"##", "##", '#', BlockRegister.getRegisteredBlock("massassiRock")});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("archive_bookshelf")), new Object[]{"###", "BBB", "###", '#', Items.iron_ingot, 'B', Items.book});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("archive_bookshelf_2")), new Object[]{"###", "BBB", "###", '#', Items.iron_ingot, 'B', Items.book});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("archive_bookshelf_3")), new Object[]{"###", "BBB", "###", '#', Items.iron_ingot, 'B', Items.book});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("imperialFloorPanel")), new Object[]{"###", "#I#", "###", '#', Items.iron_ingot, 'I', Blocks.stone});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("deathStarWallPanel1")), new Object[]{"###", "#I#", "###", '#', Blocks.stonebrick, 'I', Items.iron_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("deathStarWallPanel2")), new Object[]{"###", "#I#", "###", '#', Blocks.stonebrick, 'I', Items.iron_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("deathStarWallPanel3")), new Object[]{"###", "#I#", "###", '#', Blocks.stonebrick, 'I', Items.iron_ingot});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("fortInquisFloorPanel")), new Object[]{"III", "IFI", "III", 'I', Items.iron_ingot, 'F', Blocks.redstone_block});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("fortInquisWallPanel")), new Object[]{"IRI", "RFR", "IRI", 'I', Items.iron_ingot, 'R', Items.redstone, 'F', Blocks.stonebrick});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("kesselMachinery")), new Object[]{"III", "IRI", "III", 'I', Items.iron_ingot, 'R', Items.redstone});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("kesselMachineryPanel")), new Object[]{"IRI", "III", "IRI", 'I', Items.iron_ingot, 'R', Items.redstone});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("tatooineAwning")), new Object[]{"WWW", "SSS", 'W', Blocks.wool, 'S', Items.stick});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("tatooineSandstone")), new Object[]{"##", "##", '#', BlockRegister.getRegisteredBlock("tatooineSand1")});

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.getRegisteredBlock("temple_brick"), 4), new Object[]{"##", "##", '#', Blocks.stonebrick});
    }

    // Smelting recipes
    private static void registerSmeltingRecipes() {
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("karniteOre"), new ItemStack(ItemRegister.getRegisteredItem("karniteIngot")), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("braccaCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("braccaRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("geonosisCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("geonosisRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("ilumRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("mustafarCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("mustafarRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("tatooineCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("tatooineRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("dathomirCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("dathomirRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("massassiCobblestone"), new ItemStack(BlockRegister.getRegisteredBlock("massassiRock"), 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("mandaloreSand"), new ItemStack(Blocks.glass, 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("geonosisSand"), new ItemStack(Blocks.glass, 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("tatooineSand1"), new ItemStack(Blocks.glass, 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("tatooineSand2"), new ItemStack(Blocks.glass, 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("tatooineSand3"), new ItemStack(Blocks.glass, 1), 0.2f);
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("tatooineSand4s"), new ItemStack(Blocks.glass, 1), 0.2f);

        // Kessel ores
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselCoalOre"), new ItemStack(Items.coal), 0.1f); // Coal
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselDiamondOre"), new ItemStack(Items.diamond), 1.0f); // Diamond
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselEmeraldOre"), new ItemStack(Items.emerald), 1.0f); // Emerald
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselGoldOre"), new ItemStack(Items.gold_ingot), 1.0f); // Gold Ingot
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselIronOre"), new ItemStack(Items.iron_ingot), 0.7f); // Iron Ingot
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselLapisOre"), new ItemStack(Items.dye, 1, 4), 0.2f); // Lapis Lazuli
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselQuartzOre"), new ItemStack(Items.quartz), 0.2f); // Nether Quartz
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("kesselRedstoneOre"), new ItemStack(Items.redstone, 1), 0.7f); // Redstone

        // Ilum ores
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumCoalOre"), new ItemStack(Items.coal), 0.1f); // Coal
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumDiamondOre"), new ItemStack(Items.diamond), 1.0f); // Diamond
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumEmeraldOre"), new ItemStack(Items.emerald), 1.0f); // Emerald
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumGoldOre"), new ItemStack(Items.gold_ingot), 1.0f); // Gold Ingot
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumIronOre"), new ItemStack(Items.iron_ingot), 0.7f); // Iron Ingot
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumLapisOre"), new ItemStack(Items.dye, 1, 4), 0.2f); // Lapis Lazuli
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumQuartzOre"), new ItemStack(Items.quartz), 0.2f); // Nether Quartz
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("ilumRedstoneOre"), new ItemStack(Items.redstone, 1), 0.7f); // Redstone
        
        GameRegistry.addSmelting(BlockRegister.getRegisteredBlock("mandaloreSand"), new ItemStack(Blocks.glass), 0.2f);
    }

    // Shapeless recipes
    private static void registerShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(GameRegistry.findItem("outerrim", "liquid_bacta_bucket"), 1), new ItemStack(ItemRegister.getRegisteredItem("alazhi")), new ItemStack(ItemRegister.getRegisteredItem("kavam")), new ItemStack(ItemRegister.getRegisteredItem("vratixia_renanicus")), new ItemStack(GameRegistry.findItem("outerrim", "liquid_ambori_bucket")));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.getRegisteredItem("vratixia_renanicus"), 3), Items.wheat, Items.reeds);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.getRegisteredItem("alazhi"), 2), Items.milk_bucket, new ItemStack(Items.dye, 1, 15), Blocks.brown_mushroom);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.getRegisteredItem("kavam"), 1), Items.quartz, Items.diamond);
        
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegister.getRegisteredBlock("massassiBricksMossy")), BlockRegister.getRegisteredBlock("massassiBricks"), Blocks.vine);

        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegister.getRegisteredBlock("zeffoSlateMossy")), BlockRegister.getRegisteredBlock("zeffoSlate"), Blocks.vine);
    }
}