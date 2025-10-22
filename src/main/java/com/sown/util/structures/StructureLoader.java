package com.sown.util.structures;

import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.io.*;
import java.util.*;

/**
 * Scans a cuboid and writes a ready-to-compile WorldGenerator subclass that
 *  • contains width/length checks
 *  • splits long outputs into helper methods (MAX_LINES)
 *  • compresses straight X-runs into simple for-loops
 *  • defines any non-vanilla blocks up-front via getBlockByString(...)
 */
public class StructureLoader {

    private static final File OUT_DIR    = new File("config/outerrim_structures_code");
    private static final int  MAX_LINES  = 1500;                 // helper split size

    public static void init() {
        if (!OUT_DIR.exists()) OUT_DIR.mkdirs();
    }

    /* ------------------------------------------------------------ */
    /*  MAIN ENTRY POINT                                            */
    /* ------------------------------------------------------------ */

    public static void saveStructureAsClass(World world,
                                            int x1, int y1, int z1,
                                            int x2, int y2, int z2,
                                            String className,
                                            String packageName) {

        /* --- Bounds --- */
        int minX = Math.min(x1, x2), maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2), maxY = Math.max(y1, y2);
        int minZ = Math.min(z1, z2), maxZ = Math.max(z1, z2);

        int width  = maxX - minX + 1;
        int length = maxZ - minZ + 1;

        /* --- Output file --- */
        File pkgDir = new File(OUT_DIR, packageName.replace('.', '/'));
        pkgDir.mkdirs();
        File outFile = new File(pkgDir, className + ".java");

        try (PrintWriter w = new PrintWriter(new FileWriter(outFile))) {

            /* ---------------------------------------------------- */
            /*  HEADER / IMPORTS                                    */
            /* ---------------------------------------------------- */
            w.printf("package %s;%n%n", packageName);
            w.println("import net.minecraft.block.Block;");
            w.println("import net.minecraft.init.Blocks;");
            w.println("import net.minecraft.world.World;");
            w.println("import net.minecraft.world.gen.feature.WorldGenerator;");
            w.println("import java.util.Random;");
            w.println();
            w.printf("public class %s extends WorldGenerator {%n", className);

            /* ---------------------------------------------------- */
            /*  PRE-DEFINE MOD BLOCKS (pass-1 scan)                 */
            /* ---------------------------------------------------- */
            Map<String, Set<String>> modBlocks = collectExternalBlocks(world,
                                                                       minX, minY, minZ,
                                                                       maxX, maxY, maxZ);

            if (!modBlocks.isEmpty()) {
                w.println("    /* -------- external-mod block look-ups -------- */");
                w.println("    private static Block getBlockByString(String id){");
                w.println("        return (Block)Block.blockRegistry.getObject(id);");
                w.println("    }");
                w.println();
            }

            /* ---------------------------------------------------- */
            /*  GENERATE METHOD HEADER                              */
            /* ---------------------------------------------------- */
            w.println("    @Override");
            w.println("    public boolean generate(World world, Random rand, int x, int y, int z) {");
            w.printf ("        int width = %d, length = %d;%n", width, length);
            w.println("        if (!canPlaceOnSolidGround(world, x, y, z, width, length)) return false;");
            w.println();

            // Emit variable definitions for external blocks
            if (!modBlocks.isEmpty()) {
                for (Map.Entry<String, Set<String>> mod : modBlocks.entrySet()) {
                    String modID = mod.getKey();
                    for (String id : mod.getValue()) {
                        w.printf("        Block %s_%s = getBlockByString(\"%s:%s\");%n",
                                 modID, id, modID, id);
                    }
                }
                w.println();
            }

            /* ---------------------------------------------------- */
            /*  BODY CALLS – one helper every MAX_LINES             */
            /* ---------------------------------------------------- */
            List<BlockRun> runs = collectRuns(world, minX, minY, minZ, maxX, maxY, maxZ);
            int runsPerPart = Math.max(1, runs.size() / ((runs.size()/MAX_LINES) + 1));
            int partCount   = 0;
            for (int i = 0; i < runs.size(); i += runsPerPart) {
                w.printf("        generatePart%d(world, x, y, z);%n", partCount++);
            }

            w.println("        return true;");
            w.println("    } // generate");
            w.println();

            /* ---------------------------------------------------- */
            /*  HELPER PARTS                                        */
            /* ---------------------------------------------------- */
            int runIdx = 0;
            for (int p = 0; p < partCount; p++) {
                w.printf("    private void generatePart%d(World w, int x, int y, int z){%n", p);
                for (int n = 0; n < runsPerPart && runIdx < runs.size(); n++, runIdx++) {
                    BlockRun r = runs.get(runIdx);
                    if (r.length > 1) {
                        w.printf("        for(int dx=%d; dx<%d; dx++) place(w, x+dx, y+%d, z+%d, %s, %d);%n",
                                 r.startX - minX,
                                 r.startX - minX + r.length,
                                 r.y - minY,
                                 r.z - minZ,
                                 r.blockRef(),
                                 r.meta);
                    } else {
                        w.printf("        place(w, x+%d, y+%d, z+%d, %s, %d);%n",
                                 r.startX - minX,
                                 r.y      - minY,
                                 r.z      - minZ,
                                 r.blockRef(),
                                 r.meta);
                    }
                }
                w.println("    }");
                w.println();
            }

            /* ---------------------------------------------------- */
            /*  LOW-LEVEL HELPERS                                   */
            /* ---------------------------------------------------- */
            w.println("    private static void place(World w,int x,int y,int z,Block b,int m){");
            w.println("        if(b!=null) w.setBlock(x,y,z,b,m,2);");
            w.println("    }");
            w.println();
            w.println("    private boolean canPlaceOnSolidGround(World w,int x,int y,int z,int W,int L){");
            w.println("        for(int dx=0;dx<W;dx++) for(int dz=0;dz<L;dz++){");
            w.println("            Block b=w.getBlock(x+dx,y-1,z+dz);");
            w.println("            if(b==null||b==Blocks.air) return false;");
            w.println("        }");
            w.println("        return true;");
            w.println("    }");
            w.println("}");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /* ------------------------------------------------------------ */
    /*  PASS-1 : gather non-vanilla blocks for variable creation     */
    /* ------------------------------------------------------------ */
    private static Map<String, Set<String>> collectExternalBlocks(World w,
                                                                  int minX,int minY,int minZ,
                                                                  int maxX,int maxY,int maxZ){
        Map<String, Set<String>> mods = new LinkedHashMap<>();
        for(int x=minX;x<=maxX;x++){
            for(int y=minY;y<=maxY;y++){
                for(int z=minZ;z<=maxZ;z++){
                    Block b = w.getBlock(x,y,z);
                    if(b==null||b==Blocks.air) continue;
                    String reg = Block.blockRegistry.getNameForObject(b);
                    if(reg==null) continue;
                    String[] p = reg.split(":",2);
                    if(p.length!=2) continue;
                    if("minecraft".equals(p[0])) continue;
                    mods.computeIfAbsent(p[0],k->new LinkedHashSet<>()).add(p[1]);
                }
            }
        }
        return mods;
    }

    /* ------------------------------------------------------------ */
    /*  PASS-2 : compress straight X-runs                           */
    /* ------------------------------------------------------------ */
    private static List<BlockRun> collectRuns(World w,
                                              int minX,int minY,int minZ,
                                              int maxX,int maxY,int maxZ){
        List<BlockRun> runs=new ArrayList<>();
        for(int z=minZ;z<=maxZ;z++){
            for(int y=minY;y<=maxY;y++){
                int runStart=minX;
                Block runBlock=w.getBlock(minX,y,z);
                int   runMeta =w.getBlockMetadata(minX,y,z);
                for(int x=minX+1;x<=maxX+1;x++){
                    Block b   =(x<=maxX? w.getBlock(x,y,z):null);
                    int   meta=(x<=maxX? w.getBlockMetadata(x,y,z):-1);
                    if(b==runBlock&&meta==runMeta){
                        // keep extending
                    }else{
                        int len=x-runStart;
                        if(runBlock!=null&&runBlock!=Blocks.air){
                            runs.add(new BlockRun(runStart,y,z,runBlock,runMeta,len));
                        }
                        runStart=x;runBlock=b;runMeta=meta;
                    }
                }
            }
        }
        return runs;
    }

    /* ------------------------------------------------------------ */
    /*  DATA HOLDER                                                 */
    /* ------------------------------------------------------------ */
    private static class BlockRun{
        final int startX,y,z,length,meta; final Block block;
        BlockRun(int sx,int yy,int zz,Block b,int m,int len){
            startX=sx;y=yy;z=zz;block=b;meta=m;length=len;
        }
        String blockRef(){
            String reg = Block.blockRegistry.getNameForObject(block);
            if(reg==null) return "Blocks.air";
            String[] p = reg.split(":",2);
            if("minecraft".equals(p[0])) return "Blocks."+p[1];
            return p[0]+"_"+p[1];
        }
    }
}
