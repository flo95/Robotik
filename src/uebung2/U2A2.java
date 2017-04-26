package uebung2;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;
import main.robotik.TouchSensorStatus;

public class U2A2 {

	private int maxCounter;
	private final int degrees = 5;
	private TouchSensor rigth;
	private TouchSensor left;
	private Driver driver;
	private TouchSensorStatus status;
	private int hitCounter;
	private int counter;
	private int noWallDegree = 5;

	public void run() {
		status = TouchSensorStatus.NOTHING;
		hitCounter = 0;
		rigth = new TouchSensor(SensorPort.S1);
		left = new TouchSensor(SensorPort.S2);
		driver = Driver.getInstance();
		maxCounter = 200;
		driveKroetenwanderung();
	}

	/**
	 * Aufgabe 2
	 */
	private void driveKroetenwanderung() {
		while (true) {
			driver.forward();
			hitCounter++;
			System.out.println(hitCounter);
			if (hitCounter == maxCounter && status != TouchSensorStatus.NOTHING) {
				driver.stop();
				if (counter == 1) {
					noWallDegree = 45;
					maxCounter = 300;
				}
				if (counter == 0) {
					noWallDegree = 10;
					maxCounter = 200;
				}
				if (status == TouchSensorStatus.RIGHT) {
					driver.rotateRightDegrees(noWallDegree);
				} else {
					driver.rotateLeftDegrees(noWallDegree);
				}
				hitCounter = 150;
				counter++;
			}
			if (rigth.isPressed() && !left.isPressed()) {
				status = TouchSensorStatus.RIGHT;
				driver.stop();
				driver.rotateLeftDegrees(degrees);
				hitCounter = 0;
				counter = 0;
			}
			if (left.isPressed() && !rigth.isPressed()) {
				status = TouchSensorStatus.LEFT;
				driver.stop();
				driver.rotateRightDegrees(degrees);
				hitCounter = 0;
				counter = 0;
			}
			if (left.isPressed() && rigth.isPressed()) {
				driver.stop();
				if (status == TouchSensorStatus.RIGHT) {
					driver.rotateLeftDegrees(noWallDegree);
				} else {
					driver.rotateRightDegrees(noWallDegree);
				}
				hitCounter = 0;
				counter = 0;
			}
		}
	}

}
