package dev.iwanczuk.driver;

class GasPosition {

	private static final double kickDownTh = 0.5;
	private static final double doubleKickDownTh = 0.7;
	private final double position;

	private GasPosition(double position) {
		this.position = position;
	}

	static GasPosition of(double position) {
		if (position < 0) {
			throw new IllegalArgumentException("Gas position value = " + position + " must be in range <0;1>!");
		}
		if (position > 1) {
			throw new IllegalArgumentException("Gas position value = " + position + " must be in range <0;1>!");
		}
		return new GasPosition(position);
	}

	boolean isKickDown() {
		return position > kickDownTh;
	}

	boolean isNotKickDown() {
		return !isKickDown();
	}

	boolean isDoubleKickDown() {
		return position > doubleKickDownTh;
	}

	double getValue() {
		return position;
	}

}
