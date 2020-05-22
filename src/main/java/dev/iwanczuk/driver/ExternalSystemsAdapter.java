package dev.iwanczuk.driver;

import dev.iwanczuk.driver.exception.ExternalSystemsException;

public class ExternalSystemsAdapter implements ExternalSystems {

	// Nie mam pojęcia jaka wartość prędkości kątowej oznacza drift, przyjąłem 120 ;d
	private static final double DRIFTING_THRESHOLD = 120;

	private final dev.iwanczuk.api.ExternalSystems externalSystems;

	public ExternalSystemsAdapter() {
		externalSystems = new dev.iwanczuk.api.ExternalSystems();
		externalSystems.setCurrentRpm(0);
	}

	public ExternalSystemsAdapter(Rpm rpm) {
		externalSystems = new dev.iwanczuk.api.ExternalSystems();
		externalSystems.setCurrentRpm(rpm.getValueAsDouble());
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
