package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import main.robotik.Driver;

public class LeftTouchSensorListener implements SensorPortListener {

	private Driver driver;

	public LeftTouchSensorListener() {
		driver = Driver.getInstance();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		driver.rotateLeftDegrees(30);
		driver.driveRightCurve();
	}

}
