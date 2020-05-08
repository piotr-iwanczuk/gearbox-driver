package dev.iwanczuk.driver.external;

class ExternalSystemsAdapter implements ExternalSystems {

	private final dev.iwanczuk.api.ExternalSystems externalSystems;

	private ExternalSystemsAdapter(Rpm rpm) {
		externalSystems = new dev.iwanczuk.api.ExternalSystems();
		externalSystems.setCurrentRpm(rpm.getValue());
	}

	static ExternalSystemsAdapter of() {
		return new ExternalSystemsAdapter(Rpm.of(0d));
	}

	static ExternalSystemsAdapter of(Rpm rpm) {
		return new ExternalSystemsAdapter(rpm);
	}

	@Override
	public Rpm currentRpm() {
		return Rpm.of(externalSystems.getCurrentRpm());
	}
}
