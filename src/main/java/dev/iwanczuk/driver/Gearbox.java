package dev.iwanczuk.driver;

import java.util.stream.Stream;

import dev.iwanczuk.driver.dto.GearboxModeDto;

public interface Gearbox {
	Gear currentGear();
	GearboxMode currentMode();
	GearboxState currentState();
	GearRange currentRange();

	void changeMode(GearboxMode mode);
	void changeGear(Gear gear);
	void upshiftGear();
	void downshiftGear();
}

class GearboxState {
	final GearboxMode mode;
	final GearRange range;
	final Gear gear;

	GearboxState(GearboxMode mode, Gear gear, GearRange range) {
		this.mode = mode;
		this.gear = gear;
		this.range = range;
	}
}

enum GearboxMode {
	DRIVE(1),
	PARK(2),
	REVERSE(3),
	NEUTRAL(4);

	private final int stateNumber;

	GearboxMode(int stateNumber) {
		this.stateNumber = stateNumber;
	}

	int getStateNumber() {
		return stateNumber;
	}

	static GearboxMode of(GearboxModeDto gearboxMode) {
		return valueOf(gearboxMode.name());
	}

	static GearboxMode of(int stateNumber) {
		return Stream.of(values()).filter(state -> state.getStateNumber() == stateNumber).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cannot find state for number " + stateNumber + "!"));
	}
}
