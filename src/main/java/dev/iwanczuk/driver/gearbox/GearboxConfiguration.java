package dev.iwanczuk.driver.gearbox;

import dev.iwanczuk.driver.dto.GearboxCurrentState;

public class GearboxConfiguration {

	public static Gearbox createGearbox() {
		return GearboxAdapter.of();
	}

	public static Gearbox createGearbox(GearboxCurrentState gearboxState) {
		return GearboxAdapter.of(gearboxState.state, Gear.of(gearboxState.gear));
	}

}
