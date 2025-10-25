package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import com.sown.outerrim.models.blocks.ModelCoaxiumRack;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.util.ui.P3D;

public class RenderBlockCoaxiumRack extends TileEntitySpecialRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(
			"outerrim:textures/models/blocks/coaxium_container.png");
	private final ModelCoaxiumRack model = new ModelCoaxiumRack();

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks) {
		if (!(te instanceof TileEntityCoaxiumContainer))
			return;
		TileEntityCoaxiumContainer tile = (TileEntityCoaxiumContainer) te;

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 3.0, z + 0.5);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(90.0F * tile.getFacing(), 0.0F, 1.0F, 0.0F);
		P3D.glScalef(1.25);
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);
		GL11.glPopMatrix();
	}
}
