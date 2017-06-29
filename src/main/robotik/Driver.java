package main.robotik;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Driver {

	private static Driver self;
	private DifferentialPilot pilot;

	private Driver() {
		pilot = new DifferentialPilot(56, 120, Motor.A, Motor.B);
		pilot.setTravelSpeed(10);
		pilot.setRotateSpeed(1);
	}

	public static Driver getInstance() {
		if (self == null) {
			self = new Driver();
		}
		return self;
	}

	public void driveCentimeters(double cm) {
		pilot.travel(cm * 10);
	}

	public void rotateRightDegrees(double degree) {
		pilot.rotate(degree * 0.88);
	}

	public void rotateLeftDegrees(double degree) {
		pilot.rotate((-0.879) * degree);
	}

	public void forward() {
		pilot.forward();
	}

	public void backward() {
		pilot.backward();
	}

	public void stop() {
		pilot.stop();
	}

	public void driveLeftCurve() {
		pilot.steer(-25);
	}

	public void driveRightCurve() {
		pilot.steer(25);
	}
}
