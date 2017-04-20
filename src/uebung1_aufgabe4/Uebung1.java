package uebung1_aufgabe4;

public class Uebung1 {
	public void run() {
		driveHausVomNikolaus();
	}

	/**
	 * Aufgabe 6
	 */
	private void driveHausVomNikolaus() {
		Driver driver = Driver.getInstance();
		driver.driveCentimeters(50);
		driver.rotateRightDegrees(135);
		driver.driveCentimeters((Math.sqrt(Math.pow(50, 2) * 2)));
		driver.rotateLeftDegrees(135);
		driver.driveCentimeters(50);
		driver.rotateLeftDegrees(45);
		driver.driveCentimeters((Math.sqrt(Math.pow(25, 2) * 2)));
		driver.rotateLeftDegrees(90);
		driver.driveCentimeters((Math.sqrt(Math.pow(25, 2) * 2)));
		driver.rotateLeftDegrees(135);
		driver.driveCentimeters(50);
		driver.rotateRightDegrees(135);
		driver.driveCentimeters((Math.sqrt(Math.pow(50, 2) * 2)));
		driver.rotateLeftDegrees(135);
		driver.driveCentimeters(50);
		driver.rotateLeftDegrees(180);
		driver.driveCentimeters(50);
	}

}
