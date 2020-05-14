package dev.iwanczuk.driver;

import java.util.stream.Stream;

import dev.iwanczuk.driver.dto.GearboxModeDto;
import lombok.RequiredArgsConstructor;

public interface Gearbox {
	Gear getCurrentGear();
	GearboxMode getCurrentMode();
	GearboxState getCurrentState();

	void changeMode(GearboxMode mode);
	void changeGear(Gear gear);
	void upshiftGear();
	void downshiftGear();
}

class GearboxState {
	final GearboxMode mode;
	final Gear gear;

	GearboxState(Gear gear) {
		this.mode = gear.getMode();
		this.gear = gear;
	}
}

class GearboxAdapter implements Gearbox {

	private final dev.iwanczuk.api.Gearbox apiGearbox;

	GearboxAdapter() {
		apiGearbox = new dev.iwanczuk.api.Gearbox();
		Object[] params = new Object[] { GearboxMode.PARK.getStateNumber(), 0};
		apiGearbox.setGearBoxCurrentParams(params);
		apiGearbox.setMaxDrive(GearboxMode.DRIVE.getMaxGear());
	}

	GearboxAdapter(GearboxMode state, Gear gear) {
		apiGearbox = new dev.iwanczuk.api.Gearbox();
		Object[] params = new Object[] {state.getStateNumber(), gear.getValue()};
		apiGearbox.setGearBoxCurrentParams(params);
		apiGearbox.setMaxDrive(GearboxMode.DRIVE.getMaxGear());
	}

	@Override
	public Gear getCurrentGear() {
		return Gear.of((Integer) apiGearbox.getCurrentGear());
	}

	@Override
	public GearboxMode getCurrentMode() {
		return GearboxMode.of((Integer) apiGearbox.getState());
	}

	@Override
	public GearboxState getCurrentState() {
		return new GearboxState(getCurrentGear());
	}

	@Override
	public void changeMode(GearboxMode mode) {
		Object[] params = new Object[] {mode.getStateNumber(), getCurrentGear().getValue()};
		apiGearbox.setGearBoxCurrentParams(params);
	}

	@Override
	public void changeGear(Gear gear) {
		apiGearbox.setCurrentGear(gear.getValue());
	}

	@Override
	public void upshiftGear() {
		apiGearbox.setCurrentGear(getCurrentGear().getNext().getValue());
	}

	@Override
	public void downshiftGear() {
		apiGearbox.setCurrentGear(getCurrentGear().getPrevious().getValue());
	}
}

enum GearboxMode {
	DRIVE(1, 1, 8),
	PARK(2, 0),
	REVERSE(3, -1),
	NEUTRAL(4, 0);

	private final int stateNumber;
	private final int minGear;
	private final int maxGear;

	GearboxMode(int stateNumber, int minGear, int maxGear) {
		this.stateNumber = stateNumber;
		this.minGear = minGear;
		this.maxGear = maxGear;
	}

	GearboxMode(int stateNumber, int gear) {
		this.stateNumber = stateNumber;
		this.minGear = gear;
		this.maxGear = gear;
	}

	boolean isInCorrectRange(int gear) {
		return gear >= minGear && gear <= maxGear;
	}

	int getStateNumber() {
		return stateNumber;
	}

	int getMinGear() {
		return minGear;
	}

	int getMaxGear() {
		return maxGear;
	}

	static GearboxMode of(GearboxModeDto gearboxMode) {
		return valueOf(gearboxMode.name());
	}

	static GearboxMode of(int stateNumber) {
		return Stream.of(values()).filter(state -> state.getStateNumber() == stateNumber).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cannot find state for number " + stateNumber + "!"));
	}
}
