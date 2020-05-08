package dev.iwanczuk.driver.gearbox;

class GearboxAdapter implements Gearbox {

	private dev.iwanczuk.api.Gearbox apiGearbox;

	private GearboxAdapter(State state, Gear gear) {
		apiGearbox = new dev.iwanczuk.api.Gearbox();
		Object[] params = new Object[] {state.getStateNumber(), gear.getValue()};
		apiGearbox.setGearBoxCurrentParams(params);
		apiGearbox.setMaxDrive(Gear.MAX_GEAR);
	}

	static GearboxAdapter of() {
		return new GearboxAdapter(State.DRIVE, Gear.of(1));
	}

	static GearboxAdapter of(State state, Gear gear) {
		return new GearboxAdapter(state, gear);
	}

	@Override
	public Gear getCurrentGear() {
		return Gear.of((Integer) apiGearbox.getCurrentGear());
	}

	@Override
	public State getState() {
		return State.of((Integer) apiGearbox.getState());
	}

	@Override
	public void changeState(State state) {

	}

	@Override
	public void changeMode(Mode state) {

	}

	@Override
	public void changeGear(int gear) {

	}

	@Override
	public void upshift() {
		int nextGear = getNextGear().getValue();
		apiGearbox.setCurrentGear(nextGear);
	}

	@Override
	public void downshift() {
		int previousGear = getPreviousGear().getValue();
		apiGearbox.setCurrentGear(previousGear);
	}

	private Gear getNextGear() {
		return Gear.of((Integer) apiGearbox.getCurrentGear()).getNext();
	}

	private Gear getPreviousGear() {
		return Gear.of((Integer) apiGearbox.getCurrentGear()).getPrevious();
	}
}
