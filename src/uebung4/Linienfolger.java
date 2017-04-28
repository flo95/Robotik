package uebung4;

import lejos.nxt.SensorPort;

/**
 * Ziel dieser Übung ist es, einen Roboter zu konstruieren und zu programmieren,
 * der einer schwarzen Linie folgt.
 */
public class Linienfolger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FollowLineListener followLineListener = new FollowLineListener();
		SensorPort.S4.addSensorPortListener(followLineListener);
		while (true) {
			
		}
	}

}
