package com.vfseries.common;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVF extends Entity{

	private double velocityX;
	private double velocityY;
	private double velocityZ;
	
	public EntityVF(World world){
		super(world);
		this.preventEntitySpawning = true;
		this.yOffset = this.height/2.0f;
		this.setSize(1.0f,1.0f);
		
	}
	
	public EntityVF(World world,double posx,double posy,double posz){
		super(world);
		
		this.setPosition(posx,posy+(double)this.yOffset,posz);
		this.motionX = 0.0d;
		this.motionY = 0.0d;
		this.motionZ = 0.0d;
		this.prevPosX = posx;
		this.prevPosY = posy;
		this.prevPosZ = posz;
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		this.setPosition(this.posX,this.posY,this.posZ);
		this.setRotation(this.rotationYaw,this.rotationPitch);
		
//		this.riddenByEntity.mountEntity(p_70078_1_);
		
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0f));
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public float getDamageTaken() {
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	public int getTimeSinceHit() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

}
