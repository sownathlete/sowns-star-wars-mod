package com.sown.outerrim.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWookiee extends EntityCreature {
    // DataWatcher IDs
    private static final int DW_TEXTURE_INDEX = 25;
    private static final int DW_GENDER_FLAG   = 28; // 0 = female, 1 = male

    public EntityWookiee(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 2.2F); // Wookiee are tall and fairly wide
        this.getNavigator().setCanSwim(true);

        // AI tasks
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5D, false));
        this.tasks.addTask(2, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));

        // Randomly pick gender once on construction
        boolean isMale = this.rand.nextBoolean();
        this.getDataWatcher().updateObject(DW_GENDER_FLAG, isMale ? 1 : 0);

        // Choose a texture index (0–2 for female, 3–5 for male)
        int textureIndex = isMale
            ? 3 + this.rand.nextInt(3)
            : this.rand.nextInt(3);
        this.getDataWatcher().updateObject(DW_TEXTURE_INDEX, textureIndex);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        // DW_TEXTURE_INDEX: which skin to use
        this.getDataWatcher().addObject(DW_TEXTURE_INDEX, 0);
        // DW_GENDER_FLAG: 0 = female, 1 = male
        this.getDataWatcher().addObject(DW_GENDER_FLAG, 0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        // Ensure attackDamage is registered before setting it
        if (this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage) == null) {
            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        }

        // Follow range (default attribute)
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        // Health (default attribute)
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        // Movement speed (default attribute)
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        // Attack damage (registered above)
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }

    public boolean isMale() {
        return this.getDataWatcher().getWatchableObjectInt(DW_GENDER_FLAG) == 1;
    }

    public int getTextureIndex() {
        return this.getDataWatcher().getWatchableObjectInt(DW_TEXTURE_INDEX);
    }

    @Override
    public String getCommandSenderName() {
        return "Wookiee";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:entity.wookiee.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:entity.wookiee.death";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:entity.wookiee.ambient";
    }

    @Override
    public int getTalkInterval() {
        return 1000; // roughly every 50 seconds
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        // Per-tick logic (animations or AI tweaks) can go here
    }

    /**
     * Optional method that a custom renderer can call to fetch the correct texture
     * based on gender + texture index:
     *
     * Resource location paths might be:
     *   - assets/outerrim/textures/entity/wookie_female_0.png
     *   - assets/outerrim/textures/entity/wookie_female_1.png
     *   - assets/outerrim/textures/entity/wookie_female_2.png
     *   - assets/outerrim/textures/entity/wookie_male_3.png
     *   - assets/outerrim/textures/entity/wookie_male_4.png
     *   - assets/outerrim/textures/entity/wookie_male_5.png
     *
     * A renderer might implement something like:
     *   int idx = this.getTextureIndex();
     *   return new ResourceLocation(
     *       "outerrim",
     *       "textures/entity/wookie_" +
     *       (this.isMale() ? "male_" : "female_") +
     *       idx +
     *       ".png"
     *   );
     */
}
