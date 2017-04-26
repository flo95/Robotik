package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;

public class UltraSensorListener implements SensorPortListener {

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (aNewValue > 25) {
			Sound.playTone(300, 100);
		}
		System.out.println("ultra: " + aNewValue);
	}

}
