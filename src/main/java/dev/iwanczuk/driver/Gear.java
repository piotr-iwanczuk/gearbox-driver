package dev.iwanczuk.driver;

import dev.iwanczuk.driver.exception.GearException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class Gear {
	private final GearboxMode mode;
	private final int gear;

	private Gear(GearboxMode mode, int gear) {
		if (!mode.isInCorrectRange(gear)) {
			throw new GearException("Incorrect gear!");
		}
		this.gear = gear;
		this.mode = mode;
	}

	static Gear of(int gear) {
		return new Gear(GearboxMode.DRIVE, gear);
	}

	static Gear revers() {
		return new Gear(GearboxMode.REVERSE, -1);
	}

	static Gear park() {
		return new Gear(GearboxMode.PARK, 0);
	}

	static Gear neutral() {
		return new Gear(GearboxMode.NEUTRAL, 0);
	}

	int getValue() {
		return gear;
	}

	GearboxMode getMode() {
		return mode;
	}

	Gear getNext() {
		return Gear.of(gear + 1);
	}

	Gear getPrevious() {
		return Gear.of(gear - 1);
	}

	boolean canUpshift() {
		return canSetGear(gear + 1);
	}

	boolean canDownshift() {
		return canSetGear(gear - 1);
	}

	boolean canDoubleDownshift() {
		return canSetGear(gear - 2);
	}

	private boolean canSetGear(int gear) {
		return mode.isInCorrectRange(gear);
	}
}
