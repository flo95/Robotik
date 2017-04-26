package uebung3.aufgabe1;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;

public class U3A1 {

	private TouchSensor rigth;
	private TouchSensor left;
	private Driver driver;

	public void run() {
		rigth = new TouchSensor(SensorPort.S1);
		left = new TouchSensor(SensorPort.S2);
		driver = Driver.getInstance();
		driver.forward();
		SensorPortListener rightListener = new RightTouchSensorListener(rigth.isPressed());
		SensorPortListener leftListener = new LeftTouchSensorListener();

		SensorPort.S1.addSensorPortListener(rightListener);
		SensorPort.S2.addSensorPortListener(leftListener);
		driveKroetenwanderung();
	}

	/**
	 * Aufgabe 2
	 */
	private void driveKroetenwanderung() {
		while (true) {

		}
	}

}
