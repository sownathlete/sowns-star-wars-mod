package com.sown.outerrim;

import com.sown.outerrim.bounty.BountyDeathHandler;
import com.sown.outerrim.bounty.BountyLoginFMLHandler;
import com.sown.outerrim.bounty.BountyLoginHandler;
import com.sown.outerrim.dimension.ilum.IlumOreGeneration;
import com.sown.outerrim.dimension.kessel.KesselOreGeneration;
import com.sown.outerrim.dimension.tatooine.TatooineOreGeneration;
import com.sown.outerrim.gui.GuiHandler;
import com.sown.outerrim.handlers.*;
import com.sown.outerrim.network.MessageHyperdrive;
import com.sown.outerrim.registry.*;
import com.sown.util.structures.StructureLoader;
import com.sown.util.world.creation.worldgen.WorldGenFieldAssociation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OuterRimCommonProxy {
    private static int packetId = 1;

    public void preInit(FMLPreInitializationEvent event) {
        moveFlanPack();
        setupNetworking();
        setupConfig(event);
        GameRegistry.registerWorldGenerator(new KesselOreGeneration(), 0);
        GameRegistry.registerWorldGenerator(new IlumOreGeneration(), 0);
        GameRegistry.registerWorldGenerator(new TatooineOreGeneration(), 0);
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(OuterRim.instance, new GuiHandler());

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new RegionRegenHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerDeathHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerKillHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerLevelUpHandler());
        MinecraftForge.EVENT_BUS.register(new BountyDeathHandler());
        MinecraftForge.EVENT_BUS.register(new BountyLoginHandler());
        MinecraftForge.EVENT_BUS.register(new SownChatHandler());
        MinecraftForge.EVENT_BUS.register(new BucketHandler());

        OuterRim.meleeDamageSource = new DamageSource("outerrim.melee").setDamageBypassesArmor();
        OuterRim.blasterDamageSource = new DamageSource("outerrim.blaster").setProjectile();

        FMLCommonHandler.instance().bus().register(new TimeSyncHandler());
        FMLCommonHandler.instance().bus().register(new CoaxiumWorldTicker());

        EntityRegister.registerAll();
        ItemRegister.registerAll();
        BlockRegister.registerAll();
        FluidRegister.registerAll();
        WorldGenFieldAssociation.init();
        WorldRegister.registerAll();
        RecipeRegister.registerAll();
        StructureLoader.init();

        BountyLoginFMLHandler.register();

        if (Loader.isModLoaded("xenobyte")) {
            throw new RuntimeException("Unexpected error: IndexOutOfBoundsException at java.util.ArrayList.get()");
        }
    }

    private void setupConfig(FMLPreInitializationEvent event) {
        OuterRimResources.ConfigOptions.configFile = new File(event.getSuggestedConfigurationFile().getPath().replace("outerrim", "outerrim-1.0"));
        OuterRimResources.ConfigOptions.config = new Configuration(OuterRimResources.ConfigOptions.configFile, "1.0");
        OuterRimResources.ConfigOptions.loadConfigOptions();
        OuterRimResources.ConfigOptions.config.save();
    }

    private void moveFlanPack() {
        File modsFolder = new File("mods");
        File flanFolder = new File("Flan");

        if (!flanFolder.exists()) flanFolder.mkdir();

        boolean needsRestart = false;
        File[] files = modsFolder.listFiles((dir, name) -> name.endsWith(".jar") || name.endsWith(".zip"));
        if (files != null) {
            for (File file : files) {
                if (isFlanPackWithoutMCModInfo(file)) {
                    File destination = new File(flanFolder, file.getName());
                    if (!destination.exists() && copyFile(file, destination)) {
                        System.out.println("[Sown] Moved " + file.getName() + " to Flan folder. Restart required.");
                        needsRestart = true;
                    }
                }
            }
        }

        if (needsRestart) throw new RuntimeException("Flan pack moved. Restart required.");
    }

    private boolean isFlanPackWithoutMCModInfo(File file) {
        try (ZipFile zipFile = new ZipFile(file)) {
            boolean hasFlansmodFolder = false;
            boolean hasMCModInfo = false;
            boolean hasFrenchPack = false;
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                String entryName = entries.nextElement().getName();
                if (entryName.startsWith("com/flansmod/")) hasFlansmodFolder = true;
                if (entryName.equals("mcmod.info")) hasMCModInfo = true;
                if (entryName.startsWith("com/flansmod/frenchpack/")) hasFrenchPack = true;
            }
            return hasFlansmodFolder && !hasMCModInfo && !hasFrenchPack;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean copyFile(File source, File dest) {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setupNetworking() {
        OuterRim.network = NetworkRegistry.INSTANCE.newSimpleChannel("outerrim.chan");
        registerMessageServer(MessageHyperdrive.class);
    }

    @SuppressWarnings("unchecked")
    private void registerMessageServer(@SuppressWarnings("rawtypes") Class messageHandler) {
        OuterRim.network.registerMessage(messageHandler, messageHandler, packetId, Side.SERVER);
        ++packetId;
    }
}
