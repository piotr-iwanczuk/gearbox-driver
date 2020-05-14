package dev.iwanczuk.driver;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class CarLevel {
	private final Level level;

	private CarLevel(Level level) {
		this.level = level;
	}

	static CarLevel of(Level level) {
		return new CarLevel(level);
	}

	Level getValue() {
		return level;
	}

	boolean goesFlat() {
		return Level.FLAT.equals(level);
	}

	boolean goesUphill() {
		return Level.UPHILL.equals(level);
	}

	boolean goesDownhill() {
		return Level.DOWNHILL.equals(level);
	}

}
