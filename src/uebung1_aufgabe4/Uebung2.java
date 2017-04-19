package uebung1_aufgabe4;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Uebung2 {

	public static void run() {
		driveKrötenwanderung();
	}

	/**
	 * Aufgabe 2
	 */
	private static void driveKrötenwanderung() {
		TouchSensor rigth = new TouchSensor(SensorPort.S1);
		TouchSensor left = new TouchSensor(SensorPort.S2);
		Driver driver = Driver.getInstance();
		while (true) {
			driver.forward();
			if (rigth.isPressed()) {
				driver.rotateLeftDegrees(10);
			}
			if (left.isPressed()) {
				driver.rotateRightDegrees(10);
			}

		}
	}

}
