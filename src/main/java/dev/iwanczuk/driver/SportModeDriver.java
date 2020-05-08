package dev.iwanczuk.driver;

import dev.iwanczuk.driver.external.Rpm;

class SportModeDriver extends Driver {

	private final Driver baseDriver;
	private final Rpm minRpm;
	private final Rpm maxRpm;
	private final GasPosition kickDownThreshold;
	private final GasPosition doubleKickDownThreshold;

	SportModeDriver(Driver baseDriver, Rpm minRpm, Rpm maxRpm, GasPosition kickDownThreshold, GasPosition doubleKickDownThreshold) {
		super(baseDriver.gearbox, baseDriver.externalSystems);
		this.baseDriver = baseDriver;
		this.minRpm = minRpm;
		this.maxRpm = maxRpm;
		this.kickDownThreshold = kickDownThreshold;
		this.doubleKickDownThreshold = doubleKickDownThreshold;
	}

	public void manageGearbox() {

		Rpm currentRpm = externalSystems.currentRpm();

		if (currentRpm.isGreaterThan(minRpm)) {
			gearbox.upshift();
		}

		if (currentRpm.isLowerThan(maxRpm)) {
			gearbox.downshift();
		}

	}

}
