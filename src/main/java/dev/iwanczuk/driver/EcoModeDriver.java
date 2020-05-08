package dev.iwanczuk.driver;

import dev.iwanczuk.driver.external.Rpm;

class EcoModeDriver extends Driver {

	private final Driver baseDriver;
	private final Rpm minRpm;
	private final Rpm maxRpm;

	public EcoModeDriver(Driver baseDriver, Rpm minRpm, Rpm maxRpm) {
		super(baseDriver.gearbox, baseDriver.externalSystems);
		this.baseDriver = baseDriver;
		this.minRpm = minRpm;
		this.maxRpm = maxRpm;
	}

	public void manageGearbox() {

		Rpm currentRpm = externalSystems.currentRpm();

		if (currentRpm.isGreaterThan(maxRpm)) {
			gearbox.upshift();
		}

		if (currentRpm.isLowerThan(minRpm)) {
			gearbox.downshift();
		}

	}

}
