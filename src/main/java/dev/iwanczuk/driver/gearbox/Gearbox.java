package dev.iwanczuk.driver.gearbox;

public interface Gearbox {

	Gear getCurrentGear();
	State getState();

	void changeState(State state);
	void changeMode(Mode state);
	void changeGear(int gear);
	void upshift();
	void downshift();

}
