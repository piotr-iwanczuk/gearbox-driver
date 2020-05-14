package dev.iwanczuk.driver;

import lombok.RequiredArgsConstructor;

abstract class RpmConfigDecorator extends RpmConfig {

}

@RequiredArgsConstructor
class FirstAggressiveMode extends RpmConfigDecorator {
	private final RpmConfig rpm;

	@Override
	Rpm getLowerRpm() {
		return rpm.getLowerRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getUpperRpm() {
		return rpm.getUpperRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getKickDownRpm() {
		return rpm.getKickDownRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getDoubleKickDownRpm() {
		return rpm.getDoubleKickDownRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getDownHilWithTrailerReductionRpm() {
		return rpm.getDownHilWithTrailerReductionRpm().multiplyByFactor(1.3);
	}
}

@RequiredArgsConstructor
class SecondAggressiveMode extends RpmConfigDecorator {
	private final RpmConfig rpm;

	@Override
	Rpm getLowerRpm() {
		return rpm.getLowerRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getUpperRpm() {
		return rpm.getUpperRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getKickDownRpm() {
		return rpm.getKickDownRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getDoubleKickDownRpm() {
		return rpm.getDoubleKickDownRpm().multiplyByFactor(1.3);
	}

	@Override
	Rpm getDownHilWithTrailerReductionRpm() {
		return rpm.getDownHilWithTrailerReductionRpm().multiplyByFactor(1.3);
	}
}
