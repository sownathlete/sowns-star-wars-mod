/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.sown.outerrim.entities;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class AiFreqMove
extends EntityAIBase {
    private EntityLiving entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private int freq;
    private int maxDistance = 10;
    private Block preferredBlock = Blocks.grass;

    public AiFreqMove(EntityLiving entity, double speed, int freq) {
        this.entity = entity;
        this.speed = speed;
        this.freq = freq;
        this.setMutexBits(1);
    }

    public AiFreqMove(EntityLiving entity, double speed, int freq, int maxDist, Block preferredBlock) {
        this.entity = entity;
        this.speed = speed;
        this.freq = freq;
        this.maxDistance = maxDist;
        this.preferredBlock = preferredBlock;
        this.setMutexBits(1);
    }

    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    private Vec3 findRandomTarget(EntityLiving entity, int maxDistanceX, int maxDistanceY) {
        Random random = entity.getRNG();
        boolean flag = false;
        int k = 0;
        int l = 0;
        int i1 = 0;
        float f = -99999.0f;
        for (int l1 = 0; l1 < 10; ++l1) {
            int j1 = random.nextInt(2 * maxDistanceX) - maxDistanceX;
            int i2 = random.nextInt(2 * maxDistanceY) - maxDistanceY;
            int k1 = random.nextInt(2 * maxDistanceX) - maxDistanceX;
            float f1 = this.getBlockPathWeight(entity, j1 += MathHelper.floor_double((double)entity.posX), i2 += MathHelper.floor_double((double)entity.posY), k1 += MathHelper.floor_double((double)entity.posZ));
            if (!(f1 > f)) continue;
            f = f1;
            k = j1;
            l = i2;
            i1 = k1;
            flag = true;
        }
        if (flag) {
            return Vec3.createVectorHelper((double)k, (double)l, (double)i1);
        }
        return null;
    }

    private float getBlockPathWeight(EntityLiving entity, int par1, int par2, int par3) {
        return entity.worldObj.getBlock(par1, par2 - 1, par3) == this.preferredBlock ? 0.0f : entity.worldObj.getLightBrightness(par1, par2, par3) - 0.5f;
    }

    public boolean shouldExecute() {
        if (this.freq == 0 || this.entity.getRNG().nextInt(this.freq) == 0) {
            Vec3 vec3 = this.findRandomTarget(this.entity, this.maxDistance, this.maxDistance);
            if (vec3 == null) {
                return false;
            }
            this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            return true;
        }
        return false;
    }

    @Override
    public void startExecuting() {
        this.entity.setSneaking(true);  // Start sneaking when moving.
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.entity.setSneaking(false);  // Stop sneaking when task ends.
    }
}

