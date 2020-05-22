package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.GearboxDriverState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GearboxDriver {
	private final GearboxDriverState driverState;
	private final Gearbox gearbox;
	private final ExternalSystems externalSystems;

	public void manageGear() {
		if (isDriveMode()) {
			Driver calculator = DriverFactory.getDriver(driverState);
			Gear calculatedGear = calculator.calculate(gearbox.currentState(), externalSystems.getCurrentState());
			gearbox.changeGear(calculatedGear);
		}
	}

	public void manualUpshift() {
		gearbox.upshiftGear();
	}

	public void manualDownshift() {
		gearbox.downshiftGear();
	}

	public void setDriveMode() {
		gearbox.changeMode(GearboxMode.DRIVE);
	}

	public void setParkMode() {
		gearbox.changeMode(GearboxMode.PARK);
	}

	public void setReversMode() {
		gearbox.changeMode(GearboxMode.REVERSE);
	}

	public void setNeutralMode() {
		gearbox.changeMode(GearboxMode.NEUTRAL);
	}

	private boolean isDriveMode() {
		return GearboxMode.DRIVE.equals(gearbox.currentMode());
	}
}
