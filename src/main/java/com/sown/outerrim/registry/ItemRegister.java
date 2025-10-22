/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemFood
 */
package com.sown.outerrim.registry;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import com.sown.outerrim.OuterRim;
import com.sown.outerrim.blocks.BlockCustomCrops;
import com.sown.outerrim.entities.EntityLaserProjectile;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import com.sown.outerrim.items.ItemCoaxiumRaw;
import com.sown.outerrim.items.ItemCoaxiumVial;
import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.items.ItemCoaxiumVialUnstableMax;
import com.sown.outerrim.items.ItemCoaxiumVialUnstableMedium;
import com.sown.outerrim.items.ItemCountDookuArmor;
import com.sown.outerrim.items.ItemCustom;
import com.sown.outerrim.items.ItemCustomArmor;
import com.sown.outerrim.items.ItemCustomAxe;
import com.sown.outerrim.items.ItemCustomBlaster;
import com.sown.outerrim.items.ItemCustomBow;
import com.sown.outerrim.items.ItemCustomDrink;
import com.sown.outerrim.items.ItemCustomHoe;
import com.sown.outerrim.items.ItemCustomHyperdrive;
import com.sown.outerrim.items.ItemCustomModelArmor;
import com.sown.outerrim.items.ItemCustomPickaxe;
import com.sown.outerrim.items.ItemCustomRecord;
import com.sown.outerrim.items.ItemCustomShovel;
import com.sown.outerrim.items.ItemCustomSword;
import com.sown.outerrim.items.ItemDarthVaderArmor;
import com.sown.outerrim.items.ItemEmptyVial;
import com.sown.outerrim.items.ItemInquisitorArmor;
import com.sown.outerrim.items.ItemKesselMineWorkerArmor;
import com.sown.outerrim.items.ItemSmugglerHanSoloArmor;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ItemRegister {
    private static final Map<String, Item> registeredItems = new HashMap<>();
    
    public static List<Item> tools = new ArrayList<Item>();
    public static List<Item> weapons = new ArrayList<Item>();
    public static List<Item> hyperdrives = new ArrayList<Item>();
    public static List<Item> materials = new ArrayList<Item>();
    public static List<Item> misc = new ArrayList<Item>();
    public static List<Item> food = new ArrayList<Item>();
    public static List<Item> story = new ArrayList<Item>();
    public static List<Item> spawning = new ArrayList<Item>();
    public static List<Item> deco = new ArrayList<Item>();
    public static List<Item> glassItems = new ArrayList<>();
    
    public static void registerAll() {

        // Items
        registerItem("vial", materials);
        registerItem("coaxiumVial", materials);
        registerItem("coaxiumVolatile2", materials);
        registerItem("coaxiumVolatile1", materials);
        registerItem("coaxiumRaw", materials);
        
        registerItem("alazhi", materials);
        
        /* NEW coaxium set */
        reg("vialEmpty", ItemRegister.misc, new Item().setMaxStackSize(16));
        reg("vialCoaxiumRaw",ItemRegister.misc,     new ItemCoaxiumVialRaw());       // volatile
        reg("vialCoaxiumRefined", ItemRegister.misc,new Item().setMaxStackSize(1));                     // safe, sellable

        //registerItem("beskarIngot", materials);
        //registerItem("durasteelIngot", materials);
        //registerItem("mandalorianIronIngot", materials);
        //registerItem("cortosisIngot", materials);
        //registerItem("zersiumIngot", materials);
        registerItem("galactic_credit_1", misc);
        registerItem("galactic_credit_10", misc);
        registerItem("galactic_credit_100", misc);
        registerItem("galactic_credit_1000", misc);
        //registerItem("darkTreeSapling", deco);
        registerItem("deathStarPlans", story);
        //registerItem("deathStickRed", misc);
        //registerItem("deathStickYellow", misc);
        //registerItem("beskar_crucible_tongs", misc);
        //registerItem("glowing_ingot", materials);
        //registerItem("beskar_stick", misc);
        //registerItem("glowing_beskar_stick", misc);
        registerItem("glass", food);
        //registerItem("goblet", food);
        //registerItem("japorIvorySapling", deco);
        registerItem("karniteIngot", materials);
        registerItem("kavam", materials);
        //registerWeapon("beskarSpear", "Sword", 250, 14, 6.0F);;
        //registerItem("mug", food);
        registerItem("spawn_egg");
        //registerItem("trinititeCrystal", materials);
        //registerItem("kessel_spice", materials);
        //registerItem("kessel_spice_jar", materials);
        registerItem("vratixia_renanicus", materials);
        //registerItem("jar", misc);
        //registerItem("beskar_crucible_tongs", tools);
        registerItem("caf_beans", food);
        registerItem("ground_caf_beans", food);
        registerItem("ice_cream_cone", food);
        //registerArmor("rebreather", "Helmet", 500, new int[] {2, 0, 0, 0}, 15, Items.iron_ingot, misc);
        registerFood("raw_nuna_leg", 2, 0.3F, false);
        registerFood("cooked_nuna_leg", 6, 0.8F, false);
        registerFood("colo_claw_roe", 4, 0.5F, false);
        registerFood("blue_milk_cheese", 5, 0.6F, false);
        registerFood("frog", 2, 0.3F, false);
        registerFood("duracrete_slug", 2, 0.3F, false);
        registerFood("duraslug_roll", 7, 0.9F, false);
        registerFood("meiloorun_fruit", 4, 0.6F, false);
        registerFood("ice_cream", 3, 0.5F, false);
        registerFood("rootleaf_stew", 8, 1.0F, false);
        registerFood("garden_salad", 5, 0.6F, false);
        registerFood("duriak", 4, 0.5F, false);
        
        registerItem("republic_crest");
        registerItem("imperial_crest");
        registerItem("separatist_hex_icon");
        registerItem("rebel_alliance_starbird");
        registerItem("first_order_insignia");
        registerItem("jedi_crest");

        
        // Drinks
        registerDrink("ardees", 0, 0.0f, true, true, true, false, null);
        registerDrink("blueMilk", 0, 0f, true, true, true, true, null);
        registerDrink("blueTonic", 0, 0f, true, true, true, false, null);
        registerDrink("greenMilk", 0, 0f, true, true, true, true, null);
        registerDrink("milk", 0, 0f, true, true, false, true, null);
        registerDrink("reactorCore", 0, 0f, true, true, true, false, null);
        registerDrink("redWine", 0, 0f, true, true, true, false, null);
        registerDrink("spiceLiqueur", 0, 0f, true, true, true, false, null);
        registerDrink("vodka", 0, 0f, true, true, true, false, null);
        registerDrink("water", 0, 0f, false, true, false, false, null);
        registerDrink("whiteWine", 0, 0f, true, true, true, false, null);
        registerDrink("spotchka", 0, 0f, true, true, true, false, null);
        registerDrink("caf", 0, 0f, true, true, true, false, null);

        // Hyperdrives
        registerHyperdrive("Abednedo");
        registerHyperdrive("AhchTo");
        registerHyperdrive("AjanKloss");
        registerHyperdrive("Alderaan");
        registerHyperdrive("Anoat");
        registerHyperdrive("Arvala7");
        registerHyperdrive("Athulla");
        registerHyperdrive("Aurea");
        registerHyperdrive("Bahryn");
        registerHyperdrive("Bakura");
        registerHyperdrive("Balosar");
        registerHyperdrive("Batuu");
        registerHyperdrive("Bespin");
        registerHyperdrive("Bestine");
        registerHyperdrive("Bogano");
        registerHyperdrive("Bothawui");
        registerHyperdrive("Bracca");
        registerHyperdrive("Byss");
        registerHyperdrive("Cantonica");
        registerHyperdrive("Carida");
        registerHyperdrive("Cato Neimoidia");
        registerHyperdrive("Chandrila");
        registerHyperdrive("Cholganna");
        registerHyperdrive("Christophsis");
        registerHyperdrive("Concord Dawn");
        registerHyperdrive("Concordia");
        registerHyperdrive("Corellia");
        registerHyperdrive("Corfai");
        registerHyperdrive("Coruscant");
        registerHyperdrive("Crait");
        registerHyperdrive("Csilla");
        registerHyperdrive("Dagobah");
        registerHyperdrive("Dantooine");
        registerHyperdrive("Dathomir");
        registerHyperdrive("DeathStar");
        registerHyperdrive("Devaron");
        registerHyperdrive("Dosuun");
        registerHyperdrive("DQar");
        registerHyperdrive("Drall");
        registerHyperdrive("Dromund Kaas");
        registerHyperdrive("Duro");
        registerHyperdrive("Eadu");
        registerHyperdrive("Earth");
        registerHyperdrive("Empress Teta");
        registerHyperdrive("Endor");
        registerHyperdrive("Exegol");
        registerHyperdrive("Felucia");
        registerHyperdrive("Florrum");
        registerHyperdrive("Fondor");
        registerHyperdrive("Formos");
        registerHyperdrive("Froz");
        registerHyperdrive("Gamorr");
        registerHyperdrive("Geonosis");
        registerHyperdrive("Hapes");
        registerHyperdrive("Hosnian Prime");
        registerHyperdrive("Hoth");
        registerHyperdrive("Hurikane");
        registerHyperdrive("Iego");
        registerHyperdrive("Ilum");
        registerHyperdrive("Jakku");
        registerHyperdrive("Jedha");
        //registerHyperdrive("Jestefad");
        registerHyperdrive("Kamino");
        registerHyperdrive("Kashyyyk");
        registerHyperdrive("KefBir");
        registerHyperdrive("Kessel");
        registerHyperdrive("Kijimi");
        registerHyperdrive("Korriban");
        registerHyperdrive("Krownest");
        registerHyperdrive("Kuat");
        registerHyperdrive("Lahmu");
        registerHyperdrive("Lothal");
        registerHyperdrive("Malachor");
        registerHyperdrive("Malastare");
        registerHyperdrive("Manaan");
        registerHyperdrive("Mandalore");
        registerHyperdrive("Mimban");
        registerHyperdrive("Mon Calamari");
        registerHyperdrive("Mortis");
        registerHyperdrive("Mustafar");
        registerHyperdrive("Mygeeto");
        registerHyperdrive("Naboo");
        registerHyperdrive("NalHutta");
        registerHyperdrive("Nevarro");
        registerHyperdrive("Niamos");
        registerHyperdrive("Nubia");
        registerHyperdrive("Numidian Prime");
        registerHyperdrive("Nur");
        registerHyperdrive("Onderon");
        registerHyperdrive("Ord Mantell");
        registerHyperdrive("Pasaana");
        registerHyperdrive("Pillio");
        registerHyperdrive("Polis Massa");
        registerHyperdrive("Rakata Prime");
        registerHyperdrive("Raxus Prime");
        registerHyperdrive("Rhenvar");
        registerHyperdrive("Riflor");
        registerHyperdrive("Rodia");
        registerHyperdrive("Ryloth");
        registerHyperdrive("Sacorria");
        registerHyperdrive("Saleucami");
        registerHyperdrive("Savareen");
        registerHyperdrive("Scarif");
        registerHyperdrive("Selonia");
        registerHyperdrive("Sorgan");
        registerHyperdrive("Sullust");
        registerHyperdrive("Takodana");
        registerHyperdrive("Talus");
        registerHyperdrive("Taris");
        registerHyperdrive("Tatooine");
        registerHyperdrive("Telos IV");
        registerHyperdrive("Tralus");
        registerHyperdrive("Trandosha");
        registerHyperdrive("Tython");
        registerHyperdrive("Umbara");
        registerHyperdrive("Utapau");
        registerHyperdrive("Vagran");
        registerHyperdrive("Vandor");
        registerHyperdrive("Vjun");
        registerHyperdrive("Wobani");
        registerHyperdrive("WorldBetweenWorlds");
        registerHyperdrive("Xyquine II");
        registerHyperdrive("Yavin 4");
        registerHyperdrive("Zeffo");

        registerRecord("anakin_vs_obi_wan");
        registerRecord("battle_of_the_heroes");
        registerRecord("battle_over_coruscant");
        registerRecord("cantina_band");
        registerRecord("clone_army_march");
        registerRecord("droid_army_march");
        registerRecord("duel_of_the_fates");
        registerRecord("jedi_rocks");
        registerRecord("niamos");
        registerRecord("sugaan_essena");
        registerRecord("the_imperial_march");
        registerRecord("yub_nub");


        // Tools
        //registerToolSet("beskar", 1561, 10, ItemRegister.getRegisteredItem("beskarIngot"));
        registerToolSet("karnite", 1561, 10, ItemRegister.getRegisteredItem("karniteIngot"));
        registerWeapon("nightbrotherMallet", "Sword", 250, 14, 6.0F);
        registerWeapon("electricBaton", "Sword", 250, 14, 6.0F);
        registerCustomBlaster("blaster");

        //test
        registerStoryCustomModelArmorSet("japor_snippet", DIAMOND_DURABILITY, LEATHER_REDUCTION_AMOUNTS, LEATHER_ENCHANTABILITY, ItemRegister.getRegisteredItem("spawn_egg"), false, true, false, false);
        registerStoryCustomModelArmorSet("sith_eyes", DIAMOND_DURABILITY, DIAMOND_REDUCTION_AMOUNTS, DIAMOND_ENCHANTABILITY, ItemRegister.getRegisteredItem("spawn_egg"), true, false, false, false);
        
        // Armor sets
        //registerCustomModelArmorSet("beskar", DIAMOND_DURABILITY, DIAMOND_REDUCTION_AMOUNTS, DIAMOND_ENCHANTABILITY, ItemRegister.getRegisteredItem("beskarIngot"), true, true, true, true);
        //registerArmorSet("bounty_hunter", DIAMOND_DURABILITY, DIAMOND_REDUCTION_AMOUNTS, DIAMOND_ENCHANTABILITY, ItemRegister.getRegisteredItem("beskarIngot"));
        registerInquisitorArmorSet("inquisitor", DIAMOND_DURABILITY, DIAMOND_REDUCTION_AMOUNTS, DIAMOND_ENCHANTABILITY, ItemRegister.getRegisteredItem("spawn_egg"));
        registerArmorSet("karnite", DIAMOND_DURABILITY, DIAMOND_REDUCTION_AMOUNTS, DIAMOND_ENCHANTABILITY, ItemRegister.getRegisteredItem("karniteIngot"));
        registerCustomArmorSet("kessel_worker", 33, new int[]{2, 5, 4, 1}, 10, ItemRegister.getRegisteredItem("spawn_egg"), ItemKesselMineWorkerArmor.class);
        registerCustomArmorSet("smuggler_han_solo", 33, new int[]{2, 5, 4, 1}, 10, ItemRegister.getRegisteredItem("spawn_egg"), ItemSmugglerHanSoloArmor.class);
        registerCustomArmorSet("count_dooku", 33, new int[]{2, 5, 4, 1}, 10, ItemRegister.getRegisteredItem("spawn_egg"), ItemCountDookuArmor.class);
        registerCustomArmorSet("darth_vader", 33, new int[]{2, 5, 4, 1}, 10, ItemRegister.getRegisteredItem("spawn_egg"), ItemDarthVaderArmor.class);
    }
  
    // Getter method for registeredItems
    public static Map<String, Item> getRegisteredItems() {
        return Collections.unmodifiableMap(registeredItems);  // Return an unmodifiable view for safety
    }
    
    private static Item reg(String id, List<Item> list, Item item) {
        item.setUnlocalizedName("outerrim." + id)
            .setTextureName("outerrim:" + id)
            .setCreativeTab(list == ItemRegister.tools ? OuterRim.tabTools : OuterRim.tabMisc);

        GameRegistry.registerItem(item, id);
        list.add(item);

        /*  FIX: write to the real map, not the read-only view  */
        registeredItems.put(id, item);          // <- use the field directly
        return item;
    }

    public static Item registerItem(String name) {
        return registerItem(name, null);
    }
    
    public static Item registerItem(String name, List<Item> category) {
        if (registeredItems.containsKey(name)) {
            return registeredItems.get(name);
        } else {
            Item item = new ItemCustom(name);

            // Assign a default creative tab if it doesn’t have one
            if (category == materials) {
                item.setCreativeTab(OuterRim.tabMaterials);
            } else if (category == tools) {
                item.setCreativeTab(OuterRim.tabTools);
            } else if (category == weapons) {
                item.setCreativeTab(OuterRim.tabCombat);
            } else if (category == food) {
                item.setCreativeTab(OuterRim.tabFood);
            } else if (category == misc) {
                item.setCreativeTab(OuterRim.tabMisc);
            } else if (category == hyperdrives) {
                item.setCreativeTab(OuterRim.tabMisc);
            } else if (category == spawning) {
                item.setCreativeTab(OuterRim.tabMisc);
            } else if (category == story) {
                item.setCreativeTab(OuterRim.tabMisc);
            } else if (category == deco) {
                item.setCreativeTab(OuterRim.tabDeco);
            } else if (category == glassItems) {
                item.setCreativeTab(OuterRim.tabMisc);
            } else {
                item.setCreativeTab(CreativeTabs.tabMisc); // Default tab for unclassified items
            }

            GameRegistry.registerItem(item, name);
            registeredItems.put(name, item);

            // Add the item to the passed category (list) if provided
            if (category != null) {
                category.add(item);
            }

            return item;
        }
    }
    public static Item registerRecord(String name) {
        if (registeredItems.containsKey(name)) {
            return registeredItems.get(name);
        }

        // Derive the sound event from the name
        String soundEvent = "outerrim:records." + name; // Custom namespace and directory

        // Create the custom record item
        ItemCustomRecord record = new ItemCustomRecord(name, soundEvent);

        // Register the item
        GameRegistry.registerItem(record, name);
        registeredItems.put(name, record);
        misc.add(record); // Add to the misc list (or another category if appropriate)

        return record;
    }
    
    public static Item getRegisteredItem(String name) {
        return registeredItems.get(name);
    }

    public static void registerToolSet(String name, int durability, int enchantability, Item craftingIngot) {
        // Create and register tool material.
        Item.ToolMaterial material = EnumHelper.addToolMaterial(name, 3, durability, 8.0F, 3.0F, enchantability);
        
        // Create tools.
        Item shovel = new ItemCustomShovel(material)
                .setUnlocalizedName("outerrim." + name + "Shovel")
                .setTextureName("outerrim:" + name + "Shovel")
                .setCreativeTab(OuterRim.tabTools);
        registeredItems.put(name + "Shovel", shovel);
        tools.add(shovel);
        Item pickaxe = new ItemCustomPickaxe(material)
                .setUnlocalizedName("outerrim." + name + "Pickaxe")
                .setTextureName("outerrim:" + name + "Pickaxe")
                .setCreativeTab(OuterRim.tabTools);
        registeredItems.put(name + "Pickaxe", pickaxe);
        tools.add(pickaxe);
        Item axe = new ItemCustomAxe(material)
                .setUnlocalizedName("outerrim." + name + "Axe")
                .setTextureName("outerrim:" + name + "Axe")
                .setCreativeTab(OuterRim.tabTools);
        registeredItems.put(name + "Axe", axe);
        tools.add(axe);
        Item hoe = new ItemCustomHoe(material)
                .setUnlocalizedName("outerrim." + name + "Hoe")
                .setTextureName("outerrim:" + name + "Hoe")
                .setCreativeTab(OuterRim.tabTools);
        registeredItems.put(name + "Hoe", hoe);
        tools.add(hoe);
        Item sword = new ItemCustomSword(material)
                .setUnlocalizedName("outerrim." + name + "Sword")
                .setTextureName("outerrim:" + name + "Sword")
                .setCreativeTab(OuterRim.tabCombat);
        registeredItems.put(name + "Sword", sword);
        weapons.add(sword);
        
        // Register tools.
        GameRegistry.registerItem(shovel, name + "Shovel");
        GameRegistry.registerItem(pickaxe, name + "Pickaxe");
        GameRegistry.registerItem(axe, name + "Axe");
        GameRegistry.registerItem(hoe, name + "Hoe");
        GameRegistry.registerItem(sword, name + "Sword");
        
        // Register recipes
        GameRegistry.addShapedRecipe(new ItemStack(shovel, 1), new Object[]{" A ", " B ", " B ", Character.valueOf('A'), craftingIngot, Character.valueOf('B'), Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(pickaxe, 1), new Object[]{"AAA", " B ", " B ", Character.valueOf('A'), craftingIngot, Character.valueOf('B'), Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(axe, 1), new Object[]{"AA ", "AB ", " B ", Character.valueOf('A'), craftingIngot, Character.valueOf('B'), Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(hoe, 1), new Object[]{"AA ", " B ", " B ", Character.valueOf('A'), craftingIngot, Character.valueOf('B'), Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(sword, 1), new Object[]{" A ", " A ", " B ", Character.valueOf('A'), craftingIngot, Character.valueOf('B'), Items.stick});
    }

    // Define constants for the armor materials
    public static final ItemArmor.ArmorMaterial LEATHER = ItemArmor.ArmorMaterial.CLOTH;
    public static final ItemArmor.ArmorMaterial IRON = ItemArmor.ArmorMaterial.IRON;
    public static final ItemArmor.ArmorMaterial GOLD = ItemArmor.ArmorMaterial.GOLD;
    public static final ItemArmor.ArmorMaterial DIAMOND = ItemArmor.ArmorMaterial.DIAMOND;
    public static final ItemArmor.ArmorMaterial CHAINMAIL = ItemArmor.ArmorMaterial.CHAIN;

    // Durability variables for each armor type
    public static final int LEATHER_DURABILITY = 5;
    public static final int IRON_DURABILITY = 15;
    public static final int GOLD_DURABILITY = 7;
    public static final int DIAMOND_DURABILITY = 33;
    public static final int CHAINMAIL_DURABILITY = 12;

    // Enchantability variables for each armor type
    public static final int LEATHER_ENCHANTABILITY = 15;
    public static final int IRON_ENCHANTABILITY = 9;
    public static final int GOLD_ENCHANTABILITY = 25;
    public static final int DIAMOND_ENCHANTABILITY = 10;
    public static final int CHAINMAIL_ENCHANTABILITY = 12;

    // Reduction amounts variables for each armor type
    public static final int[] LEATHER_REDUCTION_AMOUNTS = new int[]{1, 2, 1, 1};
    public static final int[] IRON_REDUCTION_AMOUNTS = new int[]{2, 5, 3, 1};
    public static final int[] GOLD_REDUCTION_AMOUNTS = new int[]{2, 5, 3, 1};
    public static final int[] DIAMOND_REDUCTION_AMOUNTS = new int[]{3, 8, 6, 3};
    public static final int[] CHAINMAIL_REDUCTION_AMOUNTS = new int[]{2, 5, 4, 1};

    public static void registerArmorSet(String name, int durability, int[] reductionAmounts, int enchantability, Item craftingIngot) {
        ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);

        Item helmet = new ItemCustomArmor(material, 0, name + "Helmet");
        registeredItems.put(name + "Helmet", helmet);
        weapons.add(helmet);
        Item chestplate = new ItemCustomArmor(material, 1, name + "Chestplate");
        registeredItems.put(name + "Chestplate", chestplate);
        weapons.add(chestplate);
        Item leggings = new ItemCustomArmor(material, 2, name + "Leggings");
        registeredItems.put(name + "Leggings", leggings);
        weapons.add(leggings);
        Item boots = new ItemCustomArmor(material, 3, name + "Boots");
        registeredItems.put(name + "Boots", boots);
        weapons.add(boots);

        GameRegistry.registerItem(helmet, name + "Helmet");
        GameRegistry.registerItem(chestplate, name + "Chestplate");
        GameRegistry.registerItem(leggings, name + "Leggings");
        GameRegistry.registerItem(boots, name + "Boots");

        GameRegistry.addShapedRecipe(new ItemStack(helmet, 1), new Object[]{"AAA", "A A", "   ", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(chestplate, 1), new Object[]{"A A", "AAA", "AAA", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(leggings, 1), new Object[]{"AAA", "A A", "A A", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(boots, 1), new Object[]{"   ", "A A", "A A", 'A', craftingIngot});
    }

    public static void registerInquisitorArmorSet(String name, int durability, int[] reductionAmounts, int enchantability, Item craftingIngot) {
        ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);

        Item helmet = new ItemInquisitorArmor(material, 0, name + "Helmet");
        registeredItems.put(name + "Helmet", helmet);
        weapons.add(helmet);
        Item chestplate = new ItemInquisitorArmor(material, 1, name + "Chestplate");
        registeredItems.put(name + "Chestplate", chestplate);
        weapons.add(chestplate);
        Item leggings = new ItemInquisitorArmor(material, 2, name + "Leggings");
        registeredItems.put(name + "Leggings", leggings);
        weapons.add(leggings);
        Item boots = new ItemInquisitorArmor(material, 3, name + "Boots");
        registeredItems.put(name + "Boots", boots);
        weapons.add(boots);

        GameRegistry.registerItem(helmet, name + "Helmet");
        GameRegistry.registerItem(chestplate, name + "Chestplate");
        GameRegistry.registerItem(leggings, name + "Leggings");
        GameRegistry.registerItem(boots, name + "Boots");

        GameRegistry.addShapedRecipe(new ItemStack(helmet, 1), new Object[]{"AAA", "A A", "   ", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(chestplate, 1), new Object[]{"A A", "AAA", "AAA", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(leggings, 1), new Object[]{"AAA", "A A", "A A", 'A', craftingIngot});
        GameRegistry.addShapedRecipe(new ItemStack(boots, 1), new Object[]{"   ", "A A", "A A", 'A', craftingIngot});
    }
    
    public static void registerCustomArmorSet(
            String baseName,
            int durability,
            int[] reductionAmounts,
            int enchantability,
            Item craftingIngot,
            Class<? extends ItemArmor> armorItemClass
    ) {
        // 1) Create the EnumHelper ArmorMaterial
        ItemArmor.ArmorMaterial mat = EnumHelper.addArmorMaterial(
            baseName.toUpperCase(),
            durability,
            reductionAmounts,
            enchantability
        );

        try {
            // 2) Locate the (ArmorMaterial, int renderIndex, int armorType) constructor
            Constructor<? extends ItemArmor> ctor = armorItemClass.getConstructor(
                ItemArmor.ArmorMaterial.class,
                int.class,
                int.class
            );

            // 3) Instantiate each piece in turn.
            ItemArmor helmet   = ctor.newInstance(mat, 0, 0);
            ItemArmor chest    = ctor.newInstance(mat, 0, 1);
            ItemArmor leggings = ctor.newInstance(mat, 0, 2);
            ItemArmor boots    = ctor.newInstance(mat, 0, 3);

            // 4) Set unlocalized and texture names for INVENTORY icons!
            //    (These should match your .png names in assets/outerrim/textures/items/)
            String modid = "outerrim";
            helmet.setUnlocalizedName(modid + "." + baseName + "_helmet");
            helmet.setTextureName(modid + ":" + baseName + "_helmet");
            helmet.setCreativeTab(OuterRim.tabCombat);

            chest.setUnlocalizedName(modid + "." + baseName + "_chestplate");
            chest.setTextureName(modid + ":" + baseName + "_chestplate");
            chest.setCreativeTab(OuterRim.tabCombat);

            leggings.setUnlocalizedName(modid + "." + baseName + "_leggings");
            leggings.setTextureName(modid + ":" + baseName + "_leggings");
            leggings.setCreativeTab(OuterRim.tabCombat);

            boots.setUnlocalizedName(modid + "." + baseName + "_boots");
            boots.setTextureName(modid + ":" + baseName + "_boots");
            boots.setCreativeTab(OuterRim.tabCombat);

            // 5) Put them in your registeredItems map
            registeredItems.put(baseName + "_helmet",   helmet);
            registeredItems.put(baseName + "_chestplate", chest);
            registeredItems.put(baseName + "_leggings",  leggings);
            registeredItems.put(baseName + "_boots",     boots);

            // 6) Register each item with GameRegistry.
            GameRegistry.registerItem(helmet,   baseName + "_helmet");
            GameRegistry.registerItem(chest,    baseName + "_chestplate");
            GameRegistry.registerItem(leggings, baseName + "_leggings");
            GameRegistry.registerItem(boots,    baseName + "_boots");

            // 7) Add crafting recipes
            GameRegistry.addShapedRecipe(
                new ItemStack(helmet,   1),
                "AAA", "A A", "   ",
                'A', craftingIngot
            );
            GameRegistry.addShapedRecipe(
                new ItemStack(chest,    1),
                "A A", "AAA", "AAA",
                'A', craftingIngot
            );
            GameRegistry.addShapedRecipe(
                new ItemStack(leggings, 1),
                "AAA", "A A", "A A",
                'A', craftingIngot
            );
            GameRegistry.addShapedRecipe(
                new ItemStack(boots,    1),
                "   ", "A A", "A A",
                'A', craftingIngot
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerCustomModelArmorSet(
            String name, 
            int durability, 
            int[] reductionAmounts, 
            int enchantability, 
            Item craftingIngot,
            boolean registerHelmet, 
            boolean registerChestplate, 
            boolean registerLeggings, 
            boolean registerBoots) {
        
        ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);

        if (registerHelmet) {
            Item helmet = new ItemCustomModelArmor(material, 0, name + "Helmet").setCreativeTab(OuterRim.tabCombat);
            registeredItems.put(name + "Helmet", helmet);
            weapons.add(helmet);
            GameRegistry.registerItem(helmet, name + "Helmet");
            GameRegistry.addShapedRecipe(new ItemStack(helmet, 1), "AAA", "A A", "   ", 'A', craftingIngot);
        }

        if (registerChestplate) {
            Item chestplate = new ItemCustomModelArmor(material, 1, name + "Chestplate").setCreativeTab(OuterRim.tabCombat);
            registeredItems.put(name + "Chestplate", chestplate);
            weapons.add(chestplate);
            GameRegistry.registerItem(chestplate, name + "Chestplate");
            GameRegistry.addShapedRecipe(new ItemStack(chestplate, 1), "A A", "AAA", "AAA", 'A', craftingIngot);
        }

        if (registerLeggings) {
            Item leggings = new ItemCustomModelArmor(material, 2, name + "Leggings").setCreativeTab(OuterRim.tabCombat);
            registeredItems.put(name + "Leggings", leggings);
            weapons.add(leggings);
            GameRegistry.registerItem(leggings, name + "Leggings");
            GameRegistry.addShapedRecipe(new ItemStack(leggings, 1), "AAA", "A A", "A A", 'A', craftingIngot);
        }

        if (registerBoots) {
            Item boots = new ItemCustomModelArmor(material, 3, name + "Boots").setCreativeTab(OuterRim.tabCombat);
            registeredItems.put(name + "Boots", boots);
            weapons.add(boots);
            GameRegistry.registerItem(boots, name + "Boots");
            GameRegistry.addShapedRecipe(new ItemStack(boots, 1), "   ", "A A", "A A", 'A', craftingIngot);
        }
    }

    public static void registerStoryCustomModelArmorSet(
            String name, 
            int durability, 
            int[] reductionAmounts, 
            int enchantability, 
            Item craftingIngot,
            boolean registerHelmet, 
            boolean registerChestplate, 
            boolean registerLeggings, 
            boolean registerBoots) {
        
        // Create and register the armor material
        ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);

        if (registerHelmet) {
            Item helmet = new ItemCustomModelArmor(material, 0, name + "Helmet");
            registeredItems.put(name + "Helmet", helmet);
            story.add(helmet);
            GameRegistry.registerItem(helmet, name + "Helmet");
            GameRegistry.addShapedRecipe(new ItemStack(helmet, 1), "AAA", "A A", "   ", 'A', craftingIngot);
        }

        if (registerChestplate) {
            Item chestplate = new ItemCustomModelArmor(material, 1, name + "Chestplate");
            registeredItems.put(name + "Chestplate", chestplate);
            story.add(chestplate);
            GameRegistry.registerItem(chestplate, name + "Chestplate");
            GameRegistry.addShapedRecipe(new ItemStack(chestplate, 1), "A A", "AAA", "AAA", 'A', craftingIngot);
        }

        if (registerLeggings) {
            Item leggings = new ItemCustomModelArmor(material, 2, name + "Leggings");
            registeredItems.put(name + "Leggings", leggings);
            story.add(leggings);
            GameRegistry.registerItem(leggings, name + "Leggings");
            GameRegistry.addShapedRecipe(new ItemStack(leggings, 1), "AAA", "A A", "A A", 'A', craftingIngot);
        }

        if (registerBoots) {
            Item boots = new ItemCustomModelArmor(material, 3, name + "Boots");
            registeredItems.put(name + "Boots", boots);
            story.add(boots);
            GameRegistry.registerItem(boots, name + "Boots");
            GameRegistry.addShapedRecipe(new ItemStack(boots, 1), "   ", "A A", "A A", 'A', craftingIngot);
        }
    }

    public static void registerCustomBlaster(String blasterName) {
        Item customBlaster = new ItemCustomBlaster();
        customBlaster.setUnlocalizedName(blasterName);
        customBlaster.setTextureName("outerrim:" + blasterName);
        GameRegistry.registerItem(customBlaster, blasterName);
        weapons.add(customBlaster);
        registeredItems.put(blasterName, customBlaster);
    }

    public static Item registerHyperdrive(String planetName) {
        // Remove spaces from the planet name
        String formattedName = planetName.replace(" ", "");

        // Create the hyperdrive item with the corrected name
        Item hyperdrive = new ItemCustomHyperdrive(formattedName);
        
        // Concatenate "hyperdrive" with the formatted planet name
        String hyperdriveName = "hyperdrive" + formattedName;
        
        // Register the item in the game registry using the correct name
        GameRegistry.registerItem(hyperdrive, hyperdriveName);
        
        // Add to the hyperdrives list
        hyperdrives.add(hyperdrive);
        
        // Put the correctly formatted name into the registeredItems map
        registeredItems.put(hyperdriveName, hyperdrive);
        
        // Set the item's creative tab and texture
        hyperdrive.setCreativeTab(OuterRim.tabMisc);
        hyperdrive.setTextureName("outerrim:hyperdrives/" + hyperdriveName);
        
        // Return the registered hyperdrive item
        return hyperdrive;
    }
    
    public static Item registerArmor(String name, String armorType, int durability, int[] reductionAmounts, int enchantability, Item craftingIngot, List<Item> itemGroup) {
        if (registeredItems.containsKey(name + armorType)) return registeredItems.get(name + armorType);
        // Create and register armor material.
        ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);
        Item armor = null;
        int armorIndex = -1;
        switch (armorType) {
            case "Helmet":
                armorIndex = 0;
                break;
            case "Chestplate":
                armorIndex = 1;
                break;
            case "Leggings":
                armorIndex = 2;
                break;
            case "Boots":
                armorIndex = 3;
                break;
        }
        if (armorIndex != -1) {
            armor = new ItemCustomArmor(material, armorIndex, name + armorType).setUnlocalizedName("outerrim." + name + armorType).setTextureName("outerrim:" + name + armorType).setCreativeTab(OuterRim.tabCombat);
            GameRegistry.registerItem(armor, name + armorType);
            registeredItems.put(name + armorType, armor);
        }

        // If an item group is specified, add the armor to that group; otherwise, add it to the "weapons" group by default.
        if (itemGroup != null) {
            itemGroup.add(armor);
        } else {
            weapons.add(armor);
        }

        return armor;
    }
    
    public static Item registerWeapon(String name, String toolType, int durability, int enchantability, float damage) {
        if (registeredItems.containsKey(name + toolType)) return registeredItems.get(name + toolType);
        // Create and register tool material.
        Item.ToolMaterial material = EnumHelper.addToolMaterial(name, 3, durability, 8.0F, damage, enchantability);
        Item weapon = null;
        switch (toolType) {
            case "Sword":
                weapon = new ItemCustomSword(material).setUnlocalizedName("outerrim." + name).setTextureName("outerrim:" + name).setCreativeTab(OuterRim.tabCombat);
                break;
            case "Axe":
                weapon = new ItemCustomAxe(material).setUnlocalizedName("outerrim." + name).setTextureName("outerrim:" + name).setCreativeTab(OuterRim.tabTools);
                break;
            case "Bow":
                weapon = new ItemCustomBow().setUnlocalizedName("outerrim." + name).setTextureName("outerrim:" + name).setCreativeTab(OuterRim.tabCombat);
                break;
        }
        if (weapon != null) {
            GameRegistry.registerItem(weapon, name + toolType);
            registeredItems.put(name + toolType, weapon);
            weapons.add(weapon);
        }
        return weapon;
    }
    
    /** Registers all coaxium items at once:
     *   coaxiumRaw, coaxiumVialEmpty,
     *   coaxiumVial, coaxiumVialMed, coaxiumVialMax
     */
    public static void registerCoaxiumSet(String base) {
        // raw material
        Item raw = new ItemCoaxiumRaw();
        GameRegistry.registerItem(raw, base + "Raw");
        registeredItems.put(base + "Raw", raw);
        materials.add(raw);

        // empty container
        Item empty = new ItemEmptyVial();
        GameRegistry.registerItem(empty, base + "VialEmpty");
        registeredItems.put(base + "VialEmpty", empty);
        misc.add(empty);

        // normal, medium, and max instability vials
        Item normal = new ItemCoaxiumVial();
        GameRegistry.registerItem(normal, base + "Vial");
        registeredItems.put(base + "Vial", normal);
        misc.add(normal);

        Item med = new ItemCoaxiumVialUnstableMedium();
        GameRegistry.registerItem(med, base + "VialMed");
        registeredItems.put(base + "VialMed", med);
        misc.add(med);

        Item max = new ItemCoaxiumVialUnstableMax();
        GameRegistry.registerItem(max, base + "VialMax");
        registeredItems.put(base + "VialMax", max);
        misc.add(max);
    }
    
    public static Item registerFood(String name, int hunger, float saturation, boolean canBePlanted) {
        // Register the food item
        ItemFood food = (ItemFood) new ItemFood(hunger, saturation, false)
                .setUnlocalizedName("outerrim." + name)
                .setTextureName("outerrim:" + name)
                .setCreativeTab(OuterRim.tabFood);
        GameRegistry.registerItem(food, name);
        registeredItems.put(name, food);
        ItemRegister.food.add(food);

        if (canBePlanted) {
            // Register the corresponding plant block
        	BlockCustomCrops plant = new BlockCustomCrops(name);
            plant.setBlockName("outerrim." + name + "Plant")
                 .setBlockTextureName("outerrim:" + name + "Plant");
            GameRegistry.registerBlock(plant, name + "Plant");

            // Register the seeds item for planting the crop
            ItemSeeds seeds = (ItemSeeds) new ItemSeeds(plant, Blocks.farmland)
                    .setUnlocalizedName("outerrim." + name + "Seeds")
                    .setTextureName("outerrim:" + name + "Seeds")
                    .setCreativeTab(OuterRim.tabMaterials);
            GameRegistry.registerItem(seeds, name + "Seeds");

            // Adding planting recipe: 1 food item gives 1 seed item
            GameRegistry.addShapelessRecipe(new ItemStack(seeds, 1), food);
            
            // Modify the custom block to drop the right seeds and food
            plant.setCustomDrops(seeds, food);
        }

        return food;
    }
    
    public static Item registerDrink(String name, int hunger, float saturation, boolean hasBottle, boolean hasGlass, boolean hasBucket, boolean removesPotions, PotionEffect... effects) {
        List<String> containerTypes = new ArrayList<>();
        if (hasBottle) containerTypes.add("Bottle");
        if (hasGlass) containerTypes.add("Glass");
        // if (hasMug) containerTypes.add("Mug");
        if (hasBucket) containerTypes.add("Bucket");
        // if (hasGoblet) containerTypes.add("Goblet");

        // Default to an empty container type if none are specified
        if (containerTypes.isEmpty()) containerTypes.add("");

        ItemCustomDrink lastRegisteredItem = null;
        for (String containerType : containerTypes) {
            String finalName = name + containerType;
            ItemCustomDrink drink = new ItemCustomDrink(hunger, saturation);
            drink.setRemovesPotionEffects(removesPotions);
            drink.setUnlocalizedName("outerrim." + finalName);
            drink.setTextureName("outerrim:drinks/container/" + containerType.toLowerCase() + "/" + finalName);
            drink.setCreativeTab(OuterRim.tabFood);

            switch (containerType) {
                case "Bottle":
                    drink.setContainerItem(Items.glass_bottle);
                    break;
                case "Glass":
                    drink.setContainerItem(ItemRegister.getRegisteredItem("glass"));
                    break;
                // case "Goblet":
                //     drink.setContainerItem(ItemRegister.getRegisteredItem("goblet"));
                //     break;
                // case "Mug":
                //     drink.setContainerItem(ItemRegister.getRegisteredItem("mug"));
                //     break;
                case "Bucket":
                    drink.setContainerItem(Items.bucket);
                    break;
                default:
                    drink.setContainerItem(null);
                    break;
            }

            if (effects != null) {
                for (PotionEffect effect : effects) {
                    if (effect != null) {
                        drink.addPotionEffect(effect);
                    }
                }
            }

            GameRegistry.registerItem(drink, finalName);
            registeredItems.put(finalName, drink);
            food.add(drink);
            lastRegisteredItem = drink;
        }

        // Sort the food list to group by container type
        food.sort((item1, item2) -> {
            String name1 = item1.getUnlocalizedName().toLowerCase();
            String name2 = item2.getUnlocalizedName().toLowerCase();

            // Prioritize by container type: Glass < Bottle < Bucket
            if (name1.contains("glass") && !name2.contains("glass")) return -1;
            if (name2.contains("glass") && !name1.contains("glass")) return 1;
            if (name1.contains("bottle") && !name2.contains("bottle")) return -1;
            if (name2.contains("bottle") && !name1.contains("bottle")) return 1;
            if (name1.contains("bucket") && !name2.contains("bucket")) return -1;
            if (name2.contains("bucket") && !name1.contains("bucket")) return 1;

            // Fallback to alphabetical order
            return name1.compareTo(name2);
        });

        return lastRegisteredItem;
    }

}