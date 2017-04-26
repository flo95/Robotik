package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;

public class RightTouchSensorListener implements SensorPortListener {

	private Driver driver;
	private TouchSensor rigth;

	public RightTouchSensorListener() {
		rigth = new TouchSensor(SensorPort.S1);
		driver = Driver.getInstance();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (rigth.isPressed()) {
			driver.rotateLeftDegrees(15);
			driver.driveLeftCurve();
		} else {
			driver.driveRightCurve();
		}
	}

}
