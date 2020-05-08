package dev.iwanczuk.driver.dto;

import dev.iwanczuk.driver.gearbox.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GearboxCurrentState {

	public final State state;
	public final int gear;

}
