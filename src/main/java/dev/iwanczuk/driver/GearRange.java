package dev.iwanczuk.driver;

import dev.iwanczuk.driver.exception.GearException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class GearRange {
	private final Gear firstGear;
	private final Gear maxGear;

	private GearRange(Gear firstGear, Gear maxGear) {
		if (firstGear.isGreaterThan(maxGear)) {
			throw new GearException("Incorrect gear range! " + firstGear + " is greater than " + maxGear);
		}
		this.firstGear = firstGear;
		this.maxGear = maxGear;
	}

	static GearRange of(int firstGear, int maxGear) {
		return new GearRange(Gear.of(firstGear), Gear.of(maxGear));
	}

	Gear next(Gear currentGear) {
		return trim(currentGear.next());
	}

	Gear previous(Gear currentGear) {
		return trim(currentGear.previous());
	}

	Gear doublePrevious(Gear currentGear) {
		return trim(currentGear.doublePrevious());
	}

	private Gear trim(Gear gear) {
		if (gear.isGreaterThan(maxGear)) {
			return maxGear;
		}
		if (gear.isLowerThan(firstGear)) {
			return firstGear;
		}
		return gear;
	}

}
