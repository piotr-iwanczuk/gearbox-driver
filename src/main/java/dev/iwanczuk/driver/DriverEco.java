package dev.iwanczuk.driver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DriverEco extends Driver {

	private final RpmConfig rpm;

	@Override
	Gear calculate(GearboxState gearbox, ExternalSystemsState externalSystems) {
		Gear currentGear = gearbox.gear;
		GearRange range = gearbox.range;
		Rpm currentRpm = externalSystems.rpm;
		boolean isTrailerAttached = externalSystems.isTrailerAttached;
		CarLevel carLevel = externalSystems.carLevel;

		if (shouldUpshift(currentRpm)) {
			return range.next(currentGear);
		}

		if (shouldDownshift(currentRpm, isTrailerAttached, carLevel)) {
			return range.previous(currentGear);
		}

		return currentGear;
	}

	private boolean shouldUpshift(Rpm currentRpm) {
		return currentRpm.isAbove(rpm.getUpperRpm());
	}

	private boolean shouldDownshift(Rpm currentRpm, boolean isTrailerAttached, CarLevel carLevel) {
		return currentRpm.isBelow(rpm.getLowerRpm()) || goesDownhillWithTrailerAttached(currentRpm, isTrailerAttached, carLevel);
	}

	private boolean goesDownhillWithTrailerAttached(Rpm currentRpm, boolean isTrailerAttached, CarLevel carLevel) {
		return isTrailerAttached && carLevel.goesDownhill() && currentRpm.isBelow(rpm.getDownHilWithTrailerReductionRpm());
	}

}
