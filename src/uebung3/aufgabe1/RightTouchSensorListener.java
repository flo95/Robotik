package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import main.robotik.Driver;

public class RightTouchSensorListener implements SensorPortListener {

	private Driver driver;
	private boolean pressed;

	public RightTouchSensorListener(boolean pressed) {
		this.pressed = pressed;
		driver = Driver.getInstance();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (pressed) {
			driver.rotateLeftDegrees(15);
			driver.driveLeftCurve();
		} else {
			driver.driveRightCurve();
		}
	}

}
