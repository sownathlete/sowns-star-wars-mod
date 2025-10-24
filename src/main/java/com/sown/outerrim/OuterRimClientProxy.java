package com.sown.outerrim;

import com.sown.outerrim.client.render.RenderPortableCoaxiumPump;
import com.sown.outerrim.entities.*;
import com.sown.outerrim.handlers.FrozenPlayerHandler;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.rendering.RenderItemPortableCoaxiumPump;
import com.sown.outerrim.rendering.RenderItemCarbonite;
import com.sown.outerrim.rendering.RenderItemCoaxiumContainer;
import com.sown.outerrim.rendering.RenderItemCoaxiumPump;
import com.sown.outerrim.rendering.RenderItemCoaxiumRefinery;
import com.sown.outerrim.rendering.RenderItemFeluciaFlowerTallTurquoise;
import com.sown.outerrim.rendering.RenderItemHoloProjector;
import com.sown.outerrim.rendering.RenderItemMoistureVaporator;
import com.sown.outerrim.rendering.RenderItemVenatorScreen;
import com.sown.outerrim.rendering.RenderPortableCoaxiumExtractorTESR;
import com.sown.outerrim.rendering.ItemRendererPortableCoaxiumExtractor;
import com.sown.outerrim.rendering.RenderBlockCarbonite;
import com.sown.outerrim.rendering.RenderBlockCoaxiumPump;
import com.sown.outerrim.rendering.RenderBlockCoaxiumRack;
import com.sown.outerrim.rendering.RenderBlockCoaxiumRefinery;
import com.sown.outerrim.rendering.RenderBlockFeluciaFlowerTallTurquoise;
import com.sown.outerrim.rendering.RenderBlockHoloProjector;
import com.sown.outerrim.rendering.RenderBlockMoistureVaporator;
import com.sown.outerrim.rendering.RenderBlockVenatorScreen;
import com.sown.outerrim.tileentities.TileEntityCarbonite;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;
import com.sown.outerrim.tileentities.TileEntityHoloProjector;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityVenatorScreen;

import com.sown.outerrim.handlers.DimensionEntrySoundHandler;
import com.sown.outerrim.items.InquisitorHelmetLayerHandler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public class OuterRimClientProxy extends OuterRimCommonProxy {
    public static OuterRimClientProxy INSTANCE;

    public static Minecraft mc;

    public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

    public static void setupArmorModels() {
        INSTANCE = new OuterRimClientProxy();
    }

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        System.setProperty("fml.skipFirstTextureLoad", "false");
        OuterRimClientProxy.setupArmorModels();
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new FrozenPlayerHandler());
        FMLCommonHandler.instance().bus().register(new DimensionEntrySoundHandler());
        new InquisitorHelmetLayerHandler();
        mc = Minecraft.getMinecraft();
        // also set the shared client reference used elsewhere (e.g., MessageSpawnClientParticle)
        OuterRim.mc = mc;
        registerRendering();
    }

    public OuterRimClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void registerRendering() {
        //RenderingRegistry.registerEntityRenderingHandler(EntityBlasterVariableBolt.class, new RenderBlasterBolt(GLPalette.BRIGHT_RED));
        //MinecraftForgeClient.registerItemRenderer((Item)ItemRegister.blasterPistol, new RenderBlasterPistol());
        RenderingRegistry.registerEntityRenderingHandler(MobCoruscantCommoner.class, new RenderCoruscantCommoner(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobTatooineCommoner.class, new RenderTatooineCommoner(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobSteve.class, new RenderSteve(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooperP1.class, new RenderCloneTrooperP1(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooperPhase2.class, new RenderCloneTrooperPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper501stPhase2.class, new RenderCloneTrooper501stPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper212thPhase2.class, new RenderCloneTrooper212thPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper41stPhase2.class, new RenderCloneTrooper41stPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobAlex.class, new RenderAlex(new ModelHumanSlim(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobInquisitor.class, new RenderInquisitor(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityZabrak.class, new RenderZabrak(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityNightsister.class, new RenderNightsister(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobBantha.class, new RenderBantha());
        RenderingRegistry.registerEntityRenderingHandler(MobTest.class, new RenderQuadrupedAnimal(new ModelQuadrupedAnimal(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MobBogRat.class, new RenderBogRat(new ModelBogRat(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, new RenderJabba(new ModelJabba(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobYoda.class, new RenderYoda(new ModelYoda(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityKaadu.class, new RenderKaadu(new ModelKaadu(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFalumpaset.class, new RenderFalumpaset(new ModelFalumpaset(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEwok.class, new RenderEwok(new ModelEwok(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityWookiee.class, new RenderWookiee());
        RenderingRegistry.registerEntityRenderingHandler(EntityPykeSentinel.class, new RenderPykeSentinel());
        RenderingRegistry.registerEntityRenderingHandler(EntityKesselMineWorker.class, new RenderKesselMineWorker(new ModelKesselMineWorker(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCountDooku.class, new RenderCountDooku());
        RenderingRegistry.registerEntityRenderingHandler(EntityDarthVader.class, new RenderDarthVader());
        RenderingRegistry.registerEntityRenderingHandler(EntitySmugglerHanSolo.class, new RenderSmugglerHanSolo(new ModelSmugglerHanSolo(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGM12L1.class, new RenderGM12L1(new ModelGM12L1(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityPelikki.class, new RenderPelikki(new ModelPelikki(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBattleDroid.class, new RenderBattleDroid(new ModelBattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityB2BattleDroid.class, new RenderB2BattleDroid(new ModelB2BattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityRustyB2BattleDroid.class, new RenderRustyB2BattleDroid(new ModelB2BattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityDroideka.class, new RenderDroideka());
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper.class, new RenderReconTrooper(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper501st.class, new RenderReconTrooper501st(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper212th.class, new RenderReconTrooper212th(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper41st.class, new RenderReconTrooper41st(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper187th.class, new RenderReconTrooper187th(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooperCoruscantGuard.class, new RenderReconTrooperCoruscantGuard(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTuskenRaider.class, new RenderTuskenRaiderMelee(new ModelTuskenRaiderMelee(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMimbaneseSoldier.class, new RenderMimbaneseSoldier());
        RenderingRegistry.registerEntityRenderingHandler(EntityAstromech.class, new RenderAstromech());
        RenderingRegistry.registerEntityRenderingHandler(EntitySandBeast.class, new RenderSandBeast(new ModelSandBeast(), 0.5f));
        registerCloneTrooperRenderer(MobCloneTrooper187th.class, "187th");
        registerCloneTrooperRenderer(MobCloneTrooperCoruscantGuard.class, "coruscant_guard");
        RenderingRegistry.registerEntityRenderingHandler(EntityCaptainRex.class, new RenderCaptainRex(new ModelCaptainRex(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserProjectile.class, new RenderLaserProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserProjectileRed.class, new RenderLaserProjectileRed());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("moisture_vaporator")), new RenderItemMoistureVaporator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMoistureVaporator.class, new RenderBlockMoistureVaporator());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_screen")), new RenderItemVenatorScreen());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVenatorScreen.class, new RenderBlockVenatorScreen());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("carbonite_block")), new RenderItemCarbonite());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarbonite.class, new RenderBlockCarbonite());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("holo_projector")), new RenderItemHoloProjector());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloProjector.class, new RenderBlockHoloProjector());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_container")), new RenderItemCoaxiumContainer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumContainer.class, new RenderBlockCoaxiumRack());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_refinery")), new RenderItemCoaxiumRefinery());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumRefinery.class, new RenderBlockCoaxiumRefinery());

        System.out.println("Found: "+BlockRegister.getRegisteredBlock("coaxium_pump"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_pump")), new RenderItemCoaxiumPump());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumPump.class, new RenderBlockCoaxiumPump());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("felucia_flower_tall_turquoise")), new RenderItemFeluciaFlowerTallTurquoise());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeluciaFlowerTurquoise.class, new RenderBlockFeluciaFlowerTallTurquoise());

        System.out.println("Found: "+BlockRegister.getRegisteredBlock("portable_coaxium_pump"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("portable_coaxium_pump")), new ItemRendererPortableCoaxiumExtractor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortableCoaxiumPump.class, new RenderPortableCoaxiumExtractorTESR());

//        // KaminoDoorLarge
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("kamino_door_large")),
//            new RenderBlockKaminoDoorLarge()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityKaminoDoorLarge.class,
//            new RenderKaminoDoorLarge()
//        );
//
//        // KaminoDoorSmall
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("kamino_door_small")),
//            new RenderBlockKaminoDoorSmall()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityKaminoDoorSmall.class,
//            new RenderKaminoDoorSmall()
//        );
//
//        // KaminoRailing
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("kamino_railing")),
//            new RenderBlockKaminoRailing()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityKaminoRailing.class,
//            new RenderKaminoRailing()
//        );
//
//        // VenatorBridgeChair
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_bridge_chair")),
//            new RenderBlockVenatorBridgeChair()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityVenatorBridgeChair.class,
//            new RenderVenatorBridgeChair()
//        );
//
//        // VenatorBridgeDoor
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_bridge_door")),
//            new RenderBlockVenatorBridgeDoor()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityVenatorBridgeDoor.class,
//            new RenderVenatorBridgeDoor()
//        );
//
//        // VenatorBridgeMechanicalTable
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_bridge_mechanical_table")),
//            new RenderBlockVenatorBridgeMechanicalTable()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityVenatorBridgeMechanicalTable.class,
//            new RenderVenatorBridgeMechanicalTable()
//        );
//
//        // VenatorHoloTable
//        MinecraftForgeClient.registerItemRenderer(
//            Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_holo_table")),
//            new RenderBlockVenatorHoloTable()
//        );
//        ClientRegistry.bindTileEntitySpecialRenderer(
//            TileEntityVenatorHoloTable.class,
//            new RenderVenatorHoloTable()
//        );
    }

    public static void registerCloneTrooperRenderer(Class<? extends EntityCloneTrooperBase> trooperClass, String battalionName) {
        RenderingRegistry.registerEntityRenderingHandler(trooperClass,
            new RenderCloneTrooper(new ModelCloneTrooperPhase2(), 0.5F, battalionName));
    }
}
