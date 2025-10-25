package com.sown.outerrim;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class OuterRimResources {

	public static class ConfigOptions {
		public static final String CAT_DIM = "dimensions";
		public static final String CAT_BIOMES = "biomes";
		public static final String CAT_COMPAT = "compatibility";
		private static int guiCounter = 0;
		public static final int GUI_VAPORATOR = 1;
		public static final int GUI_REFINERY  = 2;
		public static final int GUI_COAXIUM_CONTAINER = 3;
		public static final int GUI_PORTABLE_PUMP = 4;
		public static final int GUI_COAXIUM_PUMP = 5;

		public static int biomeAbednedoId;
		public static int biomeAhchToId;
		public static int biomeAjanKlossId;
		public static int biomeAlderaanId;
		public static int biomeAnoatId;
		public static int biomeArvala7Id;
		public static int biomeAthullaId;
		public static int biomeAureaId;
		public static int biomeBahrynId;
		public static int biomeBakuraId;
		public static int biomeBalosarId;
		public static int biomeBatuuId;
		public static int biomeBespinId;
		public static int biomeBestineId;
		public static int biomeBoganoId;
		public static int biomeBothawuiId;
		public static int biomeBraccaId;
		public static int biomeByssId;
		public static int biomeCantonicaId;
		public static int biomeCaridaId;
		public static int biomeCatoNeimoidiaId;
		public static int biomeChandrilaId;
		public static int biomeCholgannaId;
		public static int biomeCorelliaId;
		public static int biomeCorfaiId;
		public static int biomeCoruscantId;
		public static int biomeCraitId;
		public static int biomeCraitMountainsId;
		public static int biomeCristophsisId;
		public static int biomeCsillaId;
		public static int biomeDagobahId;
		public static int biomeDantooineId;
		public static int biomeDathomirForestId;
		public static int biomeDathomirSwampId;
		public static int biomeDathomirCliffsId;
		public static int biomeDeathStarId;
		public static int biomeDevaronId;
		public static int biomeDosuunId;
		public static int biomeDQarId;
		public static int biomeDrallId;
		public static int biomeDromundkaasId;
		public static int biomeDuroId;
		public static int biomeEaduId;
		public static int biomeEarthId;
		public static int biomeEmpresstetaId;
		public static int biomeEndorTaigaId;
		public static int biomeEndorMegaTaigaId;
		public static int biomeEndorMegaTaigaHillsId;
		public static int biomeExegolId;
		public static int biomeFeluciaHighlandsId;
		public static int biomeFeluciaJungleId;
		public static int biomeFlorrumId;
		public static int biomeFondorId;
		public static int biomeFormosId;
		public static int biomeFrozId;
		public static int biomeGamorrId;
		public static int biomeGeonosisDesertId;
		public static int biomeGeonosisGravelHillsId;
		public static int biomeGeonosisMesaId;
		public static int biomeHapesId;
		public static int biomeHosnianPrimeId;
		public static int biomeHothId;
		public static int biomeHothMountainsId;
		public static int biomeHurikaneId;
		public static int biomeIegoId;
		public static int biomeIlumMountainsId;
		public static int biomeIlumPlainsId;
		public static int biomeJakkuId;
		public static int biomeJedhaId;
		public static int biomeJestefadId;
		public static int biomeKaminoId;
		public static int biomeKashyyykId;
		public static int biomeKefbirId;
		public static int biomeKesselRock1Id;
		public static int biomeKesselRock2Id;
		public static int biomeKesselDirt1Id;
		public static int biomeKesselDirt2Id;
		public static int biomeKesselMudId;
		public static int biomeKesselMountainsId;
		public static int biomeKijimiId;
		public static int biomeKorribanDesertId;
		public static int biomeKorribanMountainsId;
		public static int biomeKrownestId;
		public static int biomeKuatId;
		public static int biomeLahmuId;
		public static int biomeLothalId;
		public static int biomeMalachorId;
		public static int biomeMalastareId;
		public static int biomeManaanId;
		public static int biomeMandaloreId;
		public static int biomeMimbanId;
		public static int biomeMonCalamariId;
		public static int biomeMortisId;
		public static int biomeMustafarRockMountainsId;
		public static int biomeMustafarLavaLakesId;
		public static int biomeMustafarPlateauId;
		public static int biomeMygeetoId;
		public static int biomeNabooPlainsId;
		public static int biomeNabooGreatPlainsId;
		public static int biomeNabooMountainsId;
		public static int biomeNalhuttaId;
		public static int biomeNevarroId;
		public static int biomeNiamosId;
		public static int biomeNubiaId;
		public static int biomeNumidianPrimeId;
		public static int biomeNurId;
		public static int biomeOnderonId;
		public static int biomeOrdMantellId;
		public static int biomePasaanaId;
		public static int biomePillioId;
		public static int biomePolisMassaId;
		public static int biomeRakataPrimeId;
		public static int biomeRaxusprimeId;
		public static int biomeRhenVarId;
		public static int biomeRodiaId;
		public static int biomeRylothId;
		public static int biomeRylothPlateauId;
		public static int biomeSacorriaId;
		public static int biomeSaleucamiId;
		public static int biomeSavareenId;
		public static int biomeScarifId;
		public static int biomeSeloniaId;
		public static int biomeSorganId;
		public static int biomeSullustId;
		public static int biomeTakodanaId;
		public static int biomeTalusId;
		public static int biomeTarisId;
		public static int biomeTatooineDesertId;
		public static int biomeTatooineMountainsId;
		public static int biomeTythonForestId;
		public static int biomeTythonMountainsId;
		public static int biomeUmbaraId;
		public static int biomeUtapauId;
		public static int biomeVagrandId;
		public static int biomeVandorId;
		public static int biomeVjunId;
		public static int biomeWobaniId;
		public static int biomeWorldBetweenWorldsId;
		public static int biomeXyquineIIIId;
		public static int biomeYavin4Id;
		public static int biomeZeffoId;

		public static int dimAbednedoId;
		public static int dimAhchToId;
		public static int dimAjanKlossId;
		public static int dimAlderaanId;
		public static int dimAnoatId;
		public static int dimArvala7Id;
		public static int dimAthullaId;
		public static int dimAureaId;
		public static int dimBahrynId;
		public static int dimBakuraId;
		public static int dimBalosarId;
		public static int dimBatuuId;
		public static int dimBespinId;
		public static int dimBestineId;
		public static int dimBoganoId;
		public static int dimBothawuiId;
		public static int dimBraccaId;
		public static int dimByssId;
		public static int dimCantonicaId;
		public static int dimCaridaId;
		public static int dimCatoNeimoidiaId;
		public static int dimChandrilaId;
		public static int dimCholgannaId;
		public static int dimChristophsisId;
		public static int dimConcordDawnId;
		public static int dimConcordiaId;
		public static int dimCorelliaId;
		public static int dimCorfaiId;
		public static int dimCoruscantId;
		public static int dimCraitId;
		public static int dimCsillaId;
		public static int dimDagobahId;
		public static int dimDantooineId;
		public static int dimDathomirId;
		public static int dimDeathStarId;
		public static int dimDevaronId;
		public static int dimDosuunId;
		public static int dimDQarId;
		public static int dimDrallId;
		public static int dimDromundkaasId;
		public static int dimDuroId;
		public static int dimEaduId;
		public static int dimEmpresstetaId;
		public static int dimEndorId;
		public static int dimExegolId;
		public static int dimFeluciaId;
		public static int dimFlorrumId;
		public static int dimFondorId;
		public static int dimFormosId;
		public static int dimFrozId;
		public static int dimGamorrId;
		public static int dimGeonosisId;
		public static int dimHapesId;
		public static int dimHosnianPrimeId;
		public static int dimHothId;
		public static int dimHurikaneId;
		public static int dimIegoId;
		public static int dimIlumId;
		public static int dimJakkuId;
		public static int dimJedhaId;
		public static int dimJestefadId;
		public static int dimKaminoId;
		public static int dimKashyyykId;
		public static int dimKefbirId;
		public static int dimKesselId;
		public static int dimKijimiId;
		public static int dimKorribanId;
		public static int dimKrownestId;
		public static int dimKuatId;
		public static int dimLahmuId;
		public static int dimLothalId;
		public static int dimMalachorId;
		public static int dimMalastareId;
		public static int dimManaanId;
		public static int dimMandaloreId;
		public static int dimMimbanId;
		public static int dimMonCalamariId;
		public static int dimMortisId;
		public static int dimMustafarId;
		public static int dimMygeetoId;
		public static int dimNabooId;
		public static int dimNalhuttaId;
		public static int dimNevarroId;
		public static int dimNiamosId;
		public static int dimNubiaId;
		public static int dimNumidianPrimeId;
		public static int dimNurId;
		public static int dimOnderonId;
		public static int dimOrdMantellId;
		public static int dimPasaanaId;
		public static int dimPillioId;
		public static int dimPolisMassaId;
		public static int dimRakataPrimeId;
		public static int dimRaxusprimeId;
		public static int dimRhenVarId;
		public static int dimRiflorId;
		public static int dimRodiaId;
		public static int dimRylothId;
		public static int dimSacorriaId;
		public static int dimSaleucamiId;
		public static int dimSavareenId;
		public static int dimScarifId;
		public static int dimSeloniaId;
		public static int dimSorganId;
		public static int dimSullustId;
		public static int dimTakodanaId;
		public static int dimTalusId;
		public static int dimTarisId;
		public static int dimTatooineId;
		public static int dimTelosIVId;
		public static int dimTralusId;
		public static int dimTrandoshaId;
		public static int dimTythonId;
		public static int dimUmbaraId;
		public static int dimUtapauId;
		public static int dimVagrandId;
		public static int dimVandorId;
		public static int dimVjunId;
		public static int dimWobaniId;
		public static int dimWorldBetweenWorldsId;
		public static int dimXyquineIIIId;
		public static int dimYavin4Id;
		public static int dimZeffoId;

		public static boolean compatLegendsMod;
		public static boolean compatParzisMod;
		public static boolean compatWarpDrive;

		public static Configuration config;
		public static File configFile;

		private static ConfigCategory getCategory(String key) {
			return config.getCategory(key).setLanguageKey("category.outerrim." + key);
		}

		public static void loadConfigOptions() {
	        dimAbednedoId = config.get(CAT_DIM, "abednedo", 119).setRequiresMcRestart(true).getInt();
	        dimAhchToId = config.get(CAT_DIM, "ahchTo", 120).setRequiresMcRestart(true).getInt();
	        dimAjanKlossId = config.get(CAT_DIM, "ajankloss", 121).setRequiresMcRestart(true).getInt();
	        dimAlderaanId = config.get(CAT_DIM, "alderaan", 122).setRequiresMcRestart(true).getInt();
	        dimAnoatId = config.get(CAT_DIM, "anoat", 123).setRequiresMcRestart(true).getInt();
	        dimArvala7Id = config.get(CAT_DIM, "Arvala7", 124).setRequiresMcRestart(true).getInt();
	        dimAthullaId = config.get(CAT_DIM, "athulla", 125).setRequiresMcRestart(true).getInt();
	        dimAureaId = config.get(CAT_DIM, "aurea", 126).setRequiresMcRestart(true).getInt();
	        dimBahrynId = config.get(CAT_DIM, "bahryn", 238).setRequiresMcRestart(true).getInt();
	        dimBakuraId = config.get(CAT_DIM, "bakura", 127).setRequiresMcRestart(true).getInt();
	        dimBalosarId = config.get(CAT_DIM, "balosar", 128).setRequiresMcRestart(true).getInt();
	        dimBatuuId = config.get(CAT_DIM, "batuu", 129).setRequiresMcRestart(true).getInt();
	        dimBespinId = config.get(CAT_DIM, "bespin", 130).setRequiresMcRestart(true).getInt();
	        dimBestineId = config.get(CAT_DIM, "bestine", 131).setRequiresMcRestart(true).getInt();
	        dimBoganoId = config.get(CAT_DIM, "bogano", 132).setRequiresMcRestart(true).getInt();
	        dimBothawuiId = config.get(CAT_DIM, "bothawui", 133).setRequiresMcRestart(true).getInt();
	        dimBraccaId = config.get(CAT_DIM, "bracca", 134).setRequiresMcRestart(true).getInt();
	        dimByssId = config.get(CAT_DIM, "byss", 135).setRequiresMcRestart(true).getInt();
	        dimCantonicaId = config.get(CAT_DIM, "cantonica", 136).setRequiresMcRestart(true).getInt();
	        dimCaridaId = config.get(CAT_DIM, "carida", 137).setRequiresMcRestart(true).getInt();
	        dimCatoNeimoidiaId = config.get(CAT_DIM, "catoneimoidia", 138).setRequiresMcRestart(true).getInt();
	        dimChandrilaId = config.get(CAT_DIM, "chandrila", 139).setRequiresMcRestart(true).getInt();
	        dimCholgannaId = config.get(CAT_DIM, "cholganna", 140).setRequiresMcRestart(true).getInt();
	        dimConcordDawnId = config.get(CAT_DIM, "Concord Dawn", 239).setRequiresMcRestart(true).getInt();
	        dimConcordiaId = config.get(CAT_DIM, "concordia", 240).setRequiresMcRestart(true).getInt();
	        dimCorelliaId = config.get(CAT_DIM, "corellia", 141).setRequiresMcRestart(true).getInt();
	        dimCorfaiId = config.get(CAT_DIM, "corfai", 142).setRequiresMcRestart(true).getInt();
	        dimCoruscantId = config.get(CAT_DIM, "coruscant", 143).setRequiresMcRestart(true).getInt();
	        dimCraitId = config.get(CAT_DIM, "crait", 144).setRequiresMcRestart(true).getInt();
	        dimChristophsisId = config.get(CAT_DIM, "cristophsis", 145).setRequiresMcRestart(true).getInt();
	        dimCsillaId = config.get(CAT_DIM, "csilla", 146).setRequiresMcRestart(true).getInt();
	        dimDagobahId = config.get(CAT_DIM, "dagobah", 147).setRequiresMcRestart(true).getInt();
	        dimDantooineId = config.get(CAT_DIM, "dantooine", 148).setRequiresMcRestart(true).getInt();
	        dimDathomirId = config.get(CAT_DIM, "dathomir", 149).setRequiresMcRestart(true).getInt();
	        dimDeathStarId = config.get(CAT_DIM, "deathStar", 150).setRequiresMcRestart(true).getInt();
	        dimDevaronId = config.get(CAT_DIM, "devaron", 151).setRequiresMcRestart(true).getInt();
	        dimDosuunId = config.get(CAT_DIM, "dosuun", 152).setRequiresMcRestart(true).getInt();
	        dimDQarId = config.get(CAT_DIM, "dQar", 153).setRequiresMcRestart(true).getInt();
	        dimDrallId = config.get(CAT_DIM, "drall", 154).setRequiresMcRestart(true).getInt();
	        dimDromundkaasId = config.get(CAT_DIM, "dromundkaas", 155).setRequiresMcRestart(true).getInt();
	        dimDuroId = config.get(CAT_DIM, "duro", 156).setRequiresMcRestart(true).getInt();
	        dimEaduId = config.get(CAT_DIM, "eadu", 157).setRequiresMcRestart(true).getInt();
	        dimEmpresstetaId = config.get(CAT_DIM, "empressteta", 159).setRequiresMcRestart(true).getInt();
	        dimEndorId = config.get(CAT_DIM, "endor", 160).setRequiresMcRestart(true).getInt();
	        dimExegolId = config.get(CAT_DIM, "exegol", 161).setRequiresMcRestart(true).getInt();
	        dimFeluciaId = config.get(CAT_DIM, "felucia", 162).setRequiresMcRestart(true).getInt();
	        dimFlorrumId = config.get(CAT_DIM, "florrum", 163).setRequiresMcRestart(true).getInt();
	        dimFondorId = config.get(CAT_DIM, "fondor", 164).setRequiresMcRestart(true).getInt();
	        dimFormosId = config.get(CAT_DIM, "formos", 165).setRequiresMcRestart(true).getInt();
	        dimFrozId = config.get(CAT_DIM, "froz", 166).setRequiresMcRestart(true).getInt();
	        dimGamorrId = config.get(CAT_DIM, "gamorr", 167).setRequiresMcRestart(true).getInt();
	        dimGeonosisId = config.get(CAT_DIM, "geonosis", 168).setRequiresMcRestart(true).getInt();
	        dimHapesId = config.get(CAT_DIM, "hapes", 169).setRequiresMcRestart(true).getInt();
	        dimHosnianPrimeId = config.get(CAT_DIM, "hosnianPrime", 170).setRequiresMcRestart(true).getInt();
	        dimHothId = config.get(CAT_DIM, "hoth", 171).setRequiresMcRestart(true).getInt();
	        dimHurikaneId = config.get(CAT_DIM, "hurikane", 172).setRequiresMcRestart(true).getInt();
	        dimIegoId = config.get(CAT_DIM, "iego", 173).setRequiresMcRestart(true).getInt();
	        dimIlumId = config.get(CAT_DIM, "ilum", 174).setRequiresMcRestart(true).getInt();
	        dimJakkuId = config.get(CAT_DIM, "jakku", 175).setRequiresMcRestart(true).getInt();
	        dimJedhaId = config.get(CAT_DIM, "jedha", 176).setRequiresMcRestart(true).getInt();
	        dimJestefadId = config.get(CAT_DIM, "jestefad", 177).setRequiresMcRestart(true).getInt();
	        dimKaminoId = config.get(CAT_DIM, "kamino", 178).setRequiresMcRestart(true).getInt();
	        dimKashyyykId = config.get(CAT_DIM, "kashyyyk", 179).setRequiresMcRestart(true).getInt();
	        dimKefbirId = config.get(CAT_DIM, "kefbir", 180).setRequiresMcRestart(true).getInt();
	        dimKesselId = config.get(CAT_DIM, "kessel", 181).setRequiresMcRestart(true).getInt();
	        dimKijimiId = config.get(CAT_DIM, "kijimi", 182).setRequiresMcRestart(true).getInt();
	        dimKorribanId = config.get(CAT_DIM, "korriban", 183).setRequiresMcRestart(true).getInt();
	        dimKrownestId = config.get(CAT_DIM, "krownest", 184).setRequiresMcRestart(true).getInt();
	        dimKuatId = config.get(CAT_DIM, "kuat", 185).setRequiresMcRestart(true).getInt();
	        dimLahmuId = config.get(CAT_DIM, "lahmu", 186).setRequiresMcRestart(true).getInt();
	        dimLothalId = config.get(CAT_DIM, "lothal", 187).setRequiresMcRestart(true).getInt();
	        dimMalachorId = config.get(CAT_DIM, "Malachor", 188).setRequiresMcRestart(true).getInt();
	        dimMalastareId = config.get(CAT_DIM, "malastare", 189).setRequiresMcRestart(true).getInt();
	        dimManaanId = config.get(CAT_DIM, "manaan", 190).setRequiresMcRestart(true).getInt();
	        dimMandaloreId = config.get(CAT_DIM, "mandalore", 191).setRequiresMcRestart(true).getInt();
	        dimMimbanId = config.get(CAT_DIM, "mimban", 192).setRequiresMcRestart(true).getInt();
	        dimMonCalamariId = config.get(CAT_DIM, "monCalamari", 193).setRequiresMcRestart(true).getInt();
	        dimMortisId = config.get(CAT_DIM, "mortis", 194).setRequiresMcRestart(true).getInt();
	        dimMustafarId = config.get(CAT_DIM, "mustafar", 195).setRequiresMcRestart(true).getInt();
	        dimMygeetoId = config.get(CAT_DIM, "mygeeto", 196).setRequiresMcRestart(true).getInt();
	        dimNabooId = config.get(CAT_DIM, "naboo", 197).setRequiresMcRestart(true).getInt();
	        dimNalhuttaId = config.get(CAT_DIM, "nalhutta", 198).setRequiresMcRestart(true).getInt();
	        dimNevarroId = config.get(CAT_DIM, "Nevarro", 199).setRequiresMcRestart(true).getInt();
	        dimNiamosId = config.get(CAT_DIM, "Niamos", 242).setRequiresMcRestart(true).getInt();
	        dimNubiaId = config.get(CAT_DIM, "nubia", 200).setRequiresMcRestart(true).getInt();
	        dimNumidianPrimeId = config.get(CAT_DIM, "numidianPrime", 201).setRequiresMcRestart(true).getInt();
	        dimNurId = config.get(CAT_DIM, "nur", 202).setRequiresMcRestart(true).getInt();
	        dimOnderonId = config.get(CAT_DIM, "onderon", 203).setRequiresMcRestart(true).getInt();
	        dimOrdMantellId = config.get(CAT_DIM, "ordMantell", 204).setRequiresMcRestart(true).getInt();
	        dimPasaanaId = config.get(CAT_DIM, "pasaana", 205).setRequiresMcRestart(true).getInt();
	        dimPillioId = config.get(CAT_DIM, "pillio", 206).setRequiresMcRestart(true).getInt();
	        dimPolisMassaId = config.get(CAT_DIM, "polisMassa", 207).setRequiresMcRestart(true).getInt();
	        dimRakataPrimeId = config.get(CAT_DIM, "rakataPrime", 208).setRequiresMcRestart(true).getInt();
	        dimRaxusprimeId = config.get(CAT_DIM, "raxusprime", 209).setRequiresMcRestart(true).getInt();
	        dimRhenVarId = config.get(CAT_DIM, "rhenVar", 210).setRequiresMcRestart(true).getInt();
	        dimRiflorId = config.get(CAT_DIM, "Riflor", 241).setRequiresMcRestart(true).getInt();
	        dimRodiaId = config.get(CAT_DIM, "rodia", 211).setRequiresMcRestart(true).getInt();
	        dimRylothId = config.get(CAT_DIM, "ryloth", 212).setRequiresMcRestart(true).getInt();
	        dimSacorriaId = config.get(CAT_DIM, "sacorria", 213).setRequiresMcRestart(true).getInt();
	        dimSaleucamiId = config.get(CAT_DIM, "saleucami", 214).setRequiresMcRestart(true).getInt();
	        dimSavareenId = config.get(CAT_DIM, "savareen", 215).setRequiresMcRestart(true).getInt();
	        dimScarifId = config.get(CAT_DIM, "scarif", 216).setRequiresMcRestart(true).getInt();
	        dimSeloniaId = config.get(CAT_DIM, "selonia", 217).setRequiresMcRestart(true).getInt();
	        dimSorganId = config.get(CAT_DIM, "sorgan", 218).setRequiresMcRestart(true).getInt();
	        dimSullustId = config.get(CAT_DIM, "sullust", 219).setRequiresMcRestart(true).getInt();
	        dimTakodanaId = config.get(CAT_DIM, "takodana", 220).setRequiresMcRestart(true).getInt();
	        dimTalusId = config.get(CAT_DIM, "talus", 221).setRequiresMcRestart(true).getInt();
	        dimTarisId = config.get(CAT_DIM, "taris", 222).setRequiresMcRestart(true).getInt();
	        dimTatooineId = config.get(CAT_DIM, "tatooine", 223).setRequiresMcRestart(true).getInt();
	        dimTelosIVId = config.get(CAT_DIM, "telosIV", 224).setRequiresMcRestart(true).getInt();
	        dimTralusId = config.get(CAT_DIM, "tralus", 225).setRequiresMcRestart(true).getInt();
	        dimTrandoshaId = config.get(CAT_DIM, "trandosha", 226).setRequiresMcRestart(true).getInt();
	        dimTythonId = config.get(CAT_DIM, "tython", 227).setRequiresMcRestart(true).getInt();
	        dimUmbaraId = config.get(CAT_DIM, "umbara", 228).setRequiresMcRestart(true).getInt();
	        dimUtapauId = config.get(CAT_DIM, "utapau", 229).setRequiresMcRestart(true).getInt();
	        dimVagrandId = config.get(CAT_DIM, "vagrand", 230).setRequiresMcRestart(true).getInt();
	        dimVandorId = config.get(CAT_DIM, "vandor", 231).setRequiresMcRestart(true).getInt();
	        dimVjunId = config.get(CAT_DIM, "vjun", 232).setRequiresMcRestart(true).getInt();
	        dimWobaniId = config.get(CAT_DIM, "wobani", 233).setRequiresMcRestart(true).getInt();
	        dimWorldBetweenWorldsId = config.get(CAT_DIM, "worldBetweenWorlds", 234).setRequiresMcRestart(true).getInt();
	        dimXyquineIIIId = config.get(CAT_DIM, "xyquineIII", 235).setRequiresMcRestart(true).getInt();
	        dimYavin4Id = config.get(CAT_DIM, "yavin4", 236).setRequiresMcRestart(true).getInt();
	        dimZeffoId = config.get(CAT_DIM, "zeffo", 237).setRequiresMcRestart(true).getInt();
	        //next id 243

			// Biome IDs
	        biomeAbednedoId = config.get(CAT_BIOMES, "Abednedo", 100).setRequiresMcRestart(true).getInt();
	        biomeAhchToId = config.get(CAT_BIOMES, "Ahch-To", 101).setRequiresMcRestart(true).getInt();
	        biomeAjanKlossId = config.get(CAT_BIOMES, "Ajan Kloss", 186).setRequiresMcRestart(true).getInt();
	        biomeAnoatId = config.get(CAT_BIOMES, "Anoat", 102).setRequiresMcRestart(true).getInt();
	        biomeBahrynId = config.get(CAT_BIOMES, "Bahryn", 179).setRequiresMcRestart(true).getInt();
	        biomeBakuraId = config.get(CAT_BIOMES, "Bakura", 177).setRequiresMcRestart(true).getInt();
	        biomeBespinId = config.get(CAT_BIOMES, "Bespin", 103).setRequiresMcRestart(true).getInt();
	        biomeBestineId = config.get(CAT_BIOMES, "Bestine", 104).setRequiresMcRestart(true).getInt();
	        biomeBoganoId = config.get(CAT_BIOMES, "Bogano", 105).setRequiresMcRestart(true).getInt();
	        biomeByssId = config.get(CAT_BIOMES, "Byss", 106).setRequiresMcRestart(true).getInt();
	        biomeCaridaId = config.get(CAT_BIOMES, "Carida", 176).setRequiresMcRestart(true).getInt();
	        biomeCorelliaId = config.get(CAT_BIOMES, "Corellia", 180).setRequiresMcRestart(true).getInt();
	        biomeCoruscantId = config.get(CAT_BIOMES, "Coruscant", 107).setRequiresMcRestart(true).getInt();
	        biomeCraitId = config.get(CAT_BIOMES, "Crait Salt Flats", 108).setRequiresMcRestart(true).getInt();
	        biomeCraitMountainsId = config.get(CAT_BIOMES, "Crait Mountains", 109).setRequiresMcRestart(true).getInt();
	        biomeCsillaId = config.get(CAT_BIOMES, "Csilla", 110).setRequiresMcRestart(true).getInt();
	        biomeDagobahId = config.get(CAT_BIOMES, "Dagobah", 111).setRequiresMcRestart(true).getInt();
	        biomeDathomirForestId = config.get(CAT_BIOMES, "Dathomir Forest", 159).setRequiresMcRestart(true).getInt();
	        biomeDathomirSwampId = config.get(CAT_BIOMES, "Dathomir Swamp", 181).setRequiresMcRestart(true).getInt();
	        biomeDathomirCliffsId = config.get(CAT_BIOMES, "Dathomir Cliffs", 161).setRequiresMcRestart(true).getInt();
	        biomeDeathStarId = config.get(CAT_BIOMES, "Death Star", 112).setRequiresMcRestart(true).getInt();
	        biomeEndorTaigaId = config.get(CAT_BIOMES, "Endor Taiga", 183).setRequiresMcRestart(true).getInt();
	        biomeEndorMegaTaigaId = config.get(CAT_BIOMES, "Endor Mega Taiga", 184).setRequiresMcRestart(true).getInt();
	        biomeEndorMegaTaigaHillsId = config.get(CAT_BIOMES, "Endor Mega Taiga Hills", 185).setRequiresMcRestart(true).getInt();
	        biomeExegolId = config.get(CAT_BIOMES, "Exegol", 178).setRequiresMcRestart(true).getInt();
	        biomeFeluciaHighlandsId = config.get(CAT_BIOMES, "Felucia Highlands", 113).setRequiresMcRestart(true).getInt();
	        biomeFeluciaJungleId = config.get(CAT_BIOMES, "Felucia Jungle", 114).setRequiresMcRestart(true).getInt();
	        biomeGeonosisDesertId = config.get(CAT_BIOMES, "Geonosis Desert", 115).setRequiresMcRestart(true).getInt();
	        biomeGeonosisGravelHillsId = config.get(CAT_BIOMES, "Geonosis Mountains", 116).setRequiresMcRestart(true).getInt();
	        biomeGeonosisMesaId = config.get(CAT_BIOMES, "Geonosis Mesa", 117).setRequiresMcRestart(true).getInt();
	        biomeHothId = config.get(CAT_BIOMES, "Hoth", 118).setRequiresMcRestart(true).getInt();
	        biomeHothMountainsId = config.get(CAT_BIOMES, "Hoth Mountains", 119).setRequiresMcRestart(true).getInt();
	        biomeIlumMountainsId = config.get(CAT_BIOMES, "Ilum Mountains", 120).setRequiresMcRestart(true).getInt();
	        biomeIlumPlainsId = config.get(CAT_BIOMES, "Ilum Plains", 121).setRequiresMcRestart(true).getInt();
	        biomeJakkuId = config.get(CAT_BIOMES, "Jakku", 122).setRequiresMcRestart(true).getInt();
	        biomeKaminoId = config.get(CAT_BIOMES, "Kamino", 123).setRequiresMcRestart(true).getInt();
	        biomeKashyyykId = config.get(CAT_BIOMES, "Kashyyyk", 152).setRequiresMcRestart(true).getInt();
	        biomeKesselDirt1Id = config.get(CAT_BIOMES, "Kessel Dusty Flats", 124).setRequiresMcRestart(true).getInt();
	        biomeKesselDirt2Id = config.get(CAT_BIOMES, "Kessel Arid Hills", 125).setRequiresMcRestart(true).getInt();
	        biomeKesselMudId = config.get(CAT_BIOMES, "Kessel Mudlands", 126).setRequiresMcRestart(true).getInt();
	        biomeKesselMountainsId = config.get(CAT_BIOMES, "Kessel Mountains", 127).setRequiresMcRestart(true).getInt();
	        biomeKesselRock1Id = config.get(CAT_BIOMES, "Kessel Stone Cliffs", 128).setRequiresMcRestart(true).getInt();
	        biomeKesselRock2Id = config.get(CAT_BIOMES, "Kessel Rocky Hills", 169).setRequiresMcRestart(true).getInt();
	        biomeKorribanDesertId = config.get(CAT_BIOMES, "Korriban Desert", 170).setRequiresMcRestart(true).getInt();
	        biomeKorribanMountainsId = config.get(CAT_BIOMES, "Korriban Mountains", 171).setRequiresMcRestart(true).getInt();
	        biomeManaanId = config.get(CAT_BIOMES, "Manaan", 172).setRequiresMcRestart(true).getInt();
	        biomeMandaloreId = config.get(CAT_BIOMES, "Mandalore", 173).setRequiresMcRestart(true).getInt();
	        biomeMimbanId = config.get(CAT_BIOMES, "Mimban Rainforest", 187).setRequiresMcRestart(true).getInt();
	        biomeMustafarLavaLakesId = config.get(CAT_BIOMES, "Mustafar Lava Lakes", 174).setRequiresMcRestart(true).getInt();
	        biomeMustafarPlateauId = config.get(CAT_BIOMES, "Mustafar Mountains", 135).setRequiresMcRestart(true).getInt();
	        biomeNabooGreatPlainsId = config.get(CAT_BIOMES, "Naboo Great Plains", 136).setRequiresMcRestart(true).getInt();
	        biomeNabooMountainsId = config.get(CAT_BIOMES, "Naboo Mountains", 137).setRequiresMcRestart(true).getInt();
	        biomeNabooPlainsId = config.get(CAT_BIOMES, "Naboo Hills", 138).setRequiresMcRestart(true).getInt();
	        biomeNiamosId = config.get(CAT_BIOMES, "Niamos", 168).setRequiresMcRestart(true).getInt();
	        biomeNurId = config.get(CAT_BIOMES, "Nur", 139).setRequiresMcRestart(true).getInt();
	        biomeRakataPrimeId = config.get(CAT_BIOMES, "Rakata Prime", 153).setRequiresMcRestart(true).getInt();
	        biomeRylothId = config.get(CAT_BIOMES, "Ryloth Dusty Flats", 175).setRequiresMcRestart(true).getInt();
	        biomeRylothPlateauId = config.get(CAT_BIOMES, "Ryloth Plateau", 141).setRequiresMcRestart(true).getInt();
	        biomeScarifId = config.get(CAT_BIOMES, "Scarif", 142).setRequiresMcRestart(true).getInt();
	        biomeTarisId = config.get(CAT_BIOMES, "Taris", 150).setRequiresMcRestart(true).getInt();
	        biomeTatooineDesertId = config.get(CAT_BIOMES, "Tatooine Dune Sea", 143).setRequiresMcRestart(true).getInt();
	        biomeTatooineMountainsId = config.get(CAT_BIOMES, "Tatooine Mountains", 144).setRequiresMcRestart(true).getInt();
	        biomeTythonForestId = config.get(CAT_BIOMES, "Tython Forest", 145).setRequiresMcRestart(true).getInt();
	        biomeTythonMountainsId = config.get(CAT_BIOMES, "Tython Mountains", 146).setRequiresMcRestart(true).getInt();
	        biomeUtapauId = config.get(CAT_BIOMES, "Utapau", 182).setRequiresMcRestart(true).getInt();
	        biomeVandorId = config.get(CAT_BIOMES, "Vandor", 147).setRequiresMcRestart(true).getInt();
	        biomeVjunId = config.get(CAT_BIOMES, "Vjun", 148).setRequiresMcRestart(true).getInt();
	        biomeWorldBetweenWorldsId = config.get(CAT_BIOMES, "World Between Worlds", 188).setRequiresMcRestart(true).getInt();
	        biomeYavin4Id = config.get(CAT_BIOMES, "Yavin 4", 154).setRequiresMcRestart(true).getInt();
	        
	        //next ID: 189
	        
			ConfigOptions.getCategory(CAT_COMPAT).setComment("Compatibility");
			ConfigOptions.getCategory(CAT_DIM).setComment("Dimension IDs");
			ConfigOptions.getCategory(CAT_BIOMES).setComment("Biome IDs");
		}

		public static int getDimId(String planetName) {
			planetName = planetName.toLowerCase();

			switch (planetName) {
			case "abednedo":
				return dimAbednedoId;
			case "ahchto":
				return dimAhchToId;
			case "ajankloss":
				return dimAjanKlossId;
			case "alderaan":
				return dimAlderaanId;
			case "anoat":
				return dimAnoatId;
			case "arvala7":
				return dimArvala7Id;
			case "athulla":
				return dimAthullaId;
			case "aurea":
				return dimAureaId;
			case "bahryn":
				return dimBahrynId;
			case "bakura":
				return dimBakuraId;
			case "balosar":
				return dimBalosarId;
			case "batuu":
				return dimBatuuId;
			case "bespin":
				return dimBespinId;
			case "bestine":
				return dimBestineId;
			case "bogano":
				return dimBoganoId;
			case "bothawui":
				return dimBothawuiId;
			case "bracca":
				return dimBraccaId;
			case "byss":
				return dimByssId;
			case "cantonica":
				return dimCantonicaId;
			case "carida":
				return dimCaridaId;
			case "catoneimoidia":
				return dimCatoNeimoidiaId;
			case "chandrila":
				return dimChandrilaId;
			case "cholganna":
				return dimCholgannaId;
			case "concorddawn":
				return dimConcordDawnId;
			case "concordia":
				return dimConcordiaId;
			case "corellia":
				return dimCorelliaId;
			case "corfai":
				return dimCorfaiId;
			case "coruscant":
				return dimCoruscantId;
			case "crait":
				return dimCraitId;
			case "cristophsis":
				return dimChristophsisId;
			case "csilla":
				return dimCsillaId;
			case "dagobah":
				return dimDagobahId;
			case "dantooine":
				return dimDantooineId;
			case "dathomir":
				return dimDathomirId;
			case "deathstar":
				return dimDeathStarId;
			case "devaron":
				return dimDevaronId;
			case "dosuun":
				return dimDosuunId;
			case "dqar":
				return dimDQarId;
			case "drall":
				return dimDrallId;
			case "dromundkaas":
				return dimDromundkaasId;
			case "duro":
				return dimDuroId;
			case "eadu":
				return dimEaduId;
			case "earth":
				return 0;
			case "empressteta":
				return dimEmpresstetaId;
			case "endor":
				return dimEndorId;
			case "exegol":
				return dimExegolId;
			case "felucia":
				return dimFeluciaId;
			case "florrum":
				return dimFlorrumId;
			case "fondor":
				return dimFondorId;
			case "formos":
				return dimFormosId;
			case "froz":
				return dimFrozId;
			case "gamorr":
				return dimGamorrId;
			case "geonosis":
				return dimGeonosisId;
			case "hapes":
				return dimHapesId;
			case "hosnianprime":
				return dimHosnianPrimeId;
			case "hoth":
				return dimHothId;
			case "hurikane":
				return 33;
			case "iego":
				return dimIegoId;
			case "ilum":
				return dimIlumId;
			case "jakku":
				return dimJakkuId;
			case "jedha":
				return dimJedhaId;
			case "jestefad":
				return dimJestefadId;
			case "kamino":
				return dimKaminoId;
			case "kashyyyk":
				return dimKashyyykId;
			case "kefbir":
				return dimKefbirId;
			case "kessel":
				return dimKesselId;
			case "kijimi":
				return dimKijimiId;
			case "korriban":
				return dimKorribanId;
			case "krownest":
				return dimKrownestId;
			case "kuat":
				return dimKuatId;
			case "lahmu":
				return dimLahmuId;
			case "lothal":
				return dimLothalId;
			case "malachor":
				return dimMalachorId;
			case "malastare":
				return dimMalastareId;
			case "manaan":
				return dimManaanId;
			case "mandalore":
				return dimMandaloreId;
			case "mimban":
				return dimMimbanId;
			case "moncalamari":
				return dimMonCalamariId;
			case "mortis":
				return dimMortisId;
			case "mustafar":
				return dimMustafarId;
			case "mygeeto":
				return dimMygeetoId;
			case "naboo":
				return dimNabooId;
			case "nalhutta":
				return dimNalhuttaId;
			case "nevarro":
				return dimNevarroId;
			case "niamos":
				return dimNiamosId;
			case "nubia":
				return dimNubiaId;
			case "numidianprime":
				return dimNumidianPrimeId;
			case "nur":
				return dimNurId;
			case "onderon":
				return dimOnderonId;
			case "ordmantell":
				return dimOrdMantellId;
			case "pasaana":
				return dimPasaanaId;
			case "pillio":
				return dimPillioId;
			case "polismassa":
				return dimPolisMassaId;
			case "rakataprime":
				return dimRakataPrimeId;
			case "raxusprime":
				return dimRaxusprimeId;
			case "rhenvar":
				return dimRhenVarId;
			case "riflor":
				return dimRiflorId;
			case "rodia":
				return dimRodiaId;
			case "ryloth":
				return dimRylothId;
			case "sacorria":
				return dimSacorriaId;
			case "saleucami":
				return dimSaleucamiId;
			case "savareen":
				return dimSavareenId;
			case "scarif":
				return dimScarifId;
			case "selonia":
				return dimSeloniaId;
			case "sorgan":
				return dimSorganId;
			case "sullust":
				return dimSullustId;
			case "takodana":
				return dimTakodanaId;
			case "talus":
				return dimTalusId;
			case "taris":
				return dimTarisId;
			case "tatooine":
				return dimTatooineId;
			case "telosiv":
				return dimTelosIVId;
			case "tralus":
				return dimTralusId;
			case "trandosha":
				return dimTrandoshaId;
			case "tython":
				return dimTythonId;
			case "umbara":
				return dimUmbaraId;
			case "utapau":
				return dimUtapauId;
			case "vagrand":
				return dimVagrandId;
			case "vandor":
				return dimVandorId;
			case "vjun":
				return dimVjunId;
			case "wobani":
				return dimWobaniId;
			case "worldbetweenworlds":
				return dimWorldBetweenWorldsId;
			case "xyquineiii":
				return dimXyquineIIIId;
			case "yavin4":
				return dimYavin4Id;
			case "zeffo":
				return dimZeffoId;
			default:
				throw new IllegalArgumentException("Unknown planet: " + planetName);
			}
		}
	}
}
