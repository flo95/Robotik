package uebung5;

import lejos.robotics.subsumption.Behavior;
import main.robotik.Driver;

public class DriveForward implements Behavior {

	private Driver driver;

	public DriveForward() {
		driver = Driver.getInstance();
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		// System.out.println("DriveForward start");
		driver.forward();
	}

	@Override
	public void suppress() {
		// System.out.println("DriveForward end");
		driver.stop();
	}

}
