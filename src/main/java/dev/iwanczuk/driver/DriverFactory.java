package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.GearboxDriverState;

class DriverFactory {

	static Driver getDriver(GearboxDriverState driverState) {
		Driver driver;
		RpmConfig rpmRange;
		switch(driverState.getMode()) {
			case ECO:
				rpmRange = decorateRange(new EcoRpm(), driverState);
				driver = new DriverEco(rpmRange);
				break;
			case COMFORT:
				rpmRange = decorateRange(new ComfortRpm(), driverState);
				driver = new DriverComfort(rpmRange);
				break;
			case SPORT:
				rpmRange = decorateRange(new SportRpm(), driverState);
				driver = new DriverSport(rpmRange);
				break;
			default:
				throw new IllegalArgumentException("Cannot create Driver configuration for this GearboxDriverState");
		}

		if (driverState.isMDynamicMode()) {
			driver = new MDynamicMode(driver);
		}

		return driver;
	}

	private static RpmConfig decorateRange(RpmConfig rpmConfig, GearboxDriverState driverState) {
		switch (driverState.getAggressiveMode()) {
		case FIRST_AGGRESSIVE:
			return new FirstAggressiveMode(rpmConfig);
		case SECOND_AGGRESSIVE:
			return new SecondAggressiveMode(rpmConfig);
		default:
			return rpmConfig;
		}
	}

}
