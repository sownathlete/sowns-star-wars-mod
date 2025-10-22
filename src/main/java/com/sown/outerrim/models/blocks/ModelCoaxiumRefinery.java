package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoaxiumRefinery extends ModelBase {
	private final ModelRenderer Main;
	private final ModelRenderer MainFrame2;
	private final ModelRenderer Frame3;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer Cable2;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer Cable3;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer Cable4;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer Cable9;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer Handle3;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer MainFrame3;
	private final ModelRenderer Frame2;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer cube_r41;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;
	private final ModelRenderer cube_r44;
	private final ModelRenderer cube_r45;
	private final ModelRenderer Cable5;
	private final ModelRenderer cube_r46;
	private final ModelRenderer cube_r47;
	private final ModelRenderer cube_r48;
	private final ModelRenderer cube_r49;
	private final ModelRenderer cube_r50;
	private final ModelRenderer cube_r51;
	private final ModelRenderer cube_r52;
	private final ModelRenderer cube_r53;
	private final ModelRenderer Cable6;
	private final ModelRenderer cube_r54;
	private final ModelRenderer cube_r55;
	private final ModelRenderer cube_r56;
	private final ModelRenderer Cable7;
	private final ModelRenderer cube_r57;
	private final ModelRenderer cube_r58;
	private final ModelRenderer cube_r59;
	private final ModelRenderer Cable8;
	private final ModelRenderer cube_r60;
	private final ModelRenderer cube_r61;
	private final ModelRenderer cube_r62;
	private final ModelRenderer cube_r63;
	private final ModelRenderer cube_r64;
	private final ModelRenderer cube_r65;
	private final ModelRenderer cube_r66;
	private final ModelRenderer cube_r67;
	private final ModelRenderer Handle2;
	private final ModelRenderer cube_r68;
	private final ModelRenderer cube_r69;
	private final ModelRenderer cube_r70;
	private final ModelRenderer cube_r71;
	private final ModelRenderer cube_r72;
	private final ModelRenderer Computer;
	private final ModelRenderer cube_r73;
	private final ModelRenderer cube_r74;
	private final ModelRenderer cube_r75;
	private final ModelRenderer cube_r76;
	private final ModelRenderer cube_r77;
	private final ModelRenderer cube_r78;
	private final ModelRenderer cube_r79;
	private final ModelRenderer cube_r80;
	private final ModelRenderer cube_r81;
	private final ModelRenderer cube_r82;
	private final ModelRenderer Coaxium;
	private final ModelRenderer cube_r83;
	private final ModelRenderer CoaxiumTube_r1;
	private final ModelRenderer CoaxiumTube_r2;

	public ModelCoaxiumRefinery() {
		textureWidth = 256;
		textureHeight = 256;

		Main = new ModelRenderer(this);
		Main.setRotationPoint(-23.0F, 24.0F, 0.0F);

		MainFrame2 = new ModelRenderer(this);
		MainFrame2.setRotationPoint(46.0F, 0.0F, 0.0F);
		Main.addChild(MainFrame2);

		Frame3 = new ModelRenderer(this);
		Frame3.setRotationPoint(0.0F, 0.0F, 0.0F);
		MainFrame2.addChild(Frame3);
		Frame3.cubeList.add(new ModelBox(Frame3, 166, 36, 9.0F, -1.0F, 8.95F, 8, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 172, 100, 11.575F, -2.0F, 8.95F, 6, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 180, 179, 14.575F, -3.0F, 8.95F, 3, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 162, 199, 13.275F, -2.65F, 15.975F, 1, 2, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 66, 51, 9.25F, -0.975F, 16.025F, 5, 1, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 118, 193, 11.275F, -1.75F, 14.975F, 2, 1, 3, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 200, 20, 9.0F, -1.0F, 16.0F, 1, 1, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 30, 207, 15.9F, -3.075F, -17.0F, 1, 2, 26, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 64, 168, 21.0F, -5.0F, -16.0F, 0, 1, 5, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 132, 78, 17.0F, -4.0F, 3.0F, 4, 4, 13, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 0, 38, -15.1F, -3.075F, 6.975F, 31, 2, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 0, 42, -15.1F, -3.075F, -16.975F, 31, 2, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 3, 207, 8.9F, -3.075F, -15.0F, 2, 2, 22, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 162, 169, 21.0F, -5.0F, 3.0F, 0, 1, 5, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 160, 166, 21.0F, -5.0F, 8.975F, 0, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 136, 13, 17.0F, -5.0F, 2.975F, 4, 0, 13, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 128, 160, 17.0F, -5.0F, 2.975F, 0, 1, 13, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 166, 36, 9.0F, -1.0F, -23.95F, 8, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 172, 100, 11.575F, -2.0F, -23.95F, 6, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 180, 179, 14.575F, -3.0F, -23.95F, 3, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 162, 199, 13.275F, -2.65F, -25.975F, 1, 2, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 66, 51, 9.25F, -0.975F, -26.025F, 5, 1, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 118, 193, 11.275F, -1.75F, -25.975F, 2, 1, 3, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 200, 20, 9.0F, -1.0F, -26.0F, 1, 1, 2, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 132, 78, 17.0F, -4.0F, -24.0F, 4, 4, 13, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 128, 159, 17.0F, -5.0F, -23.975F, 0, 1, 13, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 160, 166, 21.0F, -5.0F, -23.975F, 0, 1, 7, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 105, 211, -15.1F, -3.075F, -15.0F, 6, 2, 22, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 154, 186, 17.0F, -16.0F, 7.975F, 4, 12, 1, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 164, 186, 17.0F, -16.0F, -16.975F, 4, 12, 1, 0.0F));
		Frame3.cubeList.add(new ModelBox(Frame3, 136, 26, -14.25F, -4.075F, 9.0F, 13, 2, 2, 0.0F));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-14.475F, -1.85F, -1.0F);
		Frame3.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.6632F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 88, 77, -4.0F, -1.0F, -8.0F, 4, 2, 18, 0.0F));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.025F, -1.125F, -7.0F);
		Frame3.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -1.309F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 194, 28, 11.9F, 5.925F, -12.0F, 3, 2, 2, 0.0F));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 194, 28, 11.9F, 5.925F, 16.0F, 3, 2, 2, 0.0F));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.25F, -3.8F, -18.0F);
		Frame3.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -0.0436F, -0.2182F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 180, 48, -1.15F, -0.525F, -0.5F, 3, 1, 1, 0.0F));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.25F, -3.8F, 10.0F);
		Frame3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0436F, -0.2182F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 180, 48, -1.15F, -0.525F, -0.5F, 3, 1, 1, 0.0F));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-7.75F, -3.075F, -18.0F);
		Frame3.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 3.1416F, 0.0F);
		cube_r5.mirror = true;
		cube_r5.cubeList.add(new ModelBox(cube_r5, 136, 26, -6.5F, -1.0F, -1.0F, 13, 2, 2, 0.0F));
		cube_r5.mirror = false;

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, -8.0F);
		Frame3.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 1.1519F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 22, 177, -7.2F, -10.1F, -9.25F, 1, 3, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 28, 153, -7.2F, -10.1F, 16.25F, 1, 3, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 88, 160, -7.7F, -22.1F, -9.0F, 2, 32, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 166, 48, -13.7F, 4.9F, -9.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 104, 15, -13.7F, -16.1F, -9.0F, 1, 21, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 150, 30, -13.7F, -17.1F, -9.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 66, 145, -13.7F, -17.1F, 7.5F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 40, 99, -13.7F, -16.1F, 7.5F, 1, 21, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 136, 30, -13.7F, 4.9F, 7.5F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 66, 145, -13.7F, -17.1F, 16.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 150, 30, -13.7F, -17.1F, -1.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 166, 48, -13.7F, 4.9F, -1.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 104, 15, -13.7F, -16.1F, -1.0F, 1, 21, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 40, 99, -13.7F, -16.1F, 16.0F, 1, 21, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 136, 30, -13.7F, 4.9F, 16.0F, 6, 1, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 82, 160, -7.7F, -22.1F, -1.0F, 2, 32, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 82, 160, -7.7F, -22.1F, 7.5F, 2, 32, 1, 0.0F));
		cube_r6.cubeList.add(new ModelBox(cube_r6, 82, 160, -7.7F, -22.1F, 16.0F, 2, 32, 1, 0.0F));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(19.0F, -8.0F, 0.0F);
		Frame3.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.7854F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 60, 210, -1.0F, -1.0F, -18.0F, 2, 2, 28, 0.0F));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(19.0F, -5.0F, -17.475F);
		Frame3.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 3.1416F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 136, 13, -2.0F, 0.0F, -6.5F, 4, 0, 13, 0.0F));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(17.175F, -3.525F, -20.5F);
		Frame3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, -0.3491F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 166, 26, -8.95F, -0.5F, -5.5F, 5, 1, 9, 0.0F));
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 177, -4.0F, -0.5F, -3.475F, 4, 1, 7, 0.0F));
		cube_r9.cubeList.add(new ModelBox(cube_r9, 166, 26, -8.95F, -0.5F, 29.5F, 5, 1, 9, 0.0F));
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 177, -4.0F, -0.5F, 29.475F, 4, 1, 7, 0.0F));

		Cable2 = new ModelRenderer(this);
		Cable2.setRotationPoint(-0.25F, -3.075F, 10.0F);
		MainFrame2.addChild(Cable2);
		Cable2.cubeList.add(new ModelBox(Cable2, 178, 96, -1.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(12.2265F, -12.9075F, -0.5401F);
		Cable2.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0627F, 0.473F, -0.8999F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 185, -0.35F, -0.975F, -1.075F, 1, 2, 2, 0.0F));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(12.9482F, -13.6301F, -2.2399F);
		Cable2.addChild(cube_r11);
		setRotationAngle(cube_r11, 1.4602F, 1.5147F, 0.5315F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 52, 198, -0.5F, -0.975F, -1.075F, 1, 2, 2, 0.0F));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(12.5949F, -13.2838F, -0.8887F);
		Cable2.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.119F, 1.0817F, -0.8233F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 196, 147, -0.575F, -1.0F, -1.2F, 2, 2, 2, 0.0F));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(10.8454F, -10.65F, 0.0F);
		Cable2.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0296F, 0.1736F, -1.0226F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 48, 194, -0.3F, -1.025F, -1.075F, 3, 2, 2, 0.0F));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(9.5382F, -4.9997F, 0.0F);
		Cable2.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, -1.3832F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 194, 32, 0.0F, -1.0F, -1.025F, 3, 2, 2, 0.0F));

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(8.0183F, -2.8409F, 0.0F);
		Cable2.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, -0.9905F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 196, 38, 0.0F, -1.0F, -0.975F, 3, 2, 2, 0.0F));

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(3.675F, 0.05F, 0.0F);
		Cable2.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -0.3054F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 196, 155, 0.0F, -1.0F, -1.025F, 2, 2, 2, 0.0F));

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(5.2074F, -0.3514F, 0.0F);
		Cable2.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, -0.7374F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 190, 56, 0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F));

		Cable3 = new ModelRenderer(this);
		Cable3.setRotationPoint(7.2721F, -8.1936F, 9.1847F);
		MainFrame2.addChild(Cable3);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(-6.723F, 3.9691F, 0.7346F);
		Cable3.addChild(cube_r18);
		setRotationAngle(cube_r18, -0.015F, 0.0934F, -0.5683F);
		cube_r18.cubeList.add(new ModelBox(cube_r18, 80, 51, -0.1F, -0.475F, -0.525F, 5, 1, 1, 0.0F));

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(-2.5274F, 1.2897F, 0.2685F);
		Cable3.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.0068F, 0.0943F, -0.4807F);
		cube_r19.cubeList.add(new ModelBox(cube_r19, 180, 173, -0.15F, -0.475F, -0.5F, 3, 1, 1, 0.0F));

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cable3.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0436F, -0.2246F, -0.7023F);
		cube_r20.cubeList.add(new ModelBox(cube_r20, 36, 127, -0.15F, -0.5F, -0.5F, 3, 1, 1, 0.0F));

		Cable4 = new ModelRenderer(this);
		Cable4.setRotationPoint(7.2721F, -8.1936F, -17.1847F);
		MainFrame2.addChild(Cable4);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(-6.723F, 3.9691F, -0.7346F);
		Cable4.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.015F, -0.0934F, -0.5683F);
		cube_r21.cubeList.add(new ModelBox(cube_r21, 80, 51, -0.1F, -0.475F, -0.475F, 5, 1, 1, 0.0F));

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(-2.5274F, 1.2897F, -0.2685F);
		Cable4.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0068F, -0.0943F, -0.4807F);
		cube_r22.cubeList.add(new ModelBox(cube_r22, 180, 173, -0.15F, -0.475F, -0.5F, 3, 1, 1, 0.0F));

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cable4.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.0436F, 0.2246F, -0.7023F);
		cube_r23.cubeList.add(new ModelBox(cube_r23, 36, 127, -0.15F, -0.5F, -0.5F, 3, 1, 1, 0.0F));

		Cable9 = new ModelRenderer(this);
		Cable9.setRotationPoint(-0.25F, -3.075F, -18.0F);
		MainFrame2.addChild(Cable9);
		setRotationAngle(Cable9, 0.0F, -3.1416F, 0.0F);
		Cable9.mirror = true;
		Cable9.cubeList.add(new ModelBox(Cable9, 178, 96, -4.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F));
		Cable9.mirror = false;

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(-12.5949F, -13.2838F, -0.8887F);
		Cable9.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.119F, -1.0817F, 0.8233F);
		cube_r24.mirror = true;
		cube_r24.cubeList.add(new ModelBox(cube_r24, 196, 147, -1.425F, -1.0F, -1.2F, 2, 2, 2, 0.0F));
		cube_r24.mirror = false;

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(-12.2265F, -12.9075F, -0.5401F);
		Cable9.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0627F, -0.473F, 0.8999F);
		cube_r25.mirror = true;
		cube_r25.cubeList.add(new ModelBox(cube_r25, 16, 185, -0.65F, -0.975F, -1.075F, 1, 2, 2, 0.0F));
		cube_r25.mirror = false;

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(-10.8454F, -10.65F, 0.0F);
		Cable9.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0296F, -0.1736F, 1.0226F);
		cube_r26.mirror = true;
		cube_r26.cubeList.add(new ModelBox(cube_r26, 48, 194, -2.7F, -1.025F, -1.075F, 3, 2, 2, 0.0F));
		cube_r26.mirror = false;

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(-12.9482F, -13.6301F, -2.2399F);
		Cable9.addChild(cube_r27);
		setRotationAngle(cube_r27, 1.4602F, -1.5147F, -0.5315F);
		cube_r27.mirror = true;
		cube_r27.cubeList.add(new ModelBox(cube_r27, 52, 198, -0.5F, -0.975F, -1.075F, 1, 2, 2, 0.0F));
		cube_r27.mirror = false;

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(-9.5382F, -4.9997F, 0.0F);
		Cable9.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, 1.3832F);
		cube_r28.mirror = true;
		cube_r28.cubeList.add(new ModelBox(cube_r28, 194, 32, -3.0F, -1.0F, -1.025F, 3, 2, 2, 0.0F));
		cube_r28.mirror = false;

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(-8.0183F, -2.8409F, 0.0F);
		Cable9.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, 0.9905F);
		cube_r29.mirror = true;
		cube_r29.cubeList.add(new ModelBox(cube_r29, 196, 38, -3.0F, -1.0F, -0.975F, 3, 2, 2, 0.0F));
		cube_r29.mirror = false;

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(-3.675F, 0.05F, 0.0F);
		Cable9.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, 0.0F, 0.3054F);
		cube_r30.mirror = true;
		cube_r30.cubeList.add(new ModelBox(cube_r30, 196, 155, -2.0F, -1.0F, -1.025F, 2, 2, 2, 0.0F));
		cube_r30.mirror = false;

		cube_r31 = new ModelRenderer(this);
		cube_r31.setRotationPoint(-5.2074F, -0.3514F, 0.0F);
		Cable9.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, 0.0F, 0.7374F);
		cube_r31.mirror = true;
		cube_r31.cubeList.add(new ModelBox(cube_r31, 190, 56, -4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F));
		cube_r31.mirror = false;

		Handle3 = new ModelRenderer(this);
		Handle3.setRotationPoint(19.0F, -8.0F, 0.0F);
		MainFrame2.addChild(Handle3);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle3.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0F, 0.0F, 0.7854F);
		cube_r32.cubeList.add(new ModelBox(cube_r32, 0, 194, -4.5F, -0.5F, 9.975F, 5, 1, 1, 0.0F));
		cube_r32.cubeList.add(new ModelBox(cube_r32, 194, 26, -4.5F, -0.5F, -18.975F, 5, 1, 1, 0.0F));

		cube_r33 = new ModelRenderer(this);
		cube_r33.setRotationPoint(-3.5355F, -3.5355F, 10.5F);
		Handle3.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, 0.0F, 0.48F);
		cube_r33.cubeList.add(new ModelBox(cube_r33, 36, 115, 0.325F, -10.375F, -0.5F, 1, 11, 1, 0.0F));
		cube_r33.cubeList.add(new ModelBox(cube_r33, -8, 46, 0.325F, -11.375F, -29.5F, 1, 1, 30, 0.0F));
		cube_r33.cubeList.add(new ModelBox(cube_r33, 32, 133, 0.325F, -10.375F, -29.5F, 1, 11, 1, 0.0F));

		cube_r34 = new ModelRenderer(this);
		cube_r34.setRotationPoint(-28.0322F, 3.6242F, -4.0F);
		Handle3.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.0F, 0.0F, 1.0908F);
		cube_r34.cubeList.add(new ModelBox(cube_r34, -2, 52, 0.075F, -0.5F, -12.0F, 1, 1, 24, 0.0F));

		cube_r35 = new ModelRenderer(this);
		cube_r35.setRotationPoint(-18.0322F, 0.6242F, -4.0F);
		Handle3.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.0F, 0.0F, 1.2217F);
		cube_r35.cubeList.add(new ModelBox(cube_r35, -2, 52, -0.5F, -0.5F, -12.0F, 1, 1, 24, 0.0F));

		cube_r36 = new ModelRenderer(this);
		cube_r36.setRotationPoint(-4.0322F, -5.6258F, -4.0F);
		Handle3.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.0F, 0.0F, 1.2217F);
		cube_r36.cubeList.add(new ModelBox(cube_r36, -2, 52, -0.5F, -0.5F, -12.0F, 1, 1, 24, 0.0F));

		MainFrame3 = new ModelRenderer(this);
		MainFrame3.setRotationPoint(0.0F, 0.0F, 0.0F);
		Main.addChild(MainFrame3);

		Frame2 = new ModelRenderer(this);
		Frame2.setRotationPoint(0.0F, 0.0F, 0.0F);
		MainFrame3.addChild(Frame2);
		Frame2.mirror = true;
		Frame2.cubeList.add(new ModelBox(Frame2, 166, 36, -17.0F, -1.0F, 8.95F, 8, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 172, 100, -17.575F, -2.0F, 8.95F, 6, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 180, 179, -17.575F, -3.0F, 8.95F, 3, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 162, 199, -14.275F, -2.65F, 15.975F, 1, 2, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 66, 51, -14.25F, -0.975F, 16.025F, 5, 1, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 118, 193, -13.275F, -1.75F, 14.975F, 2, 1, 3, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 200, 20, -10.0F, -1.0F, 16.0F, 1, 1, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 30, 207, -16.9F, -3.075F, -17.0F, 1, 2, 26, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 64, 168, -21.0F, -5.0F, -16.0F, 0, 1, 5, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 132, 78, -21.0F, -4.0F, 3.0F, 4, 4, 13, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 0, 38, -15.9F, -3.075F, 6.975F, 31, 2, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 0, 42, -15.9F, -3.075F, -16.975F, 31, 2, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 3, 207, -10.9F, -3.075F, -15.0F, 2, 2, 22, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 162, 169, -21.0F, -5.0F, 3.0F, 0, 1, 5, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 160, 166, -21.0F, -5.0F, 8.975F, 0, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 136, 13, -21.0F, -5.0F, 2.975F, 4, 0, 13, 0.0F));
		Frame2.mirror = false;
		Frame2.cubeList.add(new ModelBox(Frame2, 128, 160, -17.0F, -5.0F, 2.975F, 0, 1, 13, 0.0F));
		Frame2.mirror = true;
		Frame2.cubeList.add(new ModelBox(Frame2, 166, 36, -17.0F, -1.0F, -23.95F, 8, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 172, 100, -17.575F, -2.0F, -23.95F, 6, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 180, 179, -17.575F, -3.0F, -23.95F, 3, 1, 7, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 162, 199, -14.275F, -2.65F, -25.975F, 1, 2, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 66, 51, -14.25F, -0.975F, -26.025F, 5, 1, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 118, 193, -13.275F, -1.75F, -25.975F, 2, 1, 3, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 200, 20, -10.0F, -1.0F, -26.0F, 1, 1, 2, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 132, 78, -21.0F, -4.0F, -24.0F, 4, 4, 13, 0.0F));
		Frame2.mirror = false;
		Frame2.cubeList.add(new ModelBox(Frame2, 128, 159, -17.0F, -5.0F, -23.975F, 0, 1, 13, 0.0F));
		Frame2.mirror = true;
		Frame2.cubeList.add(new ModelBox(Frame2, 160, 166, -21.0F, -5.0F, -23.975F, 0, 1, 7, 0.0F));
		Frame2.mirror = false;
		Frame2.cubeList.add(new ModelBox(Frame2, 105, 211, 9.1F, -3.075F, -15.0F, 6, 2, 22, 0.0F));
		Frame2.mirror = true;
		Frame2.cubeList.add(new ModelBox(Frame2, 154, 186, -21.0F, -16.0F, 7.975F, 4, 12, 1, 0.0F));
		Frame2.cubeList.add(new ModelBox(Frame2, 164, 186, -21.0F, -16.0F, -16.975F, 4, 12, 1, 0.0F));
		Frame2.mirror = false;
		Frame2.cubeList.add(new ModelBox(Frame2, 136, 26, 1.25F, -4.075F, 9.0F, 13, 2, 2, 0.0F));

		cube_r37 = new ModelRenderer(this);
		cube_r37.setRotationPoint(14.475F, -1.85F, -1.0F);
		Frame2.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.0F, 0.0F, -0.6632F);
		cube_r37.mirror = true;
		cube_r37.cubeList.add(new ModelBox(cube_r37, 88, 77, 0.0F, -1.0F, -8.0F, 4, 2, 18, 0.0F));
		cube_r37.mirror = false;

		cube_r38 = new ModelRenderer(this);
		cube_r38.setRotationPoint(-0.025F, -1.125F, -7.0F);
		Frame2.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.0F, 0.0F, 1.309F);
		cube_r38.mirror = true;
		cube_r38.cubeList.add(new ModelBox(cube_r38, 194, 28, -14.9F, 5.925F, -12.0F, 3, 2, 2, 0.0F));
		cube_r38.cubeList.add(new ModelBox(cube_r38, 194, 28, -14.9F, 5.925F, 16.0F, 3, 2, 2, 0.0F));
		cube_r38.mirror = false;

		cube_r39 = new ModelRenderer(this);
		cube_r39.setRotationPoint(1.25F, -3.8F, -18.0F);
		Frame2.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.0F, 0.0436F, 0.2182F);
		cube_r39.mirror = true;
		cube_r39.cubeList.add(new ModelBox(cube_r39, 180, 48, -1.85F, -0.525F, -0.5F, 3, 1, 1, 0.0F));
		cube_r39.mirror = false;

		cube_r40 = new ModelRenderer(this);
		cube_r40.setRotationPoint(1.25F, -3.8F, 10.0F);
		Frame2.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.0F, -0.0436F, 0.2182F);
		cube_r40.mirror = true;
		cube_r40.cubeList.add(new ModelBox(cube_r40, 180, 48, -1.85F, -0.525F, -0.5F, 3, 1, 1, 0.0F));
		cube_r40.mirror = false;

		cube_r41 = new ModelRenderer(this);
		cube_r41.setRotationPoint(7.75F, -3.075F, -18.0F);
		Frame2.addChild(cube_r41);
		setRotationAngle(cube_r41, 0.0F, -3.1416F, 0.0F);
		cube_r41.cubeList.add(new ModelBox(cube_r41, 136, 26, -6.5F, -1.0F, -1.0F, 13, 2, 2, 0.0F));

		cube_r42 = new ModelRenderer(this);
		cube_r42.setRotationPoint(0.0F, 0.0F, -8.0F);
		Frame2.addChild(cube_r42);
		setRotationAngle(cube_r42, 0.0F, 0.0F, -1.1519F);
		cube_r42.mirror = true;
		cube_r42.cubeList.add(new ModelBox(cube_r42, 22, 177, 6.2F, -10.1F, -9.25F, 1, 3, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 28, 153, 6.2F, -10.1F, 16.25F, 1, 3, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 88, 160, 5.7F, -22.1F, -9.0F, 2, 32, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 166, 48, 7.7F, 4.9F, -9.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 104, 15, 12.7F, -16.1F, -9.0F, 1, 21, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 150, 30, 7.7F, -17.1F, -9.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 66, 145, 7.7F, -17.1F, 7.5F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 40, 99, 12.7F, -16.1F, 7.5F, 1, 21, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 136, 30, 7.7F, 4.9F, 7.5F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 66, 145, 7.7F, -17.1F, 16.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 150, 30, 7.7F, -17.1F, -1.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 166, 48, 7.7F, 4.9F, -1.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 104, 15, 12.7F, -16.1F, -1.0F, 1, 21, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 40, 99, 12.7F, -16.1F, 16.0F, 1, 21, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 136, 30, 7.7F, 4.9F, 16.0F, 6, 1, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 82, 160, 5.7F, -22.1F, -1.0F, 2, 32, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 82, 160, 5.7F, -22.1F, 7.5F, 2, 32, 1, 0.0F));
		cube_r42.cubeList.add(new ModelBox(cube_r42, 82, 160, 5.7F, -22.1F, 16.0F, 2, 32, 1, 0.0F));
		cube_r42.mirror = false;

		cube_r43 = new ModelRenderer(this);
		cube_r43.setRotationPoint(-19.0F, -8.0F, 0.0F);
		Frame2.addChild(cube_r43);
		setRotationAngle(cube_r43, 0.0F, 0.0F, -0.7854F);
		cube_r43.mirror = true;
		cube_r43.cubeList.add(new ModelBox(cube_r43, 60, 210, -1.0F, -1.0F, -18.0F, 2, 2, 28, 0.0F));
		cube_r43.mirror = false;

		cube_r44 = new ModelRenderer(this);
		cube_r44.setRotationPoint(-19.0F, -5.0F, -17.475F);
		Frame2.addChild(cube_r44);
		setRotationAngle(cube_r44, 0.0F, -3.1416F, 0.0F);
		cube_r44.mirror = true;
		cube_r44.cubeList.add(new ModelBox(cube_r44, 136, 13, -2.0F, 0.0F, -6.5F, 4, 0, 13, 0.0F));
		cube_r44.mirror = false;

		cube_r45 = new ModelRenderer(this);
		cube_r45.setRotationPoint(-17.175F, -3.525F, -20.5F);
		Frame2.addChild(cube_r45);
		setRotationAngle(cube_r45, 0.0F, 0.0F, 0.3491F);
		cube_r45.mirror = true;
		cube_r45.cubeList.add(new ModelBox(cube_r45, 166, 26, 3.95F, -0.5F, -5.5F, 5, 1, 9, 0.0F));
		cube_r45.cubeList.add(new ModelBox(cube_r45, 0, 177, 0.0F, -0.5F, -3.475F, 4, 1, 7, 0.0F));
		cube_r45.cubeList.add(new ModelBox(cube_r45, 166, 26, 3.95F, -0.5F, 29.5F, 5, 1, 9, 0.0F));
		cube_r45.cubeList.add(new ModelBox(cube_r45, 0, 177, 0.0F, -0.5F, 29.475F, 4, 1, 7, 0.0F));
		cube_r45.mirror = false;

		Cable5 = new ModelRenderer(this);
		Cable5.setRotationPoint(0.25F, -3.075F, 10.0F);
		MainFrame3.addChild(Cable5);
		Cable5.mirror = true;
		Cable5.cubeList.add(new ModelBox(Cable5, 178, 96, -4.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F));
		Cable5.mirror = false;

		cube_r46 = new ModelRenderer(this);
		cube_r46.setRotationPoint(-12.2265F, -12.9075F, -0.5401F);
		Cable5.addChild(cube_r46);
		setRotationAngle(cube_r46, 0.0627F, -0.473F, 0.8999F);
		cube_r46.mirror = true;
		cube_r46.cubeList.add(new ModelBox(cube_r46, 16, 185, -0.65F, -0.975F, -1.075F, 1, 2, 2, 0.0F));
		cube_r46.mirror = false;

		cube_r47 = new ModelRenderer(this);
		cube_r47.setRotationPoint(-12.9482F, -13.6301F, -2.2399F);
		Cable5.addChild(cube_r47);
		setRotationAngle(cube_r47, 1.4602F, -1.5147F, -0.5315F);
		cube_r47.mirror = true;
		cube_r47.cubeList.add(new ModelBox(cube_r47, 52, 198, -0.5F, -0.975F, -1.075F, 1, 2, 2, 0.0F));
		cube_r47.mirror = false;

		cube_r48 = new ModelRenderer(this);
		cube_r48.setRotationPoint(-12.5949F, -13.2838F, -0.8887F);
		Cable5.addChild(cube_r48);
		setRotationAngle(cube_r48, 0.119F, -1.0817F, 0.8233F);
		cube_r48.mirror = true;
		cube_r48.cubeList.add(new ModelBox(cube_r48, 196, 147, -1.425F, -1.0F, -1.2F, 2, 2, 2, 0.0F));
		cube_r48.mirror = false;

		cube_r49 = new ModelRenderer(this);
		cube_r49.setRotationPoint(-10.8454F, -10.65F, 0.0F);
		Cable5.addChild(cube_r49);
		setRotationAngle(cube_r49, 0.0296F, -0.1736F, 1.0226F);
		cube_r49.mirror = true;
		cube_r49.cubeList.add(new ModelBox(cube_r49, 48, 194, -2.7F, -1.025F, -1.075F, 3, 2, 2, 0.0F));
		cube_r49.mirror = false;

		cube_r50 = new ModelRenderer(this);
		cube_r50.setRotationPoint(-9.5382F, -4.9997F, 0.0F);
		Cable5.addChild(cube_r50);
		setRotationAngle(cube_r50, 0.0F, 0.0F, 1.3832F);
		cube_r50.mirror = true;
		cube_r50.cubeList.add(new ModelBox(cube_r50, 194, 32, -3.0F, -1.0F, -1.025F, 3, 2, 2, 0.0F));
		cube_r50.mirror = false;

		cube_r51 = new ModelRenderer(this);
		cube_r51.setRotationPoint(-8.0183F, -2.8409F, 0.0F);
		Cable5.addChild(cube_r51);
		setRotationAngle(cube_r51, 0.0F, 0.0F, 0.9905F);
		cube_r51.mirror = true;
		cube_r51.cubeList.add(new ModelBox(cube_r51, 196, 38, -3.0F, -1.0F, -0.975F, 3, 2, 2, 0.0F));
		cube_r51.mirror = false;

		cube_r52 = new ModelRenderer(this);
		cube_r52.setRotationPoint(-3.675F, 0.05F, 0.0F);
		Cable5.addChild(cube_r52);
		setRotationAngle(cube_r52, 0.0F, 0.0F, 0.3054F);
		cube_r52.mirror = true;
		cube_r52.cubeList.add(new ModelBox(cube_r52, 196, 155, -2.0F, -1.0F, -1.025F, 2, 2, 2, 0.0F));
		cube_r52.mirror = false;

		cube_r53 = new ModelRenderer(this);
		cube_r53.setRotationPoint(-5.2074F, -0.3514F, 0.0F);
		Cable5.addChild(cube_r53);
		setRotationAngle(cube_r53, 0.0F, 0.0F, 0.7374F);
		cube_r53.mirror = true;
		cube_r53.cubeList.add(new ModelBox(cube_r53, 190, 56, -4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F));
		cube_r53.mirror = false;

		Cable6 = new ModelRenderer(this);
		Cable6.setRotationPoint(-7.2721F, -8.1936F, 9.1847F);
		MainFrame3.addChild(Cable6);

		cube_r54 = new ModelRenderer(this);
		cube_r54.setRotationPoint(6.723F, 3.9691F, 0.7346F);
		Cable6.addChild(cube_r54);
		setRotationAngle(cube_r54, -0.015F, -0.0934F, 0.5683F);
		cube_r54.mirror = true;
		cube_r54.cubeList.add(new ModelBox(cube_r54, 80, 51, -4.9F, -0.475F, -0.525F, 5, 1, 1, 0.0F));
		cube_r54.mirror = false;

		cube_r55 = new ModelRenderer(this);
		cube_r55.setRotationPoint(2.5274F, 1.2897F, 0.2685F);
		Cable6.addChild(cube_r55);
		setRotationAngle(cube_r55, -0.0068F, -0.0943F, 0.4807F);
		cube_r55.mirror = true;
		cube_r55.cubeList.add(new ModelBox(cube_r55, 180, 173, -2.85F, -0.475F, -0.5F, 3, 1, 1, 0.0F));
		cube_r55.mirror = false;

		cube_r56 = new ModelRenderer(this);
		cube_r56.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cable6.addChild(cube_r56);
		setRotationAngle(cube_r56, 0.0436F, 0.2246F, 0.7023F);
		cube_r56.mirror = true;
		cube_r56.cubeList.add(new ModelBox(cube_r56, 36, 127, -2.85F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
		cube_r56.mirror = false;

		Cable7 = new ModelRenderer(this);
		Cable7.setRotationPoint(-7.2721F, -8.1936F, -17.1847F);
		MainFrame3.addChild(Cable7);

		cube_r57 = new ModelRenderer(this);
		cube_r57.setRotationPoint(6.723F, 3.9691F, -0.7346F);
		Cable7.addChild(cube_r57);
		setRotationAngle(cube_r57, 0.015F, 0.0934F, 0.5683F);
		cube_r57.mirror = true;
		cube_r57.cubeList.add(new ModelBox(cube_r57, 80, 51, -4.9F, -0.475F, -0.475F, 5, 1, 1, 0.0F));
		cube_r57.mirror = false;

		cube_r58 = new ModelRenderer(this);
		cube_r58.setRotationPoint(2.5274F, 1.2897F, -0.2685F);
		Cable7.addChild(cube_r58);
		setRotationAngle(cube_r58, 0.0068F, 0.0943F, 0.4807F);
		cube_r58.mirror = true;
		cube_r58.cubeList.add(new ModelBox(cube_r58, 180, 173, -2.85F, -0.475F, -0.5F, 3, 1, 1, 0.0F));
		cube_r58.mirror = false;

		cube_r59 = new ModelRenderer(this);
		cube_r59.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cable7.addChild(cube_r59);
		setRotationAngle(cube_r59, -0.0436F, -0.2246F, 0.7023F);
		cube_r59.mirror = true;
		cube_r59.cubeList.add(new ModelBox(cube_r59, 36, 127, -2.85F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
		cube_r59.mirror = false;

		Cable8 = new ModelRenderer(this);
		Cable8.setRotationPoint(0.25F, -3.075F, -18.0F);
		MainFrame3.addChild(Cable8);
		setRotationAngle(Cable8, 0.0F, 3.1416F, 0.0F);
		Cable8.cubeList.add(new ModelBox(Cable8, 178, 96, -1.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F));

		cube_r60 = new ModelRenderer(this);
		cube_r60.setRotationPoint(12.5949F, -13.2838F, -0.8887F);
		Cable8.addChild(cube_r60);
		setRotationAngle(cube_r60, 0.119F, 1.0817F, -0.8233F);
		cube_r60.cubeList.add(new ModelBox(cube_r60, 196, 147, -0.575F, -1.0F, -1.2F, 2, 2, 2, 0.0F));

		cube_r61 = new ModelRenderer(this);
		cube_r61.setRotationPoint(12.2265F, -12.9075F, -0.5401F);
		Cable8.addChild(cube_r61);
		setRotationAngle(cube_r61, 0.0627F, 0.473F, -0.8999F);
		cube_r61.cubeList.add(new ModelBox(cube_r61, 16, 185, -0.35F, -0.975F, -1.075F, 1, 2, 2, 0.0F));

		cube_r62 = new ModelRenderer(this);
		cube_r62.setRotationPoint(10.8454F, -10.65F, 0.0F);
		Cable8.addChild(cube_r62);
		setRotationAngle(cube_r62, 0.0296F, 0.1736F, -1.0226F);
		cube_r62.cubeList.add(new ModelBox(cube_r62, 48, 194, -0.3F, -1.025F, -1.075F, 3, 2, 2, 0.0F));

		cube_r63 = new ModelRenderer(this);
		cube_r63.setRotationPoint(12.9482F, -13.6301F, -2.2399F);
		Cable8.addChild(cube_r63);
		setRotationAngle(cube_r63, 1.4602F, 1.5147F, 0.5315F);
		cube_r63.cubeList.add(new ModelBox(cube_r63, 52, 198, -0.5F, -0.975F, -1.075F, 1, 2, 2, 0.0F));

		cube_r64 = new ModelRenderer(this);
		cube_r64.setRotationPoint(9.5382F, -4.9997F, 0.0F);
		Cable8.addChild(cube_r64);
		setRotationAngle(cube_r64, 0.0F, 0.0F, -1.3832F);
		cube_r64.cubeList.add(new ModelBox(cube_r64, 194, 32, 0.0F, -1.0F, -1.025F, 3, 2, 2, 0.0F));

		cube_r65 = new ModelRenderer(this);
		cube_r65.setRotationPoint(8.0183F, -2.8409F, 0.0F);
		Cable8.addChild(cube_r65);
		setRotationAngle(cube_r65, 0.0F, 0.0F, -0.9905F);
		cube_r65.cubeList.add(new ModelBox(cube_r65, 196, 38, 0.0F, -1.0F, -0.975F, 3, 2, 2, 0.0F));

		cube_r66 = new ModelRenderer(this);
		cube_r66.setRotationPoint(3.675F, 0.05F, 0.0F);
		Cable8.addChild(cube_r66);
		setRotationAngle(cube_r66, 0.0F, 0.0F, -0.3054F);
		cube_r66.cubeList.add(new ModelBox(cube_r66, 196, 155, 0.0F, -1.0F, -1.025F, 2, 2, 2, 0.0F));

		cube_r67 = new ModelRenderer(this);
		cube_r67.setRotationPoint(5.2074F, -0.3514F, 0.0F);
		Cable8.addChild(cube_r67);
		setRotationAngle(cube_r67, 0.0F, 0.0F, -0.7374F);
		cube_r67.cubeList.add(new ModelBox(cube_r67, 190, 56, 0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F));

		Handle2 = new ModelRenderer(this);
		Handle2.setRotationPoint(-19.0F, -8.0F, 0.0F);
		MainFrame3.addChild(Handle2);

		cube_r68 = new ModelRenderer(this);
		cube_r68.setRotationPoint(0.0F, 0.0F, 0.0F);
		Handle2.addChild(cube_r68);
		setRotationAngle(cube_r68, 0.0F, 0.0F, -0.7854F);
		cube_r68.mirror = true;
		cube_r68.cubeList.add(new ModelBox(cube_r68, 0, 194, -0.5F, -0.5F, 9.975F, 5, 1, 1, 0.0F));
		cube_r68.cubeList.add(new ModelBox(cube_r68, 194, 26, -0.5F, -0.5F, -18.975F, 5, 1, 1, 0.0F));
		cube_r68.mirror = false;

		cube_r69 = new ModelRenderer(this);
		cube_r69.setRotationPoint(3.5355F, -3.5355F, 10.5F);
		Handle2.addChild(cube_r69);
		setRotationAngle(cube_r69, 0.0F, 0.0F, -0.48F);
		cube_r69.mirror = true;
		cube_r69.cubeList.add(new ModelBox(cube_r69, 36, 115, -1.325F, -10.375F, -0.5F, 1, 11, 1, 0.0F));
		cube_r69.cubeList.add(new ModelBox(cube_r69, -8, 46, -1.325F, -11.375F, -29.5F, 1, 1, 30, 0.0F));
		cube_r69.cubeList.add(new ModelBox(cube_r69, 32, 133, -1.325F, -10.375F, -29.5F, 1, 11, 1, 0.0F));
		cube_r69.mirror = false;

		cube_r70 = new ModelRenderer(this);
		cube_r70.setRotationPoint(28.0322F, 3.6242F, -4.0F);
		Handle2.addChild(cube_r70);
		setRotationAngle(cube_r70, 0.0F, 0.0F, -1.0908F);
		cube_r70.mirror = true;
		cube_r70.cubeList.add(new ModelBox(cube_r70, -2, 52, -1.075F, -0.5F, -12.0F, 1, 1, 24, 0.0F));
		cube_r70.mirror = false;

		cube_r71 = new ModelRenderer(this);
		cube_r71.setRotationPoint(18.0322F, 0.6242F, -4.0F);
		Handle2.addChild(cube_r71);
		setRotationAngle(cube_r71, 0.0F, 0.0F, -1.2217F);
		cube_r71.mirror = true;
		cube_r71.cubeList.add(new ModelBox(cube_r71, -2, 52, -0.5F, -0.5F, -12.0F, 1, 1, 24, 0.0F));
		cube_r71.mirror = false;

		cube_r72 = new ModelRenderer(this);
		cube_r72.setRotationPoint(4.0322F, -5.6258F, -4.0F);
		Handle2.addChild(cube_r72);
		setRotationAngle(cube_r72, 0.0F, 0.0F, -1.2217F);
		cube_r72.mirror = true;
		cube_r72.cubeList.add(new ModelBox(cube_r72, -2, 52, -0.5F, -0.5F, -12.0F, 1, 1, 24, 0.0F));
		cube_r72.mirror = false;

		Computer = new ModelRenderer(this);
		Computer.setRotationPoint(2.0F, -3.0F, 0.0F);
		Main.addChild(Computer);
		Computer.cubeList.add(new ModelBox(Computer, 0, 0, 15.0F, -2.1F, -8.975F, 12, 2, 18, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 0, 20, 14.0F, -4.1F, -8.0F, 14, 2, 16, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 94, 193, 22.0F, -4.6F, 6.75F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 12, 194, 19.0F, -4.6F, 6.75F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 60, 0, 15.0F, -5.1F, -7.0F, 12, 1, 14, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 100, 147, 16.0F, -8.1F, -6.0F, 9, 4, 8, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 24, 190, 25.0F, -7.1F, -5.0F, 1, 3, 5, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 180, 161, 17.0F, -9.1F, -5.0F, 5, 1, 6, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 128, 159, 25.33F, -5.4655F, 3.6615F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 188, 147, 23.5F, -16.1F, 4.0F, 1, 11, 3, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 68, 194, 23.475F, -16.1F, 2.0F, 1, 4, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 10, 196, 17.525F, -16.1F, 2.0F, 1, 4, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 36, 190, 18.5F, -16.1F, 3.0F, 5, 5, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 16, 190, 17.5F, -16.1F, 4.0F, 1, 11, 3, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 188, 168, 18.5F, -16.1F, 4.0F, 5, 6, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 112, 32, 18.475F, -16.925F, 5.95F, 5, 2, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 132, 67, 15.5F, -18.1F, 0.0F, 11, 2, 9, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 194, 199, 21.5F, -18.35F, 7.5F, 3, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 64, 198, 23.25F, -18.35F, 6.0F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 70, 21.5F, -18.35F, 6.0F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 72, 20.0F, -18.35F, 6.0F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 74, 18.75F, -18.35F, 6.0F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 76, 20.0F, -18.35F, 7.5F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 78, 18.75F, -18.35F, 7.5F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 82, 17.5F, -18.35F, 6.0F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 80, 17.5F, -18.35F, 7.5F, 1, 1, 1, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 126, 32, 25.0F, -21.1F, 0.025F, 1, 3, 1, 0.0F));
		Computer.mirror = true;
		Computer.cubeList.add(new ModelBox(Computer, 126, 32, 16.0F, -21.1F, 0.025F, 1, 3, 1, 0.0F));
		Computer.mirror = false;
		Computer.cubeList.add(new ModelBox(Computer, 200, 14, 25.0F, -19.1F, 1.0F, 1, 1, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 158, 179, 16.5F, -23.1F, 0.0F, 9, 5, 2, 0.0F));
		Computer.cubeList.add(new ModelBox(Computer, 200, 17, 16.0F, -19.1F, 1.0F, 1, 1, 2, 0.0F));

		cube_r73 = new ModelRenderer(this);
		cube_r73.setRotationPoint(20.0F, -17.6F, 6.5F);
		Computer.addChild(cube_r73);
		setRotationAngle(cube_r73, -0.8727F, 0.0F, 0.0F);
		cube_r73.cubeList.add(new ModelBox(cube_r73, 0, 185, 4.475F, -0.075F, -7.1F, 1, 2, 7, 0.0F));
		cube_r73.cubeList.add(new ModelBox(cube_r73, 92, 113, -3.475F, -0.075F, -7.1F, 1, 2, 7, 0.0F));
		cube_r73.cubeList.add(new ModelBox(cube_r73, 172, 64, -2.75F, 0.3F, -6.85F, 8, 2, 6, 0.0F));

		cube_r74 = new ModelRenderer(this);
		cube_r74.setRotationPoint(16.5F, -17.6F, 8.25F);
		Computer.addChild(cube_r74);
		setRotationAngle(cube_r74, -0.3883F, 0.0F, 0.0F);
		cube_r74.mirror = true;
		cube_r74.cubeList.add(new ModelBox(cube_r74, 106, 183, -0.525F, -0.475F, -8.05F, 1, 2, 8, 0.0F));
		cube_r74.mirror = false;
		cube_r74.cubeList.add(new ModelBox(cube_r74, 106, 183, 8.525F, -0.475F, -8.05F, 1, 2, 8, 0.0F));

		cube_r75 = new ModelRenderer(this);
		cube_r75.setRotationPoint(21.0F, -13.1F, 5.0F);
		Computer.addChild(cube_r75);
		setRotationAngle(cube_r75, -0.48F, 0.0F, 0.0F);
		cube_r75.cubeList.add(new ModelBox(cube_r75, 92, 122, -2.5F, -3.0F, -0.25F, 5, 5, 2, 0.0F));

		cube_r76 = new ModelRenderer(this);
		cube_r76.setRotationPoint(18.025F, -11.1F, 3.025F);
		Computer.addChild(cube_r76);
		setRotationAngle(cube_r76, -0.7854F, 0.0F, 0.0F);
		cube_r76.cubeList.add(new ModelBox(cube_r76, 92, 195, -0.475F, -2.0F, -1.425F, 1, 2, 3, 0.0F));
		cube_r76.cubeList.add(new ModelBox(cube_r76, 180, 168, 5.425F, -2.0F, -1.425F, 1, 2, 3, 0.0F));

		cube_r77 = new ModelRenderer(this);
		cube_r77.setRotationPoint(23.1685F, -13.8411F, 0.5F);
		Computer.addChild(cube_r77);
		setRotationAngle(cube_r77, -0.5225F, 0.0514F, -0.4535F);
		cube_r77.cubeList.add(new ModelBox(cube_r77, 40, 121, -0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F));

		cube_r78 = new ModelRenderer(this);
		cube_r78.setRotationPoint(23.9F, -11.9943F, 0.2673F);
		Computer.addChild(cube_r78);
		setRotationAngle(cube_r78, -0.1174F, 0.1174F, -0.3909F);
		cube_r78.cubeList.add(new ModelBox(cube_r78, 154, 183, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));

		cube_r79 = new ModelRenderer(this);
		cube_r79.setRotationPoint(24.3156F, -10.1576F, 0.941F);
		Computer.addChild(cube_r79);
		setRotationAngle(cube_r79, 0.3471F, 0.14F, -0.1721F);
		cube_r79.cubeList.add(new ModelBox(cube_r79, 70, 200, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));

		cube_r80 = new ModelRenderer(this);
		cube_r80.setRotationPoint(25.075F, -8.6524F, 2.017F);
		Computer.addChild(cube_r80);
		setRotationAngle(cube_r80, 0.5807F, 0.1971F, -0.3394F);
		cube_r80.cubeList.add(new ModelBox(cube_r80, 200, 67, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));

		cube_r81 = new ModelRenderer(this);
		cube_r81.setRotationPoint(25.5689F, -7.2975F, 3.4027F);
		Computer.addChild(cube_r81);
		setRotationAngle(cube_r81, 0.7729F, 0.1243F, -0.2292F);
		cube_r81.cubeList.add(new ModelBox(cube_r81, 200, 64, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));

		cube_r82 = new ModelRenderer(this);
		cube_r82.setRotationPoint(25.83F, -5.4655F, 4.1615F);
		Computer.addChild(cube_r82);
		setRotationAngle(cube_r82, 0.3897F, 0.05F, -0.121F);
		cube_r82.cubeList.add(new ModelBox(cube_r82, 42, 200, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));

		Coaxium = new ModelRenderer(this);
		Coaxium.setRotationPoint(46.0F, 0.0F, -0.75F);
		Main.addChild(Coaxium);

		cube_r83 = new ModelRenderer(this);
		cube_r83.setRotationPoint(-0.1998F, -10.529F, -3.5F);
		Coaxium.addChild(cube_r83);
		setRotationAngle(cube_r83, 0.0F, 0.0F, 1.1519F);
		cube_r83.cubeList.add(new ModelBox(cube_r83, 134, 147, -3.5F, 3.0F, -11.75F, 7, 7, 7, 0.0F));
		cube_r83.cubeList.add(new ModelBox(cube_r83, 44, 97, -3.0F, -17.0F, -11.25F, 6, 21, 6, 0.0F));
		cube_r83.cubeList.add(new ModelBox(cube_r83, 44, 97, -3.0F, -17.0F, -3.0F, 6, 21, 6, 0.0F));
		cube_r83.cubeList.add(new ModelBox(cube_r83, 134, 147, -3.5F, 3.0F, -3.5F, 7, 7, 7, 0.0F));

		CoaxiumTube_r1 = new ModelRenderer(this);
		CoaxiumTube_r1.setRotationPoint(-46.0F, 0.0F, -6.75F);
		Coaxium.addChild(CoaxiumTube_r1);
		setRotationAngle(CoaxiumTube_r1, 0.0F, 0.0F, -1.1519F);
		CoaxiumTube_r1.mirror = true;
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 44, 97, 6.7F, -21.1F, -8.0F, 6, 21, 6, 0.0F));
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 134, 147, 6.2F, -1.1F, -8.5F, 7, 7, 7, 0.0F));
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 44, 97, 6.7F, -21.1F, 0.25F, 6, 21, 6, 0.0F));
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 134, 147, 6.2F, -1.1F, -0.25F, 7, 7, 7, 0.0F));
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 44, 97, 6.7F, -21.1F, 8.75F, 6, 21, 6, 0.0F));
		CoaxiumTube_r1.cubeList.add(new ModelBox(CoaxiumTube_r1, 134, 147, 6.2F, -1.1F, 8.25F, 7, 7, 7, 0.0F));
		CoaxiumTube_r1.mirror = false;

		CoaxiumTube_r2 = new ModelRenderer(this);
		CoaxiumTube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Coaxium.addChild(CoaxiumTube_r2);
		setRotationAngle(CoaxiumTube_r2, 0.0F, 0.0F, 1.1519F);
		CoaxiumTube_r2.cubeList.add(new ModelBox(CoaxiumTube_r2, 44, 97, -12.7F, -21.1F, 2.0F, 6, 21, 6, 0.0F));
		CoaxiumTube_r2.cubeList.add(new ModelBox(CoaxiumTube_r2, 134, 147, -13.2F, -1.1F, 1.5F, 7, 7, 7, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
