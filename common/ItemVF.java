package com.vfseries.common;

import java.util.List;

import macross.vfseries.sample.common.EntityVFzero;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemVF extends Item{
	public ItemVF() {
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer){
		float f4 = 1.0f;
		
		//向いている角度,正面が0渡。真下が90渡
		float pitch = entityPlayer.prevRotationPitch + (entityPlayer.rotationPitch - entityPlayer.prevRotationPitch) * f4;
		
		//左右の旋回角度。X方向が前方で90渡
		float yaw = entityPlayer.prevRotationYaw + (entityPlayer.rotationYaw - entityPlayer.prevRotationYaw) * f4;
		
		double posx = entityPlayer.prevPosX + (entityPlayer.posX - entityPlayer.prevPosX) * (double)f4;
		double posy = entityPlayer.prevPosY + (entityPlayer.posY - entityPlayer.prevPosY) * (double)f4;
		double posz = entityPlayer.prevPosZ + (entityPlayer.posZ - entityPlayer.prevPosZ) * (double)f4;
		
		//座標を決める(x,y,z)
		Vec3 vec = Vec3.createVectorHelper(posx,posy,posz);
		
		float cosYaw = MathHelper.cos((-yaw * 0.017453292f) - (float)Math.PI);
		float sinYaw = MathHelper.sin((-yaw * 0.017453292f) - (float)Math.PI);
		
		float cosPitch = -MathHelper.cos(-pitch * 0.017453292f);
		float sinPitch = MathHelper.sin(-pitch * 0.017453292f);
		
		float f18 = sinYaw * cosPitch;
		float f20 = cosYaw * cosPitch;
		double length = 5.0d;
		
		Vec3 posVec = vec.addVector((double)f18*length,(double)sinPitch*length,(double)f20 * length);
		MovingObjectPosition movingObjectPosition = world.rayTraceBlocks(vec,posVec,true);
		
		System.out.println("vec = "+vec+": posVec = "+posVec);
		
		if(movingObjectPosition == null){
			return itemStack;
		}else{
			Vec3 vec3 = entityPlayer.getLook(f4);
			boolean b26 = false;
			float f27 = 1.0f;
			
			double addCoordX = vec3.xCoord * length;
			double addCoordY = vec3.yCoord * length;
			double addCoordZ = vec3.zCoord * length;
			
			
			List list = world.getEntitiesWithinAABBExcludingEntity(entityPlayer,entityPlayer.boundingBox.addCoord(addCoordX,addCoordY,addCoordZ).expand((double)f27,(double)f27,(double)f27));
			
			int i;
			for(i=0;i < list.size();i++){
				Entity entity = (Entity)list.get(i);
				if(entity.canBeCollidedWith()){
					float collisionBorderSize = entity.getCollisionBorderSize();
					AxisAlignedBB axisAlignedBB = entity.boundingBox.expand((double)collisionBorderSize,(double)collisionBorderSize,(double)collisionBorderSize);
					if(axisAlignedBB.isVecInside(vec)){
						b26 = true;
					}
				}
			}
			
			if(b26){
				return itemStack;
			}else{
				if(movingObjectPosition.typeOfHit ==  MovingObjectPosition.MovingObjectType.BLOCK){
					int blockX = movingObjectPosition.blockX;
					int blockY = movingObjectPosition.blockY;
					int blockZ = movingObjectPosition.blockZ;
					if(world.getBlock(blockX,blockY,blockZ) == Blocks.snow_layer){
						blockY--;
					}
				
					EntityVF entity_vf = new EntityVF(world,(double)((float)blockX+0.5f),(double)((float)blockY+1.0f),(double)((float)blockZ+0.5f));
					entity_vf.rotationYaw = (float)(((MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
					
					if(!world.getCollidingBoundingBoxes(entity_vf,entity_vf.boundingBox.expand(-0.1d,-0.1d,-0.1d)).isEmpty()){
						return itemStack;
					}
					
					if(!world.isRemote){
						world.spawnEntityInWorld(entity_vf);
					}
					
					if(!entityPlayer.capabilities.isCreativeMode){
						--itemStack.stackSize;
					}
				}
				
				return itemStack;
			}
		}
	}
}
