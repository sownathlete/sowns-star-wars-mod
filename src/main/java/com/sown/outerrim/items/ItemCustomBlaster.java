package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.entities.EntityLaserProjectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemCustomBlaster extends ItemBow {
    public ItemCustomBlaster() {
        super();
        this.setCreativeTab(OuterRim.tabCombat); // Replace OuterRim.tabCombat with your actual creative tab
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int timeLeft) {
        boolean flag = player.capabilities.isCreativeMode;

        if (flag || player.inventory.hasItem(Items.arrow)) {
            // Set the power just below critical (slightly under full power)
            float power = 0.99F; // Just below full power

            // Create the laser projectile entity
            EntityLaserProjectile entityLaser = new EntityLaserProjectile(world, player, power * 2.0F);

            // Set direction of the laser projectile (fix aiming)
            entityLaser.shootProjectile(player, power * 2.0F);

            // Do not set the critical hit effect since we are below full power
            // entityLaser.setIsCritical(false); // No need to set this since it's not critical

            // Damage the item when shot
            itemStack.damageItem(1, player);

            // Play the custom sound
            world.playSoundAtEntity(player, "outerrim:item.blaster.dc15s", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + power * 0.5F);

            // Spawn the projectile in the world
            if (!world.isRemote) {
                world.spawnEntityInWorld(entityLaser);
            }
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        // Override this method to prevent the bow draw sound from playing
    }
}
