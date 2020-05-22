package dev.iwanczuk.driver;

import dev.iwanczuk.driver.exception.ExternalSystemsException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class Rpm {
	private final double rpm;

	private Rpm(double rpm) {
		if (rpm < 0) {
			throw new ExternalSystemsException("Rpm value cannot be negative!");
		}
		this.rpm = rpm;
	}

	static Rpm of(double rpm) {
		return new Rpm(rpm);
	}

	public Rpm multiplyByFactor(double factor) {
		return new Rpm(rpm * factor);
	}

	double getValueAsDouble() {
		return rpm;
	}

	boolean isBelow(Rpm rpmToCompare) {
		return rpm < rpmToCompare.getValueAsDouble();
	}

	boolean isAbove(Rpm rpmToCompare) {
		return rpm > rpmToCompare.getValueAsDouble();
	}

	boolean isBelowRange(RpmConfig range) {
		return rpm < range.getLowerRpm().getValueAsDouble();
	}

	boolean isAboveRange(RpmConfig range) {
		return rpm > range.getUpperRpm().getValueAsDouble();
	}

}
