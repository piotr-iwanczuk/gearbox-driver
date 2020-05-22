package dev.iwanczuk.driver;

import java.util.stream.Stream;

import dev.iwanczuk.driver.exception.ExternalSystemsException;
import lombok.RequiredArgsConstructor;

public interface ExternalSystems {
	Rpm getCurrentRpm();
	GasPosition getCurrentGasPosition();
	boolean isDrifting();
	boolean isTrailerAttached();
	CarLevel getCurrentCarLevel();
	ExternalSystemsState getCurrentState();
}

@RequiredArgsConstructor
class ExternalSystemsState {
	final Rpm rpm;
	final GasPosition gasPosition;
	final CarLevel carLevel;
	final boolean isDrifting;
	final boolean isTrailerAttached;
}

enum Level {
	DOWNHILL(1, 3),
	FLAT(4, 6),
	UPHILL(7, 10);

	private final int from;
	private final int to;

	Level(int from, int to) {
		this.from = from;
		this.to = to;
	}

	int getFrom() {
		return from;
	}

	int getTo() {
		return to;
	}

	static Level of(int value) {
		return Stream.of(values()).filter(level -> value >= level.getFrom() && value <= level.getTo()).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cannot find level for number " + value + "!"));
	}
}
