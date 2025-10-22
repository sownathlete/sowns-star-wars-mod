/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;

import java.util.Random;

public class NoiseUtil {
    int[] perm = new int[512];
    public float[][] grad2d = new float[][]{{1.0f, 0.0f}, {0.9239f, 0.3827f}, {0.707107f, 0.707107f}, {0.3827f, 0.9239f}, {0.0f, 1.0f}, {-0.3827f, 0.9239f}, {-0.707107f, 0.707107f}, {-0.9239f, 0.3827f}, {-1.0f, 0.0f}, {-0.9239f, -0.3827f}, {-0.707107f, -0.707107f}, {-0.3827f, -0.9239f}, {0.0f, -1.0f}, {0.3827f, -0.9239f}, {0.707107f, -0.707107f}, {0.9239f, -0.3827f}};
    public int[][] grad3d = new int[][]{{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}, {1, 1, 0}, {-1, 1, 0}, {0, -1, 1}, {0, -1, -1}};

    public NoiseUtil(long seed) {
        int i;
        Random rand = new Random(seed);
        for (i = 0; i < 256; ++i) {
            this.perm[i] = i;
        }
        for (i = 0; i < 256; ++i) {
            int j = rand.nextInt(256);
            this.perm[i] = this.perm[i] ^ this.perm[j];
            this.perm[j] = this.perm[i] ^ this.perm[j];
            this.perm[i] = this.perm[i] ^ this.perm[j];
        }
        System.arraycopy(this.perm, 0, this.perm, 256, 256);
    }

    private static float lerp(float x, float y, float n) {
        return x + n * (y - x);
    }

    private static int fastFloor(float x) {
        return x > 0.0f ? (int)x : (int)x - 1;
    }

    private static float fade(float n) {
        return n * n * n * (n * (n * 6.0f - 15.0f) + 10.0f);
    }

    private static float dot2(float[] grad2, float x, float y) {
        return grad2[0] * x + grad2[1] * y;
    }

    private static float dot3(int[] grad3, float x, float y, float z) {
        return grad3[0] * x + grad3[1] * y + grad3[2] * z;
    }

    public float noise2d(float x, float y) {
        int largeX = x > 0.0f ? (int)x : (int)x - 1;
        int largeY = y > 0.0f ? (int)y : (int)y - 1;
        x -= largeX;
        y -= largeY;
        float u = x * x * x * (x * (x * 6.0f - 15.0f) + 10.0f);
        float v = y * y * y * (y * (y * 6.0f - 15.0f) + 10.0f);
        int randY = this.perm[largeY &= 0xFF] + (largeX &= 0xFF);
        int randY1 = this.perm[largeY + 1] + largeX;
        float[] grad2 = this.grad2d[this.perm[randY] & 0xF];
        float grad00 = grad2[0] * x + grad2[1] * y;
        grad2 = this.grad2d[this.perm[randY1] & 0xF];
        float grad01 = grad2[0] * x + grad2[1] * (y - 1.0f);
        grad2 = this.grad2d[this.perm[1 + randY1] & 0xF];
        float grad11 = grad2[0] * (x - 1.0f) + grad2[1] * (y - 1.0f);
        grad2 = this.grad2d[this.perm[1 + randY] & 0xF];
        float grad10 = grad2[0] * (x - 1.0f) + grad2[1] * y;
        float lerpX0 = grad00 + u * (grad10 - grad00);
        return lerpX0 + v * (grad01 + u * (grad11 - grad01) - lerpX0);
    }

    public float noise3d(float x, float y, float z) {
        int unitX = x > 0.0f ? (int)x : (int)x - 1;
        int unitY = y > 0.0f ? (int)y : (int)y - 1;
        int unitZ = z > 0.0f ? (int)z : (int)z - 1;
        x -= unitX;
        y -= unitY;
        z -= unitZ;
        float u = x * x * x * (x * (x * 6.0f - 15.0f) + 10.0f);
        float v = y * y * y * (y * (y * 6.0f - 15.0f) + 10.0f);
        float w = z * z * z * (z * (z * 6.0f - 15.0f) + 10.0f);
        int randZ = this.perm[unitZ &= 0xFF] + (unitY &= 0xFF);
        int randZ1 = this.perm[unitZ + 1] + unitY;
        int randYZ = this.perm[randZ] + (unitX &= 0xFF);
        int randY1Z = this.perm[1 + randZ] + unitX;
        int randYZ1 = this.perm[randZ1] + unitX;
        int randY1Z1 = this.perm[1 + randZ1] + unitX;
        int[] grad3 = this.grad3d[this.perm[randYZ] & 0xF];
        float grad000 = grad3[0] * x + grad3[1] * y + grad3[2] * z;
        grad3 = this.grad3d[this.perm[1 + randYZ] & 0xF];
        float grad100 = grad3[0] * (x - 1.0f) + grad3[1] * y + grad3[2] * z;
        grad3 = this.grad3d[this.perm[randY1Z] & 0xF];
        float grad010 = grad3[0] * x + grad3[1] * (y - 1.0f) + grad3[2] * z;
        grad3 = this.grad3d[this.perm[1 + randY1Z] & 0xF];
        float grad110 = grad3[0] * (x - 1.0f) + grad3[1] * (y - 1.0f) + grad3[2] * z;
        grad3 = this.grad3d[this.perm[randYZ1] & 0xF];
        float grad001 = grad3[0] * x + grad3[1] * y + grad3[2] * (z -= 1.0f);
        grad3 = this.grad3d[this.perm[1 + randYZ1] & 0xF];
        float grad101 = grad3[0] * (x - 1.0f) + grad3[1] * y + grad3[2] * z;
        grad3 = this.grad3d[this.perm[randY1Z1] & 0xF];
        float grad011 = grad3[0] * x + grad3[1] * (y - 1.0f) + grad3[2] * z;
        grad3 = this.grad3d[this.perm[1 + randY1Z1] & 0xF];
        float grad111 = grad3[0] * (x - 1.0f) + grad3[1] * (y - 1.0f) + grad3[2] * z;
        float f1 = grad000 + u * (grad100 - grad000);
        float f2 = grad010 + u * (grad110 - grad010);
        float f3 = grad001 + u * (grad101 - grad001);
        float f4 = grad011 + u * (grad111 - grad011);
        float lerp1 = f1 + v * (f2 - f1);
        return lerp1 + w * (f3 + v * (f4 - f3) - lerp1);
    }
}

