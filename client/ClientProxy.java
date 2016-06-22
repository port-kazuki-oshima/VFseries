package com.vfseries.client;

import com.vfseries.common.EntityVF;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import macross.vfseries.sample.common.CommonProxy;
import mod.forge.boat.client.ForgeRenderBoat;
import mod.forge.boat.common.ForgeEntityBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{
	
	@Override
	public World getClientWorld(){
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public void registerRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityVF.class,new RenderVF());
	}
}
