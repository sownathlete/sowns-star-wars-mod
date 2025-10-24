package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPortableCoaxiumExtractor extends ModelBase {
	public ModelRenderer Main;
	public ModelRenderer Smallmain;
	public ModelRenderer Tube2;
	public ModelRenderer PortableMain;
	public ModelRenderer Tube3;
	public ModelRenderer LargeMain;
	public ModelRenderer Tube;
	public ModelRenderer Platform;
	public ModelRenderer Sphere1;
	public ModelRenderer Sphere2;
	public ModelRenderer Top;
	public ModelRenderer Pillar4;
	public ModelRenderer Pillar3;
	public ModelRenderer Pillar2;
	public ModelRenderer Pillar1;
	public ModelRenderer Pillar5;
	public ModelRenderer cube_r1;
	public ModelRenderer cube_r2;
	public ModelRenderer cube_r3;
	public ModelRenderer cube_r4;
	public ModelRenderer cube_r5;
	public ModelRenderer cube_r6;
	public ModelRenderer cube_r7;
	public ModelRenderer cube_r8;
	public ModelRenderer cube_r9;
	public ModelRenderer cube_r10;
	public ModelRenderer cube_r11;
	public ModelRenderer cube_r12;
	public ModelRenderer cube_r13;

	public ModelPortableCoaxiumExtractor() {
		this.textureWidth = 512;
		this.textureHeight = 512;

		Main = new ModelRenderer(this, 0, 0);
		Main.setRotationPoint(46.0F, 19.975F, 0.0F);

		Smallmain = new ModelRenderer(this, 0, 0);
		Smallmain.setRotationPoint(85.0F, 0.0F, -19.0F);
		Main.addChild(Smallmain);

		Tube2 = new ModelRenderer(this, 0, 0);
		Tube2.setRotationPoint(-19.0F, -53.2418F, -10.813F);
		Smallmain.addChild(Tube2);

		PortableMain = new ModelRenderer(this, 0, 0);
		PortableMain.setRotationPoint(0.0F, 5.025F, -7.0F);
		Main.addChild(PortableMain);
		PortableMain.setTextureOffset(328, 17).addBox(-53.0F, -12.0F, 0.0F, 14, 8, 14, 0.0F);
		PortableMain.setTextureOffset(348, 179).addBox(-51.0F, -16.0F, 2.0F, 10, 4, 10, 0.0F);
		PortableMain.setTextureOffset(88, 297).addBox(-50.0F, -17.0F, 3.0F, 8, 1, 8, 0.0F);
		PortableMain.setTextureOffset(384, 29).addBox(-49.0F, -20.0F, 4.0F, 6, 3, 6, 0.0F);
		PortableMain.setTextureOffset(360, 57).addBox(-51.0F, -5.0F, 2.0F, 10, 3, 10, 0.0F);
		PortableMain.setTextureOffset(328, 0).addBox(-54.0F, -2.0F, -1.0F, 16, 1, 16, 0.0F);
		PortableMain.setTextureOffset(130, 393).addBox(-54.0F, -12.0F, 4.0F, 1, 10, 6, 0.0F);
		PortableMain.setTextureOffset(400, 56).addBox(-54.0F, -15.0F, 5.0F, 3, 3, 4, 0.0F);
		PortableMain.setTextureOffset(84, 368).addBox(-55.0F, -11.0F, 5.0F, 1, 4, 4, 0.0F);
		PortableMain.setTextureOffset(144, 393).addBox(-39.0F, -12.0F, 4.0F, 1, 10, 6, 0.0F);
		PortableMain.setTextureOffset(324, 375).addBox(-38.0F, -11.0F, 5.0F, 1, 4, 4, 0.0F);
		PortableMain.setTextureOffset(400, 63).addBox(-41.0F, -15.0F, 5.0F, 3, 3, 4, 0.0F);

		cube_r1 = new ModelRenderer(this, 0, 0);
		cube_r1.setRotationPoint(-44.5F, -10.25F, 3.0F);
		setRotation(cube_r1, 0.0F, -1.5708F, 0.0F);
		PortableMain.addChild(cube_r1);
		cube_r1.setTextureOffset(358, 393).addBox(11.0F, -1.75F, -1.5F, 1, 10, 6, 0.0F);
		cube_r1.setTextureOffset(400, 219).addBox(12.0F, -0.75F, -0.5F, 1, 4, 4, 0.0F);
		cube_r1.setTextureOffset(400, 197).addBox(9.0F, -3.75F, -0.5F, 3, 2, 4, 0.0F);

		cube_r2 = new ModelRenderer(this, 0, 0);
		cube_r2.setRotationPoint(-44.5F, -10.25F, -3.0F);
		setRotation(cube_r2, 0.0F, 1.5708F, 0.0F);
		PortableMain.addChild(cube_r2);
		cube_r2.setTextureOffset(400, 191).addBox(-5.0F, -3.75F, -3.5F, 3, 2, 4, 0.0F);
		cube_r2.setTextureOffset(60, 400).addBox(-2.0F, -0.75F, -3.5F, 1, 4, 4, 0.0F);
		cube_r2.setTextureOffset(158, 393).addBox(-3.0F, -1.75F, -4.5F, 1, 10, 6, 0.0F);

		Tube3 = new ModelRenderer(this, 0, 0);
		Tube3.setRotationPoint(0.0F, 0.0F, 0.0F);
		PortableMain.addChild(Tube3);

		cube_r3 = new ModelRenderer(this, 0, 0);
		cube_r3.setRotationPoint(-45.95F, -4.3523F, -6.082F);
		setRotation(cube_r3, -1.5708F, -1.0036F, -1.5708F);
		Tube3.addChild(cube_r3);
		cube_r3.setTextureOffset(400, 211).addBox(-2.007F, -2.4977F, -2.0F, 2, 4, 4, 0.0F);

		cube_r4 = new ModelRenderer(this, 0, 0);
		cube_r4.setRotationPoint(-46.025F, -4.3523F, -6.082F);
		setRotation(cube_r4, -1.5708F, -0.6981F, -1.5708F);
		Tube3.addChild(cube_r4);
		cube_r4.setTextureOffset(400, 203).addBox(-0.7601F, -2.3719F, -2.0F, 2, 4, 4, 0.0F);

		cube_r5 = new ModelRenderer(this, 0, 0);
		cube_r5.setRotationPoint(-46.0F, -10.1631F, -15.4251F);
		setRotation(cube_r5, -0.7854F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r5);
		cube_r5.setTextureOffset(400, 281).addBox(-3.0F, -2.0F, -2.0F, 2, 4, 3, 0.0F);

		cube_r6 = new ModelRenderer(this, 0, 0);
		cube_r6.setRotationPoint(-46.0F, -0.1881F, -14.4251F);
		setRotation(cube_r6, -0.7854F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r6);
		cube_r6.setTextureOffset(398, 70).addBox(1.0F, -3.0F, -3.0F, 1, 6, 6, 0.0F);

		cube_r7 = new ModelRenderer(this, 0, 0);
		cube_r7.setRotationPoint(-46.0F, -4.1631F, -14.4251F);
		setRotation(cube_r7, -0.7854F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r7);
		cube_r7.setTextureOffset(190, 395).addBox(1.0F, -3.0F, -3.0F, 1, 6, 6, 0.0F);

		cube_r8 = new ModelRenderer(this, 0, 0);
		cube_r8.setRotationPoint(-46.0F, -4.1631F, -15.4251F);
		setRotation(cube_r8, -0.7854F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r8);
		cube_r8.setTextureOffset(280, 150).addBox(3.0F, 2.0F, -4.0F, 7, 1, 1, 0.0F);
		cube_r8.setTextureOffset(282, 384).addBox(-3.0F, 0.0F, -4.0F, 6, 3, 1, 0.0F);
		cube_r8.setTextureOffset(376, 241).addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);

		cube_r9 = new ModelRenderer(this, 0, 0);
		cube_r9.setRotationPoint(-46.0F, -4.3523F, -6.082F);
		setRotation(cube_r9, -1.5708F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r9);
		cube_r9.setTextureOffset(318, 394).addBox(-3.1642F, 0.3431F, -2.0F, 4, 8, 4, 0.0F);

		cube_r10 = new ModelRenderer(this, 0, 0);
		cube_r10.setRotationPoint(-45.975F, -4.3523F, -6.082F);
		setRotation(cube_r10, -1.5708F, 0.0F, -1.5708F);
		Tube3.addChild(cube_r10);
		cube_r10.setTextureOffset(120, 297).addBox(-0.5392F, -2.5819F, -2.0F, 4, 4, 4, 0.0F);

		cube_r11 = new ModelRenderer(this, 0, 0);
		cube_r11.setRotationPoint(-46.025F, -8.4841F, -5.3014F);
		setRotation(cube_r11, 1.5708F, 0.5672F, 1.5708F);
		Tube3.addChild(cube_r11);
		cube_r11.setTextureOffset(46, 400).addBox(-1.25F, -1.5F, -2.0F, 3, 4, 4, 0.0F);

		cube_r12 = new ModelRenderer(this, 0, 0);
		cube_r12.setRotationPoint(-45.975F, -9.0F, -3.0F);
		setRotation(cube_r12, 1.5708F, 1.2654F, 1.5708F);
		Tube3.addChild(cube_r12);
		cube_r12.setTextureOffset(238, 372).addBox(0.35F, -2.2F, -2.0F, 2, 4, 4, 0.0F);

		cube_r13 = new ModelRenderer(this, 0, 0);
		cube_r13.setRotationPoint(-46.0F, -9.0F, -2.0F);
		setRotation(cube_r13, 0.0F, 1.5708F, 0.0F);
		Tube3.addChild(cube_r13);
		cube_r13.setTextureOffset(364, 304).addBox(0.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);

		LargeMain = new ModelRenderer(this, 0, 0);
		LargeMain.setRotationPoint(0.0F, 4.025F, 0.0F);
		Main.addChild(LargeMain);

		Tube = new ModelRenderer(this, 0, 0);
		Tube.setRotationPoint(0.0F, -4.025F, 0.0F);
		LargeMain.addChild(Tube);

		Platform = new ModelRenderer(this, 0, 0);
		Platform.setRotationPoint(0.0F, 0.0F, 0.0F);
		LargeMain.addChild(Platform);

		Sphere1 = new ModelRenderer(this, 0, 0);
		Sphere1.setRotationPoint(19.0F, -20.025F, -19.0F);
		LargeMain.addChild(Sphere1);

		Sphere2 = new ModelRenderer(this, 0, 0);
		Sphere2.setRotationPoint(19.0F, -60.025F, -19.0F);
		LargeMain.addChild(Sphere2);

		Top = new ModelRenderer(this, 0, 0);
		Top.setRotationPoint(19.5F, -80.025F, -18.5F);
		LargeMain.addChild(Top);

		Pillar4 = new ModelRenderer(this, 0, 0);
		Pillar4.setRotationPoint(19.0F, -4.025F, -19.0F);
		LargeMain.addChild(Pillar4);

		Pillar3 = new ModelRenderer(this, 0, 0);
		Pillar3.setRotationPoint(19.0F, -4.025F, 19.0F);
		LargeMain.addChild(Pillar3);

		Pillar2 = new ModelRenderer(this, 0, 0);
		Pillar2.setRotationPoint(21.0F, -45.025F, 0.0F);
		setRotation(Pillar2, 0.0F, -1.5708F, 0.0F);
		LargeMain.addChild(Pillar2);

		Pillar1 = new ModelRenderer(this, 0, 0);
		Pillar1.setRotationPoint(-21.0F, -45.025F, 0.0F);
		setRotation(Pillar1, 0.0F, 1.5708F, 0.0F);
		LargeMain.addChild(Pillar1);

		Pillar5 = new ModelRenderer(this, 0, 0);
		Pillar5.setRotationPoint(-21.0454F, -44.5705F, 0.0F);
		setRotation(Pillar5, 0.0F, -1.5708F, 0.0F);
		LargeMain.addChild(Pillar5);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		Main.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale, Entity entityIn) {
	}

	private void setRotation(ModelRenderer part, float x, float y, float z) {
		part.rotateAngleX = x;
		part.rotateAngleY = y;
		part.rotateAngleZ = z;
	}
}
