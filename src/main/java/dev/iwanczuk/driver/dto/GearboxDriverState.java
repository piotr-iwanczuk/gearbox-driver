package dev.iwanczuk.driver.dto;

import lombok.Data;

@Data
public class GearboxDriverState {
	private final GearboxDriverMode mode;
	private final AggressiveMode aggressiveMode;
	private final boolean isMDynamicMode;

	private GearboxDriverState() {
		mode = GearboxDriverMode.COMFORT;
		aggressiveMode = AggressiveMode.STANDARD;
		isMDynamicMode = false;
	}

	private GearboxDriverState(GearboxDriverMode mode, AggressiveMode aggressiveMode, boolean isMDynamicMode) {
		this.mode = mode;
		this.aggressiveMode = aggressiveMode;
		this.isMDynamicMode = isMDynamicMode;
	}

	public static GearboxDriverState defaultState() {
		return new GearboxDriverState();
	}

	public static GearboxDriverState of(GearboxDriverMode mode, AggressiveMode aggressiveMode, boolean isMDynamicMode) {
		return new GearboxDriverState(mode, aggressiveMode, isMDynamicMode);
	}
}
