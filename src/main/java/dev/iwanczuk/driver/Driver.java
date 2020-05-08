package dev.iwanczuk.driver;

import dev.iwanczuk.driver.external.ExternalSystems;
import dev.iwanczuk.driver.gearbox.Gearbox;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Driver {

	protected final Gearbox gearbox;
	protected final ExternalSystems externalSystems;

	public void manageGearbox() {

	}

	public int getCurrentGear() {
		return gearbox.getCurrentGear().getValue();
	}
}
