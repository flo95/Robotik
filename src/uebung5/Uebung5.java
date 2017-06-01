package uebung5;

import lejos.nxt.SensorPort;
import main.robotik.Driver;

public class Uebung5 {

	public static void main(String[] args) {
		LightSensorListener lightSensorListener = new LightSensorListener();
		SensorPort.S1.addSensorPortListener(lightSensorListener);
		Driver driver = Driver.getInstance();
		driver.rotateRightDegrees(360);
		System.out.println("min = " + lightSensorListener.getMin());
		System.out.println("max = " + lightSensorListener.getMax());
		int mittelWert = (lightSensorListener.getMax() + lightSensorListener.getMin()) / 2;
		lightSensorListener.endFindMittelwert(mittelWert);
		System.out.println("mittelwert = " + mittelWert);
		// find start position
		while (lightSensorListener.getAkutellerWert() <= mittelWert + 2
				&& lightSensorListener.getAkutellerWert() >= mittelWert - 2) {
			driver.rotateRightDegrees(1);
		}
		while (true) {
			driver.forward();
		}
		// Behavior forward = new DriveForward();
		// Behavior findLine = new FindLine();
		// Behavior[] behaviorArray = { forward, findLine };
		// Arbitrator arbitrator = new Arbitrator(behaviorArray);
		// arbitrator.start();

	}

}
