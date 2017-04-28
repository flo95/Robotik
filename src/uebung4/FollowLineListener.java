package uebung4;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class FollowLineListener implements SensorPortListener {

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		System.out.println(aNewValue);
	}

}
