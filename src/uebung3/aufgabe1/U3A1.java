package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;

public class U3A1 {

	private Driver driver;
	private UltraSonicSensorExtended ultraSensor;
	private SensorPortListener rightListener;
	private SensorPortListener leftListener;
	private boolean firstWallHit;

	public void run() {
		driver = Driver.getInstance();
		firstWallHit = false;

		driver.forward();
		TouchSensor rigth = new TouchSensor(SensorPort.S1);
		TouchSensor left = new TouchSensor(SensorPort.S2);
		while (!firstWallHit) {
			if (rigth.isPressed() || left.isPressed()) {
				firstWallHit = true;
			}
		}

		rightListener = new RightTouchSensorListener();
		leftListener = new LeftTouchSensorListener();
		ultraSensor = new UltraSonicSensorExtended(SensorPort.S3);

		SensorPort.S1.addSensorPortListener(rightListener);
		SensorPort.S2.addSensorPortListener(leftListener);

		UltraSensorListener aListener = new UltraSensorListener();
		ultraSensor.addSensorPortListener(aListener);

		driveKroetenwanderung();
	}

	/**
	 * Aufgabe 2
	 */
	private void driveKroetenwanderung() {
		int olddistance = 0;
		int newDistance = 1;
		while (true) {
			newDistance = ultraSensor.getDistance();
			ultraSensor.newValue(newDistance);
			olddistance = newDistance;
			rightListener.stateChanged(SensorPort.S1, 0, 0);
		}
	}

}
