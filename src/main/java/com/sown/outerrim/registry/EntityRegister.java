package com.sown.outerrim.registry;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.entities.EntityAstromech;
import com.sown.outerrim.entities.EntityB2BattleDroid;
import com.sown.outerrim.entities.EntityBattleDroid;
import com.sown.outerrim.entities.EntityCaptainRex;
import com.sown.outerrim.entities.EntityCountDooku;
import com.sown.outerrim.entities.EntityDarthVader;
import com.sown.outerrim.entities.EntityDroideka;
import com.sown.outerrim.entities.EntityEwok;
import com.sown.outerrim.entities.EntityFalumpaset;
import com.sown.outerrim.entities.EntityGM12L1;
import com.sown.outerrim.entities.EntityKaadu;
import com.sown.outerrim.entities.EntityKesselMineWorker;
import com.sown.outerrim.entities.EntityLaserProjectile;
import com.sown.outerrim.entities.EntityLaserProjectileRed;
import com.sown.outerrim.entities.EntityMimbaneseSoldier;
import com.sown.outerrim.entities.EntityPelikki;
import com.sown.outerrim.entities.EntityPykeSentinel;
import com.sown.outerrim.entities.EntityReconTrooper;
import com.sown.outerrim.entities.EntityReconTrooperBase;
import com.sown.outerrim.entities.EntityReconTrooper187th;
import com.sown.outerrim.entities.EntityReconTrooper212th;
import com.sown.outerrim.entities.EntityReconTrooper41st;
import com.sown.outerrim.entities.EntityReconTrooper501st;
import com.sown.outerrim.entities.EntityReconTrooperCoruscantGuard;
import com.sown.outerrim.entities.EntityRustyB2BattleDroid;
import com.sown.outerrim.entities.EntitySandBeast;
import com.sown.outerrim.entities.EntitySmugglerHanSolo;
import com.sown.outerrim.entities.EntityTuskenRaider;
import com.sown.outerrim.entities.EntityWookiee;
import com.sown.outerrim.entities.MobAlex;
import com.sown.outerrim.entities.MobBantha;
import com.sown.outerrim.entities.MobBogRat;
import com.sown.outerrim.entities.MobCloneTrooper187th;
import com.sown.outerrim.entities.MobCloneTrooper212thPhase2;
import com.sown.outerrim.entities.MobCloneTrooper41stPhase2;
import com.sown.outerrim.entities.MobCloneTrooper501stPhase2;
import com.sown.outerrim.entities.EntityCloneTrooperBase;
import com.sown.outerrim.entities.MobCloneTrooperCoruscantGuard;
import com.sown.outerrim.entities.MobCloneTrooperP1;
import com.sown.outerrim.entities.MobCloneTrooperPhase2;
import com.sown.outerrim.entities.MobCoruscantCommoner;
import com.sown.outerrim.entities.MobInquisitor;
import com.sown.outerrim.entities.EntityNightsister;
import com.sown.outerrim.entities.MobSteve;
import com.sown.outerrim.entities.EntityJabba;
import com.sown.outerrim.entities.MobTatooineCommoner;
import com.sown.outerrim.entities.MobTest;
import com.sown.outerrim.entities.MobYoda;
import com.sown.outerrim.entities.EntityZabrak;
import com.sown.util.entity.EntityUtils;

import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityRegister {
	public static void registerAll() {
	    //EntityUtils.registerWithSpawnEgg(MobSteve.class, "steve", 0204204, 17912194);
	    //EntityUtils.registerWithSpawnEgg(MobAlex.class, "alex", 0214204, 17942194);
	    EntityUtils.registerWithSpawnEgg(MobTatooineCommoner.class, "tatooinecommoner", 9206099, 13882323);
	    EntityUtils.registerWithSpawnEgg(MobCoruscantCommoner.class, "coruscantcommoner", 9206099, 16711680);
	    EntityUtils.registerWithSpawnEgg(MobInquisitor.class, "inquisitor", 1315860, 13182323);

	    // Clone Troopers (organized by legion)
	    EntityUtils.registerWithSpawnEgg(MobCloneTrooperPhase2.class, "cloneTrooperPhase2", 16777215, 657930);  // Plain Phase 2
	    EntityUtils.registerWithSpawnEgg(EntityReconTrooper.class, "reconTrooper", 16777215, 657930);
	    
	    // 501st Legion
	    EntityUtils.registerWithSpawnEgg(MobCloneTrooper501stPhase2.class, "cloneTrooper501stPhase2", 16777215, 6126539);
	    EntityUtils.registerWithSpawnEgg(EntityReconTrooper501st.class, "reconTrooper501st", 16777215, 6126539);
	    registerCloneTrooper(EntityCaptainRex.class, "captain_rex", 16777215, 6126539);

	    // 212th Legion
	    EntityUtils.registerWithSpawnEgg(MobCloneTrooper212thPhase2.class, "cloneTrooper212thPhase2", 16777215, 16749877);
	    EntityUtils.registerWithSpawnEgg(EntityReconTrooper212th.class, "reconTrooper212th", 16777215, 16749877);

	    // 41st Legion
	    EntityUtils.registerWithSpawnEgg(MobCloneTrooper41stPhase2.class, "cloneTrooper41stPhase2", 16777215, 4949598);
	    EntityUtils.registerWithSpawnEgg(EntityReconTrooper41st.class, "reconTrooper41st", 16777215, 4949598);

	    // 187th Legion
	    registerCloneTrooper(MobCloneTrooper187th.class, "cloneTrooper187thPhase2", 16777215, 9643200);
	    registerReconTrooper(EntityReconTrooper187th.class, "reconTrooper187th", 16777215, 9643200);

	    // Coruscant Guard
	    registerCloneTrooper(MobCloneTrooperCoruscantGuard.class, "cloneTrooperCoruscantGuardPhase2", 16777215, 10827310);
	    registerReconTrooper(EntityReconTrooperCoruscantGuard.class, "reconTrooperCoruscantGuard", 16777215, 10827310);

	    // Droids & Mechanical Units
	    EntityUtils.registerWithSpawnEgg(EntityBattleDroid.class, "b1_battle_droid", 15919048, 13944998);
	    EntityUtils.registerWithSpawnEgg(EntityB2BattleDroid.class, "b2_battle_droid", 6778741, 3949383);
	    EntityUtils.registerWithSpawnEgg(EntityRustyB2BattleDroid.class, "rusty_b2_battle_droid", 6778741, 7753781);
	    EntityUtils.registerWithSpawnEgg(EntityDroideka.class, "droideka", 8933692, 6514281);
	    EntityUtils.registerWithSpawnEgg(EntityAstromech.class, "astromech", 8749954, 13948116);

	    // Creatures & Fauna
	    EntityUtils.registerWithSpawnEgg(EntityKaadu.class, "kaadu", 15253896, 10048049);
	    EntityUtils.registerWithSpawnEgg(EntityFalumpaset.class, "falumpaset", 12425592, 7428159);
	    EntityUtils.registerWithSpawnEgg(EntityPelikki.class, "pelikki", 13215340, 9005110);
	    EntityUtils.registerWithSpawnEgg(EntityEwok.class, "ewok", 8680030, 4995880);
	    EntityUtils.registerWithSpawnEgg(EntityWookiee.class, "wookiee", 8544557, 3813666);
	    EntityUtils.registerWithSpawnEgg(EntityKesselMineWorker.class, "kessel_mine_worker", 16176518, 16183015);
	    EntityUtils.registerWithSpawnEgg(EntityPykeSentinel.class, "pyke_sentinel", 10059591, 4211524);
	    EntityUtils.registerWithSpawnEgg(EntityGM12L1.class, "gm12_l1", 3626586, 13476960);
	    EntityUtils.registerWithSpawnEgg(EntitySmugglerHanSolo.class, "smuggler_han_solo", 12356716, 4535591);
	    EntityUtils.registerWithSpawnEgg(EntityCountDooku.class, "count_dooku", 2169118, 5581867);
	    EntityUtils.registerWithSpawnEgg(EntityDarthVader.class, "darth_vader", 1513239, 10066329);
	    //EntityUtils.registerWithSpawnEgg(MobBogRat.class, "bograt", 12500137, 6770255);
	    EntityUtils.registerWithSpawnEgg(EntityTuskenRaider.class, "tusken_raider", 15064524, 13544571);
	    EntityUtils.registerWithSpawnEgg(EntityMimbaneseSoldier.class, "mimbanese_soldier", 9445166, 10654587);
	    //EntityUtils.registerWithSpawnEgg(EntitySandBeast.class, "sand_beast", 8749954, 13948116);  // Unused?

	    // Notable Characters
	    EntityUtils.registerWithSpawnEgg(EntityJabba.class, "jabba", 3882801, 11966340);
	    EntityUtils.registerWithSpawnEgg(MobYoda.class, "yoda", 10264388, 6645294);
	    EntityUtils.registerWithSpawnEgg(EntityZabrak.class, "dathomirian", 10301999, 1315860);
	    EntityUtils.registerWithSpawnEgg(EntityNightsister.class, "nightsister", 13355979, 8198912);

	    // Projectile Entities
	    EntityRegistry.registerModEntity(EntityLaserProjectile.class, "laserProjectile", 1, OuterRim.instance, 64, 10, true);
	    EntityRegistry.registerModEntity(EntityLaserProjectileRed.class, "laserProjectileRed", 2, OuterRim.instance, 64, 10, true);
	}


    private static void registerCloneTrooper(Class<? extends EntityCloneTrooperBase> trooperClass, String name, int primaryColor, int secondaryColor) {
        EntityUtils.registerWithSpawnEgg(trooperClass, name, primaryColor, secondaryColor);
    }

    private static void registerReconTrooper(Class<? extends EntityReconTrooperBase> trooperClass, String name, int primaryColor, int secondaryColor) {
        EntityUtils.registerWithSpawnEgg(trooperClass, name, primaryColor, secondaryColor);
    }
}