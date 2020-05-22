package dev.iwanczuk;

import dev.iwanczuk.driver.ExternalSystems;
import dev.iwanczuk.driver.ExternalSystemsAdapter;
import dev.iwanczuk.driver.Gearbox;
import dev.iwanczuk.driver.GearboxAdapter;
import dev.iwanczuk.driver.GearboxDriver;
import dev.iwanczuk.driver.dto.GearboxDriverState;

public class Application {

	public static void main(String[] args) {

		Gearbox gearbox = new GearboxAdapter();
		ExternalSystems externalSystems = new ExternalSystemsAdapter();
		GearboxDriverState gearboxDriverState = GearboxDriverState.defaultState();

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxDriverState, gearbox, externalSystems);
		gearboxDriver.manageGear();

	}

}
