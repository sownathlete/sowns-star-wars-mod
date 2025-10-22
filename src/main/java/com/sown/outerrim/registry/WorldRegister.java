package com.sown.outerrim.registry;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.abednedo.AbednedoProvider;
import com.sown.outerrim.dimension.ahchto.AhchToProvider;
import com.sown.outerrim.dimension.ajankloss.AjanKlossProvider;
import com.sown.outerrim.dimension.alderaan.AlderaanProvider;
import com.sown.outerrim.dimension.anoat.AnoatProvider;
import com.sown.outerrim.dimension.bahryn.BahrynProvider;
import com.sown.outerrim.dimension.bakura.BakuraProvider;
import com.sown.outerrim.dimension.bespin.BespinProvider;
import com.sown.outerrim.dimension.bestine.BestineProvider;
import com.sown.outerrim.dimension.bogano.BoganoProvider;
import com.sown.outerrim.dimension.byss.ByssProvider;
import com.sown.outerrim.dimension.carida.CaridaProvider;
import com.sown.outerrim.dimension.catoneimoidia.CatoNeimoidiaProvider;
import com.sown.outerrim.dimension.corellia.CorelliaProvider;
import com.sown.outerrim.dimension.coruscant.CoruscantProvider;
import com.sown.outerrim.dimension.crait.CraitProvider;
import com.sown.outerrim.dimension.csilla.CsillaProvider;
import com.sown.outerrim.dimension.dagobah.DagobahProvider;
import com.sown.outerrim.dimension.dantooine.DantooineProvider;
import com.sown.outerrim.dimension.dathomir.DathomirBiomes;
import com.sown.outerrim.dimension.dathomir.WorldProviderDathomir;
import com.sown.outerrim.dimension.deathstar.DeathStarProvider;
import com.sown.outerrim.dimension.endor.EndorProvider;
import com.sown.outerrim.dimension.exegol.ExegolProvider;
import com.sown.outerrim.dimension.felucia.FeluciaBiomes;
import com.sown.outerrim.dimension.felucia.WorldProviderFelucia;
import com.sown.outerrim.dimension.geonosis.GeonosisBiomes;
import com.sown.outerrim.dimension.geonosis.WorldProviderGeonosis;
import com.sown.outerrim.dimension.hoth.HothProvider;
import com.sown.outerrim.dimension.ilum.IlumBiomes;
import com.sown.outerrim.dimension.ilum.WorldProviderIlum;
import com.sown.outerrim.dimension.jakku.JakkuProvider;
import com.sown.outerrim.dimension.kamino.KaminoProvider;
import com.sown.outerrim.dimension.kashyyyk.KashyyykProvider;
import com.sown.outerrim.dimension.kessel.KesselBiomes;
import com.sown.outerrim.dimension.kessel.WorldProviderKessel;
import com.sown.outerrim.dimension.korriban.KorribanBiomes;
import com.sown.outerrim.dimension.korriban.WorldProviderKorriban;
import com.sown.outerrim.dimension.manaan.ManaanProvider;
import com.sown.outerrim.dimension.mandalore.MandaloreBiomes;
import com.sown.outerrim.dimension.mandalore.WorldProviderMandalore;
import com.sown.outerrim.dimension.mimban.MimbanProvider;
import com.sown.outerrim.dimension.mustafar.MustafarBiomes;
import com.sown.outerrim.dimension.mustafar.WorldProviderMustafar;
import com.sown.outerrim.dimension.naboo.NabooProvider;
import com.sown.outerrim.dimension.niamos.NiamosProvider;
import com.sown.outerrim.dimension.nur.NurProvider;
import com.sown.outerrim.dimension.rakataprime.RakataPrimeProvider;
import com.sown.outerrim.dimension.ryloth.RylothBiomes;
import com.sown.outerrim.dimension.ryloth.WorldProviderRyloth;
import com.sown.outerrim.dimension.savareen.SavareenProvider;
import com.sown.outerrim.dimension.scarif.ScarifProvider;
import com.sown.outerrim.dimension.takodana.TakodanaProvider;
import com.sown.outerrim.dimension.taris.TarisProvider;
import com.sown.outerrim.dimension.tatooine.TatooineBiomes;
import com.sown.outerrim.dimension.tatooine.WorldProviderTatooine;
import com.sown.outerrim.dimension.tython.TythonBiomes;
import com.sown.outerrim.dimension.tython.WorldProviderTython;
import com.sown.outerrim.dimension.utapau.UtapauProvider;
import com.sown.outerrim.dimension.vandor.VandorProvider;
import com.sown.outerrim.dimension.vjun.VjunProvider;
import com.sown.outerrim.dimension.wbw.WBWProvider;
import com.sown.outerrim.dimension.yavin4.Yavin4Provider;
import com.sown.util.world.WorldUtils;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WorldRegister {
	public static void registerAll() {
        registerPlanet("Abednedo", AbednedoProvider.class, AbednedoProvider.abednedo);
        registerPlanet("AhchTo", AhchToProvider.class, AhchToProvider.biomes);
        registerPlanet("Ajan Kloss", AjanKlossProvider.class, AjanKlossProvider.ajanKloss);
        registerPlanet("Alderaan", AlderaanProvider.class, AlderaanProvider.biomes);
        registerPlanet("Anoat", AnoatProvider.class, AnoatProvider.anoat);
        registerPlanet("Bahryn", BahrynProvider.class, BahrynProvider.bahryn);
        registerPlanet("Bakura", BakuraProvider.class, BakuraProvider.bakura);
        registerPlanet("Bespin", BespinProvider.class, BespinProvider.bespin);
        registerPlanet("Bestine", BestineProvider.class, BestineProvider.bestine);
        registerPlanet("Bogano", BoganoProvider.class, BoganoProvider.biomes);
        registerPlanet("Byss", ByssProvider.class, ByssProvider.byss);
        registerPlanet("Carida", CaridaProvider.class, CaridaProvider.carida);
        registerPlanet("Cato Neimoidia", CatoNeimoidiaProvider.class, CatoNeimoidiaProvider.catoneimoidia);
        registerPlanet("Corellia", CorelliaProvider.class, CorelliaProvider.corellia);
        registerPlanet("Coruscant", CoruscantProvider.class, CoruscantProvider.coruscant);
        registerPlanet("Crait", CraitProvider.class, CraitProvider.biomes);
        registerPlanet("Csilla", CsillaProvider.class, CsillaProvider.csilla);
        registerPlanet("Dagobah", DagobahProvider.class, DagobahProvider.biomes);
        registerPlanet("Dantooine", DantooineProvider.class, DantooineProvider.biomes);
        registerPlanet("Dathomir", WorldProviderDathomir.class, DathomirBiomes.biomes);
        registerPlanet("Death Star", DeathStarProvider.class, DeathStarProvider.deathStar);
        registerPlanet("Endor", EndorProvider.class, EndorProvider.biomes);
        //registerPlanet("Exegol", ExegolProvider.class, ExegolProvider.exegol);
        registerPlanet("Felucia", WorldProviderFelucia.class, FeluciaBiomes.biomes);
        registerPlanet("Geonosis", WorldProviderGeonosis.class, GeonosisBiomes.biomes);
        registerPlanet("Hoth", HothProvider.class, HothProvider.biomes);
        registerPlanet("Ilum", WorldProviderIlum.class, IlumBiomes.biomes);
        registerPlanet("Jakku", JakkuProvider.class, JakkuProvider.jakku);
        registerPlanet("Kamino", KaminoProvider.class, KaminoProvider.kamino);
        registerPlanet("Kashyyyk", KashyyykProvider.class, KashyyykProvider.kashyyyk);
        registerPlanet("Kessel", WorldProviderKessel.class, KesselBiomes.biomes);
        registerPlanet("Korriban", WorldProviderKorriban.class, KorribanBiomes.biomes);
        registerPlanet("Manaan", ManaanProvider.class, ManaanProvider.manaan);
        registerPlanet("Mandalore", WorldProviderMandalore.class, MandaloreBiomes.biomes);
        registerPlanet("Mimban", MimbanProvider.class, MimbanProvider.mimban);
        registerPlanet("Mustafar", WorldProviderMustafar.class, MustafarBiomes.biomes);
        registerPlanet("Naboo", NabooProvider.class, NabooProvider.biomes);
        registerPlanet("Niamos", NiamosProvider.class, NiamosProvider.biomes);
        registerPlanet("Nur", NurProvider.class, NurProvider.nur);
        registerPlanet("Rakata Prime", RakataPrimeProvider.class, RakataPrimeProvider.rakataPrime);
        registerPlanet("Ryloth", WorldProviderRyloth.class, RylothBiomes.biomes);
        registerPlanet("Savareen", SavareenProvider.class, SavareenProvider.biomes);
        registerPlanet("Scarif", ScarifProvider.class, ScarifProvider.scarif);
        registerPlanet("Takodana", TakodanaProvider.class, TakodanaProvider.biomes);
        registerPlanet("Taris", TarisProvider.class, TarisProvider.taris);
        registerPlanet("Tatooine", WorldProviderTatooine.class, TatooineBiomes.biomes);
        registerPlanet("Tython", WorldProviderTython.class, TythonBiomes.biomes);
        registerPlanet("Utapau", UtapauProvider.class, UtapauProvider.utapau);
        registerPlanet("Vandor", VandorProvider.class, VandorProvider.vandor);
        registerPlanet("Vjun", VjunProvider.class, VjunProvider.vjun);
        registerPlanet("WorldBetweenWorlds", WBWProvider.class, WBWProvider.worldBetweenWorlds);
        registerPlanet("Yavin 4", Yavin4Provider.class, Yavin4Provider.yavin4);
	}
	
    public static void registerPlanet(String planetName, Class<? extends WorldProvider> worldProviderClass, BiomeGenBase biome) {
        List<BiomeGenBase> biomes = new ArrayList<>();
        biomes.add(biome);
        registerPlanet(planetName, worldProviderClass, biomes);
    }

    public static void registerPlanet(String planetName, Class<? extends WorldProvider> worldProviderClass, List<BiomeGenBase> biomes) {
        int dimId;
        String sanitizedPlanetName = planetName.replace(" ", ""); // Remove spaces from planet name
        try {
            dimId = (int) OuterRimResources.ConfigOptions.class.getField("dim" + sanitizedPlanetName + "Id").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exception
            throw new RuntimeException("Failed to retrieve dimension ID for planet " + planetName, e);
        }

        WorldUtils.registerDimension(dimId, worldProviderClass);
        for (BiomeGenBase biome : biomes) {
            BiomeManager.removeSpawnBiome(biome);
        }
    }
    
}

