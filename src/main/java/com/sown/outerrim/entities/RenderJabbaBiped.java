package com.sown.outerrim.entities;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderJabbaBiped
extends RenderLiving {
    public ModelJabba modelBipedMain;
    protected float field_77070_b;
    protected ModelJabba field_82423_g;
    protected ModelJabba field_82425_h;
    private static final Map field_110859_k = Maps.newHashMap();
    public static String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};
    private static final String __OBFID = "CL_00001001";

    public RenderJabbaBiped(ModelJabba par1ModelBiped, float par2) {
        this(par1ModelBiped, par2, 1.0f);
    }

    public RenderJabbaBiped(ModelJabba par1ModelBiped, float par2, float par3) {
        super((ModelBase)par1ModelBiped, par2);
        this.modelBipedMain = par1ModelBiped;
        this.field_77070_b = par3;
        this.func_82421_b();
    }

    protected void func_82421_b() {
        this.field_82423_g = new ModelJabba();
        this.field_82425_h = new ModelJabba();
    }

    @Deprecated
    public static ResourceLocation func_110857_a(ItemArmor par0ItemArmor, int par1) {
        return RenderJabbaBiped.func_110858_a(par0ItemArmor, par1, null);
    }

    @Deprecated
    public static ResourceLocation func_110858_a(ItemArmor par0ItemArmor, int par1, String par2Str) {
        Object[] arrobject = new Object[3];
        arrobject[0] = bipedArmorFilenamePrefix[par0ItemArmor.renderIndex];
        arrobject[1] = par1 == 2 ? 2 : 1;
        arrobject[2] = par2Str == null ? "" : String.format("_%s", par2Str);
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", arrobject);
        ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }
        return resourcelocation;
    }

    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            this.bindTexture(RenderJabbaBiped.getArmorResource((Entity)par1EntityLiving, itemstack, par2, null));
            ModelJabba modelbiped = par2 == 2 ? this.field_82425_h : this.field_82423_g;
            modelbiped.shape38.showModel = par2 == 0;
            modelbiped.shape38.showModel = par2 == 0;
            this.setRenderPassModel((ModelBase)modelbiped);
            modelbiped.onGround = this.mainModel.onGround;
            modelbiped.isRiding = this.mainModel.isRiding;
            modelbiped.isChild = this.mainModel.isChild;
            int j = itemarmor.getColor(itemstack);
            if (j != -1) {
                float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(j & 0xFF) / 255.0f;
                GL11.glColor3f((float)f1, (float)f2, (float)f3);
                if (itemstack.isItemEnchanted()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (itemstack.isItemEnchanted()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    protected void func_82408_c(EntityLiving par1EntityLiving, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemArmor) {
            this.bindTexture(RenderJabbaBiped.getArmorResource((Entity)par1EntityLiving, itemstack, par2, "overlay"));
            float f1 = 1.0f;
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = entity.getHeldItem();
        this.func_82420_a(entity, itemstack);
        double d3 = y - (double)entity.yOffset;
        if (entity.isSneaking()) {
            d3 -= 0.125;
        }
        super.doRender(entity, x, d3, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return null;
    }

    protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
        boolean is3D;
        boolean bl;
        Item item;
        float f1;
        IItemRenderer customRenderer;
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        super.renderEquippedItems((EntityLivingBase)par1EntityLiving, par2);
        ItemStack itemstack = par1EntityLiving.getHeldItem();
        ItemStack itemstack1 = par1EntityLiving.func_130225_q(3);
        if (itemstack1 != null) {
            GL11.glPushMatrix();
            item = itemstack1.getItem();
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack1, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            bl = is3D;
            if (item instanceof ItemBlock) {
                if (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)item).getRenderType())) {
                    f1 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                }
                this.renderManager.itemRenderer.renderItem((EntityLivingBase)par1EntityLiving, itemstack1, 0);
            } else if (item == Items.skull) {
                f1 = 1.0625f;
                GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                GameProfile gameprofile = null;
                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5f, 0.0f, -0.5f, 1, 180.0f, itemstack1.getItemDamage(), gameprofile);
            }
            GL11.glPopMatrix();
        }
        if (itemstack != null && itemstack.getItem() != null) {
            item = itemstack.getItem();
            GL11.glPushMatrix();
            if (this.mainModel.isChild) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
            }
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            bl = is3D;
            if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)item).getRenderType()))) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f1 *= 0.75f)), (float)(-f1), (float)f1);
            } else if (item == Items.bow) {
                f1 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (item.isFull3D()) {
                f1 = 0.625f;
                if (item.shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                this.transformHeldFull3DItemLayer();
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f1 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack.getItem().requiresMultipleRenderPasses()) {
                for (int i = 0; i < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++i) {
                    int j = itemstack.getItem().getColorFromItemStack(itemstack, i);
                    float f5 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f3 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f5, (float)f2, (float)f3, (float)1.0f);
                    this.renderManager.itemRenderer.renderItem((EntityLivingBase)par1EntityLiving, itemstack, i);
                }
            } else {
                int i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                float f4 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f5 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f4, (float)f5, (float)f2, (float)1.0f);
                this.renderManager.itemRenderer.renderItem((EntityLivingBase)par1EntityLiving, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
    }

    protected void transformHeldFull3DItemLayer() {
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
    }

    protected void func_82408_c(EntityLivingBase par1EntityLiving, int par2, float par3) {
        this.func_82408_c((EntityLiving)par1EntityLiving, par2, par3);
    }

    protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.shouldRenderPass((EntityLiving)par1EntityLiving, par2, par3);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
        this.renderEquippedItems((EntityLiving)par1EntityLiving, par2);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityLiving)entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
    }

    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
        ItemArmor item = (ItemArmor)stack.getItem();
        Object[] arrobject = new Object[3];
        arrobject[0] = bipedArmorFilenamePrefix[item.renderIndex];
        arrobject[1] = slot == 2 ? 2 : 1;
        arrobject[2] = type == null ? "" : String.format("_%s", type);
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", arrobject);
        s1 = ForgeHooksClient.getArmorTexture((Entity)entity, (ItemStack)stack, (String)s1, (int)slot, (String)type);
        ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }
        return resourcelocation;
    }
}

