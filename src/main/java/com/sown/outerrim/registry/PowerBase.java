/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.sown.outerrim.registry;

import com.sown.util.ui.LangUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PowerBase {
    public int currentLevel = 0;
    public int maxLevel = 5;
    public String name = "";
    public String unlocalizedName = "";
    public String unlocalizedDescription = "";
    public float rechargeTime = 0.0f;
    public float recharge = 0.0f;
    public int costMult = 0;
    public int costBase = 0;
    public int duration = 0;
    public int durationBase = 0;
    public int durationMult = 0;
    public int healthBase = 0;
    public int healthMult = 0;
    public int rangeBase = 0;
    public int rangeMult = 0;
    public boolean isRunning = false;
    public int health = 0;
    public boolean isDurationBased = false;

    public PowerBase(String name) {
        this.unlocalizedName = "force.power." + name;
        this.unlocalizedDescription = this.unlocalizedName + ".desc";
        this.name = name;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("currentLevel", this.currentLevel);
        compound.setInteger("maxLevel", this.maxLevel);
        compound.setString("name", this.name);
        compound.setString("unlocalizedName", this.unlocalizedName);
        compound.setString("unlocalizedDescription", this.unlocalizedDescription);
        compound.setFloat("rechargeTime", this.rechargeTime);
        compound.setFloat("recharge", this.recharge);
        compound.setInteger("costMult", this.costMult);
        compound.setInteger("costBase", this.costBase);
        compound.setInteger("duration", this.duration);
        compound.setInteger("durationBase", this.durationBase);
        compound.setInteger("durationMult", this.durationMult);
        compound.setInteger("healthBase", this.healthBase);
        compound.setInteger("healthMult", this.healthMult);
        compound.setInteger("rangeBase", this.rangeBase);
        compound.setInteger("rangeMult", this.rangeMult);
        compound.setInteger("health", this.health);
        compound.setBoolean("isRunning", this.isRunning);
        return compound;
    }

    public String toString() {
        return this.serialize().toString();
    }

    public PowerBase deserialize(NBTTagCompound compound) {
        this.currentLevel = compound.getInteger("currentLevel");
        this.maxLevel = compound.getInteger("maxLevel");
        this.name = compound.getString("name");
        this.unlocalizedName = compound.getString("unlocalizedName");
        this.unlocalizedDescription = compound.getString("unlocalizedDescription");
        this.rechargeTime = compound.getFloat("rechargeTime");
        this.recharge = compound.getFloat("recharge");
        this.costMult = compound.getInteger("costMult");
        this.costBase = compound.getInteger("costBase");
        this.duration = compound.getInteger("duration");
        this.durationBase = compound.getInteger("durationBase");
        this.durationMult = compound.getInteger("durationMult");
        this.healthBase = compound.getInteger("healthBase");
        this.healthMult = compound.getInteger("healthMult");
        this.rangeBase = compound.getInteger("rangeBase");
        this.rangeMult = compound.getInteger("rangeMult");
        this.health = compound.getInteger("health");
        this.isRunning = compound.getBoolean("isRunning");
        return this;
    }

    public int getCost() {
        return this.costBase + this.costMult * (this.currentLevel - 1);
    }

    public int getCostForLevel(int level) {
        return this.costBase + this.costMult * (level - 1);
    }

    public int getDamage() {
        return this.healthBase + this.healthMult * (this.currentLevel - 1);
    }

    public int getDamageForLevel(int level) {
        return this.healthBase + this.healthMult * (level - 1);
    }

    public int getDuration() {
        return this.durationBase + this.durationMult * (this.currentLevel - 1);
    }

    public int getDurationForLevel(int level) {
        return this.durationBase + this.durationMult * (level - 1);
    }

    public String getLocalizedDesc() {
        return LangUtils.translateKey(this.unlocalizedDescription);
    }

    public String getLocalizedName() {
        return LangUtils.translateKey(this.unlocalizedName);
    }

    public int getRange() {
        return this.rangeBase + this.rangeMult * (this.currentLevel - 1);
    }

    public int getRangeForLevel(int level) {
        return this.rangeBase + this.rangeMult * (level - 1);
    }

    public boolean run(EntityPlayer player) {
        return false;
    }
}

