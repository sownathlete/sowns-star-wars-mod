package com.sown.util.structures;

public class StructureBlock {
    public int x, y, z;
    public String blockName;
    public int meta;

    public StructureBlock(int x, int y, int z, String blockName, int meta) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockName = blockName;
        this.meta = meta;
    }
}