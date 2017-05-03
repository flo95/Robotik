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
	private int rotateBorder;
	boolean lastWasRight;

	public FindLine() {
		driver = Driver.getInstance();
		lightSensor = new LightSensor(SensorPort.S1);
		border = 50;
		counter = 0;
		rotateBorder = 20;
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
		System.out.println("last was right " + lastWasRight);
		if (lastWasRight) {
			if (counter < rotateBorder) {
				driver.rotateRightDegrees(1);
			} else {
				lastWasRight = false;
				System.out.println("aendere lastwasRight = " + lastWasRight);
				driver.rotateLeftDegrees(1);
				counter = -20;
			}
		} else {
			if (counter < rotateBorder) {
				driver.rotateLeftDegrees(1);
			} else {
				lastWasRight = true;
				System.out.println("aendere lastwasRight = " + lastWasRight);
				driver.rotateRightDegrees(1);
				counter = -20;
			}
		}
		counter++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void suppress() {
		System.out.println("findline end");
		driver.stop();
	}

}
