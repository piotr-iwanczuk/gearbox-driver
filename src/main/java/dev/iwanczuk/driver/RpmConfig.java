package dev.iwanczuk.driver;

abstract class RpmConfig {
	abstract Rpm getLowerRpm();
	abstract Rpm getUpperRpm();
	abstract Rpm getKickDownRpm();
	abstract Rpm getDoubleKickDownRpm();
	abstract Rpm getDownHilWithTrailerReductionRpm();

	boolean canKickDown(Rpm rpm) {
		return rpm.isBelow(getKickDownRpm());
	}
	boolean canDoubleKickDown(Rpm rpm) {
		return rpm.isBelow(getDoubleKickDownRpm());
	}
}

class EcoRpm extends RpmConfig {
	@Override
	Rpm getLowerRpm() {
		return Rpm.of(1000d);
	}

	@Override
	Rpm getUpperRpm() {
		return Rpm.of(2000d);
	}

	@Override
	Rpm getKickDownRpm() {
		return Rpm.of(0d);
	}

	@Override
	Rpm getDoubleKickDownRpm() {
		return Rpm.of(0d);
	}

	@Override
	Rpm getDownHilWithTrailerReductionRpm() {
		return Rpm.of(1500d);
	}
}

class ComfortRpm extends RpmConfig {
	@Override
	Rpm getLowerRpm() {
		return Rpm.of(1000d);
	}

	@Override
	Rpm getUpperRpm() {
		return Rpm.of(2500d);
	}

	@Override
	Rpm getKickDownRpm() {
		return Rpm.of(4500d);
	}

	@Override
	Rpm getDoubleKickDownRpm() {
		return Rpm.of(5000d);
	}

	@Override
	Rpm getDownHilWithTrailerReductionRpm() {
		return Rpm.of(2000d);
	}
}

class SportRpm extends RpmConfig {
	@Override
	Rpm getLowerRpm() {
		return Rpm.of(1500d);
	}

	@Override
	Rpm getUpperRpm() {
		return Rpm.of(5000d);
	}

	@Override
	Rpm getKickDownRpm() {
		return Rpm.of(5000d);
	}

	@Override
	Rpm getDoubleKickDownRpm() {
		return Rpm.of(5000d);
	}

	@Override
	Rpm getDownHilWithTrailerReductionRpm() {
		return Rpm.of(3000d);
	}
}