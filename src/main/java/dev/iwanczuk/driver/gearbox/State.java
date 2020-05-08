package dev.iwanczuk.driver.gearbox;

import java.util.stream.Stream;

public enum State {
	DRIVE(1), PARK(2), REVERSE(3), NEUTRAL(4);

	private int stateNumber;

	State(int stateNumber) {
		this.stateNumber = stateNumber;
	}

	public int getStateNumber() {
		return stateNumber;
	}

	public static State of(int stateNumber) {
		return Stream.of(values()).filter(state -> state.getStateNumber() == stateNumber).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cannot find state for number " + stateNumber + "!"));
	}
}
