package dev.iwanczuk.driver;

import dev.iwanczuk.driver.exception.GearException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class Gear {
	private final int gear;

	private Gear(int gear) {
		if (gear < 0) {
			throw new GearException("Gear cannot be less than 0!");
		}
		this.gear = gear;
	}

	static Gear of(int gear) {
		return new Gear(gear);
	}

	int getValueAsInt() {
		return gear;
	}

	Gear next() {
		return Gear.of(gear + 1);
	}

	Gear previous() {
		return Gear.of(gear - 1);
	}

	Gear doublePrevious() {
		return Gear.of(gear - 2);
	}

	boolean isGreaterThan(Gear gear) {
		return gear.getValueAsInt() < this.gear;
	}

	boolean isLowerThan(Gear gear) {
		return gear.getValueAsInt() > this.gear;
	}
}
