package dev.iwanczuk.driver.external;

import dev.iwanczuk.driver.dto.ExternalSystemsCurrentState;

public class ExternalSystemsConfiguration {

	public static ExternalSystems createExternalSystems() {
		return ExternalSystemsAdapter.of();
	}

	public static ExternalSystems createExternalSystems(ExternalSystemsCurrentState externalSystemsState) {
		return ExternalSystemsAdapter.of(Rpm.of(externalSystemsState.rpm));
	}

}
