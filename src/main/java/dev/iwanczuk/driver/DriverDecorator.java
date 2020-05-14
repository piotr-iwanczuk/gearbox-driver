package dev.iwanczuk.driver;

import lombok.RequiredArgsConstructor;

abstract class DriverDecorator extends Driver {

}

@RequiredArgsConstructor
class MDynamicMode extends DriverDecorator {
	private final Driver baseGearManager;

	@Override
	Gear calculate(GearboxState gearbox, ExternalSystemsState externalSystems) {

		if (externalSystems.isDrifting) {
			return gearbox.gear;
		}

		return baseGearManager.calculate(gearbox, externalSystems);
	}
}