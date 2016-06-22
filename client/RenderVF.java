package com.vfseries.client;

import org.apache.logging.log4j.core.appender.db.jpa.converter.StackTraceElementAttributeConverter;
import org.lwjgl.opengl.GL11;

import com.vfseries.common.EntityVF;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderVF extends Render{

	private static final ResourceLocation texture = new ResourceLocation("vfseries","textures/items/vf1a.png");
	protected ModelBase modelVF;
	
	public RenderVF(){
		this.shadowSize = 0.5f;
		this.modelVF = new ModelVF();
	}
	
	public void doRender(EntityVF entityVF,double x,double y,double z,float p8,float p9){
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x,(float)y,(float)z);//それぞれの軸方向に(x,y,z)分だけ移動
		GL11.glRotatef(180.0f - p8,0.0f,1.0f,0.0f);//y軸周りに(180 - p8)°回転
		
		float f10 = (float)entityVF.getTimeSinceHit() - p9;
		float f11 = entityVF.getDamageTaken() - p9;
		if(f11 < 0.0f){
			f11 = 0.0f;
		}

		if(f10 > 0.0f){
			GL11.glRotatef(MathHelper.sin(f10) * f10 * f11 / 10.0F * (float)entityVF.getForwardDirection(), 1.0f, 0.0f, 0.0f);
		}
		
//		float f10 = (float)entityVF.getTi
		GL11.glScalef(1.0f,1.0f,1.0f);
		this.bindEntityTexture(entityVF);
		this.modelVF.render(entityVF,0.0f,0.0f,-0.1f,0.0f,0.0f,0.0625f);
		
		GL11.glPopMatrix();
	}
	
	protected ResourceLocation getEntityTexture(EntityVF entity){
		return texture;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityVF)entity);
	}
	
	@Override
	public void doRender(Entity entity,double x,double y,double z,float p8,float p9){
		this.doRender((EntityVF)entity,x,y,z,p8,p9);
	}

	
	
}
