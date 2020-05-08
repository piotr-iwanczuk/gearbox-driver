package dev.iwanczuk.driver;

import dev.iwanczuk.driver.external.Rpm;

class ComfortModeDriver extends Driver {

	private final Driver baseDriver;
	private final Rpm minRpm;
	private final Rpm maxRpm;
	private final GasPosition kickDownThreshold;

	public ComfortModeDriver(Driver baseDriver, Rpm minRpm, Rpm maxRpm, GasPosition kickDownThreshold) {
		super(baseDriver.gearbox, baseDriver.externalSystems);
		this.baseDriver = baseDriver;
		this.minRpm = minRpm;
		this.maxRpm = maxRpm;
		this.kickDownThreshold = kickDownThreshold;
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
