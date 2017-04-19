package uebung1_aufgabe4;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Uebung2 {

	private static int maxCounter;
	private static final int degrees = 5;
	private static TouchSensor rigth;
	private static TouchSensor left;
	private static Driver driver;
	private static TouchSensorStatus status;
	private static int hitCounter;
	private static int counter;
	private static int noWallDegree = 5;

	public static void run() {
		status = TouchSensorStatus.NOTHING;
		hitCounter = 0;
		rigth = new TouchSensor(SensorPort.S1);
		left = new TouchSensor(SensorPort.S2);
		driver = Driver.getInstance();
		maxCounter = 200;
		driveKrötenwanderung();
	}

	/**
	 * Aufgabe 2
	 */
	private static void driveKrötenwanderung() {
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
