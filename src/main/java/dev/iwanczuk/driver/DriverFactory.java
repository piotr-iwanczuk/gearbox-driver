package dev.iwanczuk.driver;

class DriverFactory {

	static Driver getDriver(GearboxDriverState driverState) {
		Driver driver;
		RpmConfig rpmRange;
		switch(driverState.mode) {
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

		if (driverState.isMDynamicMode) {
			driver = new MDynamicMode(driver);
		}

		return driver;
	}

	private static RpmConfig decorateRange(RpmConfig rpmRange, GearboxDriverState driverState) {
		switch (driverState.aggressiveMode) {
		case FIRST_AGGRESSIVE:
			return new FirstAggressiveMode(rpmRange);
		case SECOND_AGGRESSIVE:
			return new SecondAggressiveMode(rpmRange);
		default:
			return rpmRange;
		}
	}

}
