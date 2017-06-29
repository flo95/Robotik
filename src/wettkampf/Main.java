package wettkampf;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import uebung3.UltraSonicSensorExtended;

public class Main {
	private static UltraSonicSensorExtended ultraSensor;
	private static boolean throwBall;

	public static void main(String[] args) {
		// throwBall();
		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);
		Motor.A.forward();
		Motor.B.forward();
		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S1);
		throwBall = false;
		while (true) {
			int distance = sensor.getDistance();
			System.out.println(distance);
			if (distance < 20 && !throwBall) {
				Motor.A.stop();
				Motor.B.stop();
				throwBall();
				throwBall = true;
			}
			if (Button.RIGHT.isDown()) {
				Motor.A.forward();
				Motor.B.forward();
				throwBall = false;
			}
		}
	}

	private static void throwBall() {
		Motor.C.setSpeed(90);
		Motor.C.backward();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Motor.C.stop();
		Motor.C.forward();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Motor.C.stop();
	}

}
