package dev.iwanczuk.driver;

public class GearboxAdapter implements Gearbox {

	private final dev.iwanczuk.api.Gearbox apiGearbox;
	private final GearRange gearRange;

	public GearboxAdapter() {
		apiGearbox = new dev.iwanczuk.api.Gearbox();
		Object[] params = new Object[] { GearboxMode.PARK.getStateNumber(), 1};
		apiGearbox.setGearBoxCurrentParams(params);
		apiGearbox.setMaxDrive(GearboxMode.DRIVE.getStateNumber());
		gearRange = GearRange.of(1, 8);
	}

	public GearboxAdapter(GearboxMode state, Gear gear, GearRange gearRange) {
		apiGearbox = new dev.iwanczuk.api.Gearbox();
		Object[] params = new Object[] {state.getStateNumber(), gear.getValueAsInt()};
		apiGearbox.setGearBoxCurrentParams(params);
		apiGearbox.setMaxDrive(GearboxMode.DRIVE.getStateNumber());
		this.gearRange = gearRange;
	}

	@Override
	public Gear currentGear() {
		return Gear.of((Integer) apiGearbox.getCurrentGear());
	}

	@Override
	public GearboxMode currentMode() {
		return GearboxMode.of((Integer) apiGearbox.getState());
	}

	@Override
	public GearRange currentRange() {
		return gearRange;
	}

	@Override
	public GearboxState currentState() {
		return new GearboxState(currentMode(), currentGear(), currentRange());
	}

	@Override
	public void changeMode(GearboxMode mode) {
		Object[] params = new Object[] {mode.getStateNumber(), currentGear().getValueAsInt()};
		apiGearbox.setGearBoxCurrentParams(params);
	}

	@Override
	public void changeGear(Gear gear) {
		apiGearbox.setCurrentGear(gear.getValueAsInt());
	}

	@Override
	public void upshiftGear() {
		apiGearbox.setCurrentGear(currentGear().next().getValueAsInt());
	}

	@Override
	public void downshiftGear() {
		apiGearbox.setCurrentGear(currentGear().previous().getValueAsInt());
	}
}
