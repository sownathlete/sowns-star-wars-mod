package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.IORWorldGenerator;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGeneratorOR extends WorldGenerator implements IORWorldGenerator {
  public WorldGeneratorOR(boolean doBlockNotify) {
    super(doBlockNotify);
  }
  
  public WorldGeneratorOR() {
    this(false);
  }
  
  public abstract boolean generate(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3);
}
