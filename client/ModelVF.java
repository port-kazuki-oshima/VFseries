package com.vfseries.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelVF extends ModelBase{
	private IModelCustom modelvf;
	ResourceLocation res = new ResourceLocation("vfseries","textures/models/vf1a.obj");
	
	public ModelVF() {
		modelvf = AdvancedModelLoader.loadModel(res);
	}
	
	public void render(Entity entity,float p2,float p3,float p4,float p5,float p6,float p7){
		modelvf.renderAll();
//		FMLClientHandler.instance().getClient().renderEngine.bindTexture();
		
//		System.out.println("ModelVF render");
	}
}
