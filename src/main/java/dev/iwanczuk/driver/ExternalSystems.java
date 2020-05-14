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

class ExternalSystemsAdapter implements ExternalSystems {

	// Nie mam pojęcia jaka wartość prędkości kątowej oznacza drift, przyjąłem 120 ;d
	private static final double DRIFTING_THRESHOLD = 120;

	private final dev.iwanczuk.api.ExternalSystems externalSystems;

	ExternalSystemsAdapter() {
		externalSystems = new dev.iwanczuk.api.ExternalSystems();
		externalSystems.setCurrentRpm(0);
	}

	ExternalSystemsAdapter(Rpm rpm) {
		externalSystems = new dev.iwanczuk.api.ExternalSystems();
		externalSystems.setCurrentRpm(rpm.getValue());
	}

	@Override
	public Rpm getCurrentRpm() {
		return Rpm.of(externalSystems.getCurrentRpm());
	}

	@Override
	public GasPosition getCurrentGasPosition() {
		// Brak implementacji w external systems, ale zakładam że tę informację
		// powinienem wyciągnąć właśnie z jakiegoś zewnętrznego systemu
		return GasPosition.of(0.5);
	}

	@Override
	public boolean isTrailerAttached() {
		// Tutaj ta sama sytuacja co wyżej :P
		return false;
	}

	@Override
	public CarLevel getCurrentCarLevel() {
		if (externalSystems.getLights() == null) {
			throw new ExternalSystemsException("You lost your light somewhere!");
		}
		Integer lightsPosition = externalSystems.getLights().getLightsPosition();
		return CarLevel.of(Level.of(lightsPosition));
	}

	@Override
	public boolean isDrifting() {
		return externalSystems.getAngularSpeed() > DRIFTING_THRESHOLD;
	}

	@Override
	public ExternalSystemsState getCurrentState() {
		return new ExternalSystemsState(getCurrentRpm(), getCurrentGasPosition(), getCurrentCarLevel(), isDrifting(), isTrailerAttached());
	}
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
