package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.AggressiveMode;
import dev.iwanczuk.driver.dto.GearboxDriverMode;

class DriverConf {

	private final Driver driver;
	private final int gear;
	private final double rpm;
	private final double gasPosition;
	private final Level level;
	private final boolean isDrifting;
	private final boolean isTrailerAttached;

	DriverConf(Driver driver, int gear, double rpm, double gasPosition, Level level, boolean isDrifting, boolean isTrailerAttached) {
		this.driver = driver;
		this.gear = gear;
		this.rpm = rpm;
		this.gasPosition = gasPosition;
		this.level = level;
		this.isDrifting = isDrifting;
		this.isTrailerAttached = isTrailerAttached;
	}

	static Builder mode(GearboxDriverMode mode) {
		return new Builder(mode);
	}

	int calculate() {
		GearboxState gearbox = new GearboxState(Gear.of(gear));
		ExternalSystemsState externalSystems = new ExternalSystemsState(Rpm.of(rpm), GasPosition.of(gasPosition), CarLevel.of(level), isDrifting, isTrailerAttached);
		return driver.calculate(gearbox, externalSystems).getValue();
	}

	static final class Builder {
		private final GearboxDriverMode gearboxDriverMode;
		private int gear;
		private double rpm;
		private double gasPosition;
		private Level level = Level.FLAT;
		private AggressiveMode aggressiveMode = AggressiveMode.STANDARD;
		private boolean isMDynamicMode = false;
		private boolean isDrifting = false;
		private boolean isTrailerAttached = false;

		Builder(GearboxDriverMode mode) {
			gearboxDriverMode = mode;
		}

		Builder onGear(int gear) {
			this.gear = gear;
			return this;
		}

		Builder withRpm(double rpm) {
			this.rpm = rpm;
			return this;
		}

		Builder withGasPosition(double gasPosition) {
			this.gasPosition = gasPosition;
			return this;
		}

		Builder onFirstAggressiveMode() {
			this.aggressiveMode = AggressiveMode.FIRST_AGGRESSIVE;
			return this;
		}

		Builder onSecondAggressiveMode() {
			this.aggressiveMode = AggressiveMode.FIRST_AGGRESSIVE;
			return this;
		}

		Builder withTrailer() {
			this.isTrailerAttached = true;
			return this;
		}

		Builder goesDownhill() {
			this.level = Level.DOWNHILL;
			return this;
		}

		Builder onMDynamicMode() {
			this.isMDynamicMode = true;
			return this;
		}

		Builder isDrifting() {
			this.isDrifting = true;
			return this;
		}

		DriverConf build() {
			Driver driver = DriverFactory.getDriver(new GearboxDriverState(gearboxDriverMode, aggressiveMode, isMDynamicMode));
			return new DriverConf(driver, gear, rpm, gasPosition, level, isDrifting, isTrailerAttached);
		}
	}
}


