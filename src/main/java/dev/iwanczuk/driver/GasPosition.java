package dev.iwanczuk.driver;

class GasPosition {

	private final Double position;

	private GasPosition(Double position) {
		this.position = position;
	}

	public static GasPosition of(Double position) {
		if (position < 0) {
			throw new IllegalArgumentException("Gas position value cannot be negative!");
		}
		return new GasPosition(position);
	}

}
