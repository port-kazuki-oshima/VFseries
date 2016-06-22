package com.vfseries.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import macross.vfseries.sample.common.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(
	modid="vfseries_dev",
	name="vfseries_dev",
	version="1.7.10_1.0a")
public class MainVF {
	
	//proxy
	@SidedProxy(clientSide="com.vfseries.client.ClientProxy",
				serverSide="com.vfseries.common.CommonProxy")
	public static CommonProxy proxy;

	//generate instance
	@Instance("vfseries_dev")
	public static MainVF instance;

	//ItemVFのインスタンス
	public static Item itemvf;
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try{

		}catch(Exception e){
			e.printStackTrace();
		}finally {

		}

		//itemの登録
		this.itemvf = new ItemVF().setUnlocalizedName("vfseries:vfseries_dev");
		GameRegistry.registerItem(itemvf,"vfseries:vfseries_dev");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		EntityRegistry.registerModEntity(EntityVF.class,"vfseries.entity_vf",1, this,200,2,true);
		proxy.registerRenderers();
		System.out.println("proxy.registerRenderers() = "+proxy);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evnet){
		
	}
	
}
