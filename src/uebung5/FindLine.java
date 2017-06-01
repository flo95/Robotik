package uebung5;

import lejos.robotics.subsumption.Behavior;
import main.robotik.Driver;

public class FindLine implements Behavior {

	private Driver driver;
	private int counter;
	private int rotateBorder;
	boolean lastWasRight;
	private LightSensorListener lightSensorListener;

	public FindLine(LightSensorListener lightSensorListener) {
		driver = Driver.getInstance();
		counter = 0;
		rotateBorder = 20;
		lastWasRight = true;
		this.lightSensorListener = lightSensorListener;
	}

	@Override
	public boolean takeControl() {
		// System.out.println("takeControl : " +
		// lightSensorListener.getCalculatedValue());
		if (lightSensorListener.getCalculatedValue() >= 0) {
			counter = 0;
		}
		return lightSensorListener.getCalculatedValue() < 0;
	}

	@Override
	public void action() {
		int degree = 3;
		if (lastWasRight) {
			if (counter < rotateBorder) {
				driver.rotateRightDegrees(degree);
			} else {
				lastWasRight = false;
				// System.out.println("aendere lastwasRight = " + lastWasRight);
				driver.rotateLeftDegrees(degree);
				counter = -20;
			}
		} else {
			if (counter < rotateBorder) {
				driver.rotateLeftDegrees(degree);
			} else {
				lastWasRight = true;
				// System.out.println("aendere lastwasRight = " + lastWasRight);
				driver.rotateRightDegrees(degree);
				counter = -20;
			}
		}
		counter++;

	}

	@Override
	public void suppress() {
		driver.stop();
	}

}
