package com.sown.outerrim.fluids;

import net.minecraftforge.fluids.Fluid;

public class FluidCustomLiquid extends Fluid {
    private boolean isAcid;
    private boolean repairsArmor;
    private boolean preventsDrowning;
    private boolean healsPlayer;

    public FluidCustomLiquid(String fluidName) {
        super(fluidName);
    }

    @Override
    public Fluid setLuminosity(int luminosity) {
        this.luminosity = luminosity;
        return this;
    }

    @Override
    public Fluid setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public Fluid setDensity(int density) {
        this.density = density;
        return this;
    }

    @Override
    public Fluid setViscosity(int viscosity) {
        this.viscosity = viscosity;
        return this;
    }

    public boolean getIsAcid() {
        return isAcid;
    }

    public void setIsAcid(boolean isAcid) {
        this.isAcid = isAcid;
    }

    public boolean getRepairsArmor() {
        return repairsArmor;
    }

    public void setRepairsArmor(boolean repairsArmor) {
        this.repairsArmor = repairsArmor;
    }

    public boolean getPreventsDrowning() {
        return preventsDrowning;
    }

    public void setPreventsDrowning(boolean preventsDrowning) {
        this.preventsDrowning = preventsDrowning;
    }

    public boolean getHealsPlayer() {
        return healsPlayer;
    }

    public void setHealsPlayer(boolean healsPlayer) {
        this.healsPlayer = healsPlayer;
    }
}
