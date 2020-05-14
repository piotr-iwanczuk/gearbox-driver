package dev.iwanczuk.driver;

import dev.iwanczuk.driver.dto.AggressiveMode;
import dev.iwanczuk.driver.dto.GearboxDriverMode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GearboxDriverState {
	final GearboxDriverMode mode;
	final AggressiveMode aggressiveMode;
	final boolean isMDynamicMode;
}
