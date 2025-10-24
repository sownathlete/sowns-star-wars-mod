package com.sown.outerrim;

import com.sown.outerrim.client.render.RenderPortableCoaxiumPump;
import com.sown.outerrim.entities.*;
import com.sown.outerrim.handlers.FrozenPlayerHandler;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.rendering.ItemRendererPortableCoaxiumExtractor;
import com.sown.outerrim.rendering.ItemRendererPortableCoaxiumPump;
import com.sown.outerrim.rendering.RenderBlockCarbonite;
import com.sown.outerrim.rendering.RenderBlockCoaxiumContainer;
import com.sown.outerrim.rendering.RenderBlockCoaxiumExtractor;
import com.sown.outerrim.rendering.RenderBlockCoaxiumRefinery;
import com.sown.outerrim.rendering.RenderBlockFeluciaFlowerTallTurquoise;
import com.sown.outerrim.rendering.RenderBlockHoloProjector;
import com.sown.outerrim.rendering.RenderBlockKaminoDoorLarge;
import com.sown.outerrim.rendering.RenderBlockKaminoDoorSmall;
import com.sown.outerrim.rendering.RenderBlockKaminoRailing;
import com.sown.outerrim.rendering.RenderBlockMoistureVaporator;
import com.sown.outerrim.rendering.RenderBlockPortableCoaxiumExtractor;
import com.sown.outerrim.rendering.RenderBlockVenatorBridgeChair;
import com.sown.outerrim.rendering.RenderBlockVenatorBridgeDoor;
import com.sown.outerrim.rendering.RenderBlockVenatorBridgeMechanicalTable;
import com.sown.outerrim.rendering.RenderBlockVenatorHoloTable;
import com.sown.outerrim.rendering.RenderBlockVenatorScreen;
import com.sown.outerrim.rendering.RenderCarbonite;
import com.sown.outerrim.rendering.RenderCoaxiumExtractorTESR;
import com.sown.outerrim.rendering.RenderCoaxiumRack;
import com.sown.outerrim.rendering.RenderCoaxiumRefineryTESR;
import com.sown.outerrim.rendering.RenderFeluciaFlowerTallTurquoise;
import com.sown.outerrim.rendering.RenderHoloProjector;
import com.sown.outerrim.rendering.RenderKaminoDoorLarge;
import com.sown.outerrim.rendering.RenderKaminoDoorSmall;
import com.sown.outerrim.rendering.RenderKaminoRailing;
import com.sown.outerrim.rendering.RenderMoistureVaporator;
import com.sown.outerrim.rendering.RenderPortableCoaxiumExtractorTESR;
import com.sown.outerrim.rendering.RenderVenatorBridgeChair;
import com.sown.outerrim.rendering.RenderVenatorBridgeDoor;
import com.sown.outerrim.rendering.RenderVenatorBridgeMechanicalTable;
import com.sown.outerrim.rendering.RenderVenatorHoloTable;
import com.sown.outerrim.rendering.RenderVenatorScreen;
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

import com.sown.outerrim.handlers.DimensionEntrySoundHandler;
import com.sown.outerrim.items.InquisitorHelmetLayerHandler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;

import net.minecraftforge.client.IItemRenderer;
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
        //RenderingRegistry.registerEntityRenderingHandler(EntityBlasterVariableBolt.class, (Render)new RenderBlasterBolt(GLPalette.BRIGHT_RED));
        //MinecraftForgeClient.registerItemRenderer((Item)ItemRegister.blasterPistol, (IItemRenderer)new RenderBlasterPistol());
        RenderingRegistry.registerEntityRenderingHandler(MobCoruscantCommoner.class, (Render)new RenderCoruscantCommoner(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobTatooineCommoner.class, (Render)new RenderTatooineCommoner(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobSteve.class, (Render)new RenderSteve(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooperP1.class, (Render)new RenderCloneTrooperP1(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooperPhase2.class, new RenderCloneTrooperPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper501stPhase2.class, new RenderCloneTrooper501stPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper212thPhase2.class, new RenderCloneTrooper212thPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobCloneTrooper41stPhase2.class, new RenderCloneTrooper41stPhase2(new ModelCloneTrooperPhase2(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobAlex.class, (Render)new RenderAlex(new ModelHumanSlim(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobInquisitor.class, (Render)new RenderInquisitor(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityZabrak.class, (Render)new RenderZabrak(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityNightsister.class, (Render)new RenderNightsister(new ModelHuman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobBantha.class, (Render)new RenderBantha());
        RenderingRegistry.registerEntityRenderingHandler(MobTest.class, new RenderQuadrupedAnimal(new ModelQuadrupedAnimal(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MobBogRat.class, new RenderBogRat(new ModelBogRat(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, (Render)new RenderJabba(new ModelJabba(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MobYoda.class, (Render)new RenderYoda(new ModelYoda(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityKaadu.class, (Render)new RenderKaadu(new ModelKaadu(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFalumpaset.class, (Render)new RenderFalumpaset(new ModelFalumpaset(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEwok.class, (Render)new RenderEwok(new ModelEwok(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityWookiee.class, new RenderWookiee());
        RenderingRegistry.registerEntityRenderingHandler(EntityPykeSentinel.class, new RenderPykeSentinel());
        RenderingRegistry.registerEntityRenderingHandler(EntityKesselMineWorker.class, (Render)new RenderKesselMineWorker(new ModelKesselMineWorker(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCountDooku.class, (Render)new RenderCountDooku());
        RenderingRegistry.registerEntityRenderingHandler(EntityDarthVader.class, (Render)new RenderDarthVader());
        RenderingRegistry.registerEntityRenderingHandler(EntitySmugglerHanSolo.class, (Render)new RenderSmugglerHanSolo(new ModelSmugglerHanSolo(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGM12L1.class, new RenderGM12L1(new ModelGM12L1(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityPelikki.class, (Render)new RenderPelikki(new ModelPelikki(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBattleDroid.class, (Render)new RenderBattleDroid(new ModelBattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityB2BattleDroid.class, (Render)new RenderB2BattleDroid(new ModelB2BattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityRustyB2BattleDroid.class, (Render)new RenderRustyB2BattleDroid(new ModelB2BattleDroid(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityDroideka.class, (Render)new RenderDroideka());
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper.class, (Render)new RenderReconTrooper(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper501st.class, (Render)new RenderReconTrooper501st(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper212th.class, (Render)new RenderReconTrooper212th(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper41st.class, (Render)new RenderReconTrooper41st(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooper187th.class, (Render)new RenderReconTrooper187th(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReconTrooperCoruscantGuard.class, (Render)new RenderReconTrooperCoruscantGuard(new ModelReconTrooper(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTuskenRaider.class, (Render)new RenderTuskenRaiderMelee(new ModelTuskenRaiderMelee(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMimbaneseSoldier.class, (Render)new RenderMimbaneseSoldier());
        RenderingRegistry.registerEntityRenderingHandler(EntityAstromech.class, (Render)new RenderAstromech());
        RenderingRegistry.registerEntityRenderingHandler(EntitySandBeast.class, (Render)new RenderSandBeast(new ModelSandBeast(), 0.5f));
        registerCloneTrooperRenderer(MobCloneTrooper187th.class, "187th");
        registerCloneTrooperRenderer(MobCloneTrooperCoruscantGuard.class, "coruscant_guard");
        RenderingRegistry.registerEntityRenderingHandler(EntityCaptainRex.class, (Render)new RenderCaptainRex(new ModelCaptainRex(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserProjectile.class, new RenderLaserProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserProjectileRed.class, new RenderLaserProjectileRed());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("moisture_vaporator")), (IItemRenderer)new RenderBlockMoistureVaporator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMoistureVaporator.class, (TileEntitySpecialRenderer)new RenderMoistureVaporator());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("venator_screen")), (IItemRenderer)new RenderBlockVenatorScreen());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVenatorScreen.class, (TileEntitySpecialRenderer)new RenderVenatorScreen());
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_rack")), (IItemRenderer)new RenderBlockCoaxiumRack());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumRack.class, (TileEntitySpecialRenderer)new RenderCoaxiumRack());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("carbonite_block")), (IItemRenderer)new RenderBlockCarbonite());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarbonite.class, (TileEntitySpecialRenderer)new RenderCarbonite());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("holo_projector")), (IItemRenderer)new RenderBlockHoloProjector());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloProjector.class, (TileEntitySpecialRenderer)new RenderHoloProjector());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_container")), (IItemRenderer)new RenderBlockCoaxiumContainer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumContainer.class, (TileEntitySpecialRenderer)new RenderCoaxiumRack());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxium_refinery")), new RenderBlockCoaxiumRefinery());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumRefinery.class, new RenderCoaxiumRefineryTESR());
        
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("coaxiumPump")), new RenderBlockCoaxiumExtractor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoaxiumPump.class, new RenderCoaxiumExtractorTESR());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("felucia_flower_tall_turquoise")), (IItemRenderer)new RenderBlockFeluciaFlowerTallTurquoise());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFeluciaFlowerTurquoise.class, (TileEntitySpecialRenderer)new RenderFeluciaFlowerTallTurquoise());

        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegister.getRegisteredBlock("portable_coaxium_pump")), new ItemRendererPortableCoaxiumPump());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortableCoaxiumPump.class, new RenderPortableCoaxiumPump());
        
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
