package com.sown.outerrim.utils;

public class BoundingComponent {
    public final int dx, dy, dz;
    public final float minX, minY, minZ, maxX, maxY, maxZ;

    public BoundingComponent(int dx, int dy, int dz) {
        this.dx = dx; this.dy = dy; this.dz = dz;
        this.minX = 0; this.minY = 0; this.minZ = 0;
        this.maxX = 1; this.maxY = 1; this.maxZ = 1;
    }

    public BoundingComponent(int dx, int dy, int dz, float maxY) {
        this.dx = dx; this.dy = dy; this.dz = dz;
        this.minX = 0; this.minY = 0; this.minZ = 0;
        this.maxX = 1; this.maxY = maxY; this.maxZ = 1;
    }

    public BoundingComponent(int dx, int dy, int dz, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.dx = dx; this.dy = dy; this.dz = dz;
        this.minX = minX; this.minY = minY; this.minZ = minZ;
        this.maxX = maxX; this.maxY = maxY; this.maxZ = maxZ;
    }

    @Override
    public String toString() {
        return "Part{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", dz=" + dz +
                ", minX=" + minX +
                ", minY=" + minY +
                ", minZ=" + minZ +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", maxZ=" + maxZ +
                '}';
    }
}
