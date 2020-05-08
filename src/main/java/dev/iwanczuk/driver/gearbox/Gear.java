package dev.iwanczuk.driver.gearbox;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Gear {

	static final int MAX_GEAR = 8;
	private final int gear;

	private Gear(int gear) {
		this.gear = gear;
	}

	public static Gear of(int gear) {
		if (gear < 0) {
			throw new GearboxException("Gear value cannot be less than 0!");
		}
		if (gear > MAX_GEAR) {
			throw new GearboxException("Gear value cannot be greater than " + MAX_GEAR + "!");
		}

		return new Gear(gear);
	}

	public int getValue() {
		return gear;
	}

	public Gear getNext() {
		return Gear.of(gear + 1);
	}

	public Gear getPrevious() {
		return Gear.of(gear - 1);
	}
}
