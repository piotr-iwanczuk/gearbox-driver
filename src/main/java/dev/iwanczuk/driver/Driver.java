package dev.iwanczuk.driver;

abstract class Driver {
	abstract Gear calculate(GearboxState gearbox, ExternalSystemsState externalSystems);
}
