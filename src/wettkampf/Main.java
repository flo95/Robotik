package wettkampf;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import main.robotik.Driver;
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
		RangeFeatureDetector featureDetector = new RangeFeatureDetector(sensor, 255, 1);
		featureDetector.addListener(new FeatureListener() {

			@Override
			public void featureDetected(Feature feature, FeatureDetector detector) {
				int range = (int) feature.getRangeReading().getRange();
				System.out.println(range);
				if (range < 30 && !throwBall) {
					Motor.A.setSpeed(150);
					Motor.B.setSpeed(150);
				}
				if (range < 20 && !throwBall) {
					Motor.A.stop();
					Motor.B.stop();
					// TODO check Eimer oder Gegner?
					// TODO ausrichten
					// TODO werfen
					throwBall();
					throwBall = true;
				}
				if (Button.RIGHT.isDown()) {
					Motor.A.setSpeed(300);
					Motor.B.setSpeed(300);
					Motor.A.forward();
					Motor.B.forward();
					throwBall = false;
				}

				// TODO Auto-generated method stub
			}
		});
		throwBall = false;
		while (true) {
			// TODO Eimer suchen
			// int distance = sensor.getDistance();
			// System.out.println(distance);
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
