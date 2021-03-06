package dev.iwanczuk.driver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DriverComfort extends Driver {

	private final RpmConfig rpm;

	@Override
	Gear calculate(GearboxState gearbox, ExternalSystemsState externalSystems) {
		Gear currentGear = gearbox.gear;
		GearRange range = gearbox.range;
		Rpm currentRpm = externalSystems.rpm;
		GasPosition gasPosition = externalSystems.gasPosition;
		boolean isTrailerAttached = externalSystems.isTrailerAttached;
		CarLevel carLevel = externalSystems.carLevel;

		if (shouldUpshift(currentRpm, gasPosition)) {
			return range.next(currentGear);
		}

		if (shouldDownshift(currentRpm, gasPosition, isTrailerAttached, carLevel)) {
			return range.previous(currentGear);
		}

		return currentGear;
	}

	private boolean shouldUpshift(Rpm currentRpm, GasPosition currentGasPosition) {
		return currentRpm.isAboveRange(rpm) && currentGasPosition.isNotKickDown();
	}

	private boolean shouldDownshift(Rpm currentRpm, GasPosition currentGasPosition, boolean isTrailerAttached, CarLevel carLevel) {
		return currentRpm.isBelowRange(rpm) ||
			   goesDownhillWithTrailerAttached(currentRpm, isTrailerAttached, carLevel) ||
			   isKickDownAndRpmIsNotToHigh(currentRpm, currentGasPosition);
	}

	private boolean isKickDownAndRpmIsNotToHigh(Rpm currentRpm, GasPosition currentGasPosition) {
		return currentGasPosition.isKickDown() && rpm.canKickDown(currentRpm);
	}

	private boolean goesDownhillWithTrailerAttached(Rpm currentRpm, boolean isTrailerAttached, CarLevel carLevel) {
		return isTrailerAttached && carLevel.goesDownhill() && currentRpm.isBelow(rpm.getDownHilWithTrailerReductionRpm());
	}

}
