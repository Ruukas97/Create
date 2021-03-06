package com.simibubi.create.modules.contraptions.relays.gauge;

import com.simibubi.create.modules.contraptions.base.KineticTileEntity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

public class GaugeTileEntity extends KineticTileEntity {

	public float dialTarget;
	public float dialState;
	public float prevDialState;
	public int color;

	public GaugeTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putFloat("Value", dialTarget);
		compound.putInt("Color", color);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		dialTarget = compound.getFloat("Value");
		color = compound.getInt("Color");
		super.read(compound);
	}

	@Override
	public void tick() {
		super.tick();
		prevDialState = dialState;
		dialState += (dialTarget - dialState) * .125f;
		if (dialState > 1 && world.rand.nextFloat() < 1 / 2f)
			dialState -= (dialState - 1) * world.rand.nextFloat();
	}

}
