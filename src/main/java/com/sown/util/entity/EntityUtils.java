package com.sown.util.entity;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.blocks.TabSpawning;
import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityUtils {
    private static int mobId = 300;
    private static Set<Integer> registeredEntities = new HashSet<>();
    public static String getDroidSittingMessage(boolean isSitting) {
        return isSitting ? "Restrained" : "Unrestrained";
    }

    public static int getLastUsedId() {
        return mobId;
    }

    public static Entity rayTrace(int distance, EntityLivingBase fromEntity, Entity[] exclude) {
        if (fromEntity != null &&
                fromEntity.worldObj != null) {
            Entity pointedEntity = null;
            double d0 = distance;
            MovingObjectPosition objectMouseOver = fromEntity.rayTrace(d0, 1.0F);
            double d1 = d0;
            Vec3 vec3 = fromEntity.getPosition(0.0F);
            if (objectMouseOver != null) {
                d1 = objectMouseOver.hitVec.distanceTo(vec3);
            }
            Vec3 vec31 = fromEntity.getLook(0.0F);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
            pointedEntity = null;
            float f1 = 1.0F;
            List<Entity> list = fromEntity.worldObj.getEntitiesWithinAABBExcludingEntity(fromEntity, fromEntity.boundingBox.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f1, f1, f1));
            double d2 = d1;
            for (int i = 0; i < list.size(); i++) {
                Entity entity = list.get(i);
                if (entity.canBeCollidedWith()) {
                    float f2 = entity.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
                    if (axisalignedbb.isVecInside(vec3)) {
                        if ((0.0D < d2 || d2 == 0.0D) &&
                                !Arrays.<Entity>asList(exclude).contains(entity)) {
                            pointedEntity = entity;
                            d2 = 0.0D;
                        }
                    } else if (movingobjectposition != null) {
                        double d3 = vec3.distanceTo(movingobjectposition.hitVec);
                        if ((d3 < d2 || d2 == 0.0D) &&
                                !Arrays.<Entity>asList(exclude).contains(entity)) {
                            pointedEntity = entity;
                            d2 = d3;
                        }
                    }
                }
            }
            return pointedEntity;
        }
        return null;
    }
    
    public static int getEntityIdForName(String entityName) {
        try {
            Field field = EntityList.class.getDeclaredField("stringToIDMapping");
            field.setAccessible(true);
            Map<String, Integer> stringToIDMapping = (Map<String, Integer>) field.get(null);
            return stringToIDMapping.getOrDefault(entityName, -1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static Set<Integer> getRegisteredEntityIds() {
        return registeredEntities;
    }

    public static void registerEntity(Class<? extends Entity> entityClass, String entityName) {
        while (EntityList.getClassFromID(mobId) != null) {
            mobId++;
        }
        EntityRegistry.registerModEntity(entityClass, entityName, mobId, OuterRim.instance, 80, 1, true);
        EntityList.IDtoClassMapping.put(Integer.valueOf(mobId), entityClass);
    }


public static void registerWithSpawnEgg(Class<? extends Entity> mobClass, String mobName, int bgColor, int fgColor) {
    while (EntityList.getClassFromID(mobId) != null) {
        mobId++;
    }
    EntityRegistry.registerModEntity(mobClass, mobName, mobId, OuterRim.instance, 80, 1, true);
    EntityList.IDtoClassMapping.put(Integer.valueOf(mobId), mobClass);
    EntityList.entityEggs.put(Integer.valueOf(mobId), new EntityList.EntityEggInfo(mobId, bgColor, fgColor));
    registeredEntities.add(mobId);
}

}
