package dev.iwanczuk.driver.external;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rpm {
	private final double rpm;

	private Rpm(Double rpm) {
		this.rpm = rpm;
	}

	public static Rpm of(Double rpm) {
		if (rpm < 0) {
			throw new ExternalSystemsException("Rpm value cannot be negative!");
		}
		return new Rpm(rpm);
	}

	public Double getValue() {
		return rpm;
	}

	public boolean isLowerThan(Rpm rpmToCompare) {
		return rpm < rpmToCompare.getValue();
	}

	public boolean isGreaterThan(Rpm rpmToCompare) {
		return rpm > rpmToCompare.getValue();
	}

}
