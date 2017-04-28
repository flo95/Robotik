package uebung4;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;
import main.robotik.Driver;

public class FindLine implements Behavior {

	private LightSensor lightSensor;
	private int border;
	private Driver driver;
	private int counter;
	boolean lastWasRight;

	public FindLine() {
		driver = Driver.getInstance();
		lightSensor = new LightSensor(SensorPort.S1);
		border = 50;
		counter = 1;
		lastWasRight = true;
	}

	@Override
	public boolean takeControl() {
		if (lightSensor.getLightValue() < border) {
			counter = 0;
		}
		return (lightSensor.getLightValue() < border) ? false : true;
	}

	@Override
	public void action() {
		System.out.println("light: " + lightSensor.getLightValue());
		System.out.println("counter: " + counter);
		if (lastWasRight) {
			if (counter < 20) {
				lastWasRight = true;
				driver.rotateRightDegrees(1);
			} else {
				lastWasRight = false;
				driver.rotateLeftDegrees(1);
			}
		} else {
			if (counter < 20) {
				driver.rotateLeftDegrees(1);
				lastWasRight = true;
			} else {
				lastWasRight = false;
				driver.rotateRightDegrees(1);
			}
		}
		counter++;
	}

	@Override
	public void suppress() {
		System.out.println("findline end");
		driver.stop();
	}

}
