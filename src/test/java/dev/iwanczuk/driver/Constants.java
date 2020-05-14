package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.GearboxDriverMode;

class Constants {
	static final GearboxDriverMode ECO = GearboxDriverMode.ECO;
	static final GearboxDriverMode COMFORT = GearboxDriverMode.COMFORT;
	static final GearboxDriverMode SPORT = GearboxDriverMode.SPORT;

	static final int ECO_MIN = 1000;
	static final int ECO_MAX = 2000;
	static final int ECO_IN_RANGE = 1500;
	static final int ECO_BELOW_MIN = 999;
	static final int ECO_ABOVE_MAX = 2001;
	static final int ECO_BELOW_TRAILER = 1499;
	static final int ECO_TRAILER = 1500;
	static final int ECO_ABOVE_TRAILER = 1501;

	static final int COMFORT_MIN = 1000;
	static final int COMFORT_MAX = 2500;
	static final int COMFORT_IN_RANGE = 1500;
	static final int COMFORT_BELOW_MIN = 999;
	static final int COMFORT_ABOVE_MAX = 2501;
	static final int COMFORT_BELOW_KICKDOWN = 4499;
	static final int COMFORT_KICKDOWN = 4500;
	static final int COMFORT_ABOVE_KICKDOWN = 4501;
	static final int COMFORT_BELOW_TRAILER = 1999;
	static final int COMFORT_TRAILER = 2000;
	static final int COMFORT_ABOVE_TRAILER = 2001;

	static final int SPORT_MIN = 1500;
	static final int SPORT_MAX = 5000;
	static final int SPORT_IN_RANGE = 2500;
	static final int SPORT_BELOW_MIN = 1499;
	static final int SPORT_ABOVE_MAX = 5001;
	static final int SPORT_BELOW_KICKDOWN = 4999;
	static final int SPORT_KICKDOWN = 5000;
	static final int SPORT_ABOVE_KICKDOWN = 5001;
	static final int SPORT_BELOW_DOUBLE_KICKDOWN = 4999;
	static final int SPORT_DOUBLE_KICKDOWN = 5000;
	static final int SPORT_ABOVE_DOUBLE_KICKDOWN = 5001;
	static final int SPORT_BELOW_TRAILER = 2999;
	static final int SPORT_TRAILER = 3000;
	static final int SPORT_ABOVE_TRAILER = 3001;

	static final double BELOW_KICKDOWN = 0.4;
	static final double EQUAL_KICKDOWN = 0.5;
	static final double ABOVE_KICKDOWN = 0.6;

	static final double EQUAL_DOUBLE_KICKDOWN = 0.7;
	static final double ABOVE_DOUBLE_KICKDOWN = 0.8;
}
