package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.ExternalSystemsCurrentState;
import dev.iwanczuk.driver.dto.GearboxCurrentState;
import dev.iwanczuk.driver.external.ExternalSystemsConfiguration;
import dev.iwanczuk.driver.external.Rpm;
import dev.iwanczuk.driver.gearbox.GearboxConfiguration;
import dev.iwanczuk.driver.external.ExternalSystems;
import dev.iwanczuk.driver.gearbox.Gearbox;

public class DriverFactory {

	private Object[] characteristics = new Object[]{"2000d", "1000d", "1000d", "0.5d", "2500d", 4500d, "1500d", "0.5d", "5000d", "0.7d", 5000d, 5000d, 1500d, 2000d, 3000d, 6500d, 14d};

	public static Driver getDriver(DriverConfiguration driverState) {
		Gearbox gearbox = GearboxConfiguration.createGearbox();
		ExternalSystems externalSystems = ExternalSystemsConfiguration.createExternalSystems();
		return getDriver(driverState, gearbox, externalSystems);

	}

	public static Driver getDriver(DriverConfiguration driverState, GearboxCurrentState gearboxState, ExternalSystemsCurrentState externalSystemsState) {
		Gearbox gearbox = GearboxConfiguration.createGearbox(gearboxState);
		ExternalSystems externalSystems = ExternalSystemsConfiguration.createExternalSystems(externalSystemsState);
		return getDriver(driverState, gearbox, externalSystems);

	}

	private static Driver getDriver(DriverConfiguration driverConfiguration, Gearbox gearbox, ExternalSystems externalSystems) {

		Driver driver = new Driver(gearbox, externalSystems);

		switch(driverConfiguration.mode) {
			case ECO:
				return new EcoModeDriver(driver, Rpm.of(1000d), Rpm.of(2000d));
			case COMFORT:
				return new ComfortModeDriver(driver, Rpm.of(1000d), Rpm.of(2500d), GasPosition.of(0.5d));
			case SPORT:
				return new SportModeDriver(driver, Rpm.of(1500d), Rpm.of(5000d), GasPosition.of(0.5d), GasPosition.of(0.7d));
			default:
				throw new IllegalArgumentException("Cannot create gearbox configuration for this gearbox mode");
		}

	}

}
