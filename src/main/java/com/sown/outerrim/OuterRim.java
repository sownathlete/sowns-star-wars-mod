package com.sown.outerrim;

import com.sown.outerrim.blocks.*;
import com.sown.outerrim.bounty.*;
import com.sown.outerrim.commands.*;
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
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Mod(modid = "outerrim", version = "1.0.0", name = "Additions to the Outer Rim", acceptedMinecraftVersions = "[1.7.10]")
public class OuterRim {

    @Mod.Instance("outerrim")
    public static OuterRim instance;

    @SidedProxy(clientSide = "com.sown.outerrim.OuterRimClientProxy",
            serverSide = "com.sown.outerrim.OuterRimCommonProxy")
    public static OuterRimCommonProxy proxy;
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("outerrim");
    public static Set<UUID> frozenPlayers = new HashSet<UUID>();
    public static Random rngGeneral = new Random();
    public static final int GUI_COAXIUM_REFINERY = 22;

    @SideOnly(Side.CLIENT)
    public static net.minecraft.client.Minecraft mc;

    public static CreativeTabs tabBlock = new TabBlocks("OuterRimBlocksTab");
    public static SimpleNetworkWrapper network;
    public static CreativeTabs tabUtil = new TabUtil("OuterRimUtilTab");
    public static CreativeTabs tabDeco = new TabDeco("OuterRimDecoTab");
    public static CreativeTabs tabFood = new TabFood("OuterRimFoodTab");
    public static CreativeTabs tabMaterials = new TabMaterials("OuterRimMaterialsTab");
    public static CreativeTabs tabMisc = new TabMisc("OuterRimMiscTab");
    public static CreativeTabs tabHyperdrives = new TabHyperdrives("OuterRimHyperdrivesTab");
    public static CreativeTabs tabTools = new TabTools("OuterRimToolsTab");
    public static CreativeTabs tabCombat = new TabWeapons("OuterRimCombatTab");
    public static CreativeTabs tabStory = new TabStory("OuterRimStoryTab");
    public static CreativeTabs tabSpawn = new TabSpawning("OuterRimSpawnTab");
    public static DamageSource meleeDamageSource;
    public static DamageSource blasterDamageSource;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        instance = this;
        proxy.init(event);
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event) {
        if (frozenPlayers.contains(event.entityPlayer.getUniqueID())) event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        if (Loader.isModLoaded("xenobyte")) {
            System.out.println("@sownathlete, Player " + player.getCommandSenderName() + " is using Xenobyte!");
            player.playerNetServerHandler.kickPlayerFromServer("Internal Exception: io.netty.handler.codec.DecoderException: java.io.IOException: Packet was larger than expected");
        }
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        RegionRegenHandler.initRegionSystem(server);
        RegionRegenHandler.onServerStarting();

        // Commands
        event.registerServerCommand(new CommandFreeze());
        event.registerServerCommand(new CommandUnfreeze());
        event.registerServerCommand(new CommandDiscord());
        event.registerServerCommand(new CommandMap());
        event.registerServerCommand(new CommandNavigate());
        event.registerServerCommand(new CommandPatreon());
        event.registerServerCommand(new CommandPatreonCheck());
        event.registerServerCommand(new CommandSpaceTP());
        event.registerServerCommand(new CommandRename());
        event.registerServerCommand(new CommandRTP());
        event.registerServerCommand(new CommandForceCheck());
        event.registerServerCommand(new CommandPlayScene());
        event.registerServerCommand(new CommandMusic());
        event.registerServerCommand(new CommandWiki());
        event.registerServerCommand(new CommandScanStructure());

        // Region commands
        event.registerServerCommand(new RegionRegenHandler.CommandSaveRegion());
        event.registerServerCommand(new RegionRegenHandler.CommandRegenRegion());
        event.registerServerCommand(new RegionRegenHandler.CommandListRegions());
        event.registerServerCommand(new RegionRegenHandler.CommandDeleteRegion());
        event.registerServerCommand(new RegionRegenHandler.CommandViewRegion());
        event.registerServerCommand(new RegionRegenHandler.CommandScheduleRegion());

        // Bounty system
        event.registerServerCommand(new CommandPlaceBounty());
        event.registerServerCommand(new CommandAddBounty());
        event.registerServerCommand(new CommandPayBounty());
        event.registerServerCommand(new CommandRemoveBounty());
        event.registerServerCommand(new CommandListBounties());

        if (server.isSinglePlayer()) {
            event.registerServerCommand(new CommandGameMode());
            event.registerServerCommand(new CommandGMC());
            event.registerServerCommand(new CommandGMS());
            event.registerServerCommand(new CommandSkull());
            event.registerServerCommand(new CommandVanish());
            event.registerServerCommand(new CommandDay());
            event.registerServerCommand(new CommandNight());
            event.registerServerCommand(new CommandHeal());
            event.registerServerCommand(new CommandFly());
            event.registerServerCommand(new CommandSpeed());
            event.registerServerCommand(new CommandEnchant());
            event.registerServerCommand(new CommandSmite());
        }

        BountyManager.loadBounties();
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        BountyManager.saveBounties();
    }
}
