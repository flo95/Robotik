package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;

public class BehaviourFindTrashcan implements Behavior {
	private static Model model;
	private boolean throwBall;

	@Override
	public boolean takeControl() {
		model = Model.getInstance();
		// Sobald der Roboter loslegen kann
		// TODO Auto-generated method stub
		return !model.isStart();
	}

	@Override
	public void action() {
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
				if (range < model.getDistance() && !throwBall) {
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
				if (Button.ESCAPE.isDown()) {
					throwBall();
				}
			}
		});
		throwBall = false;
		while (true) {
			
		}
	}

	private void throwBall() {
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

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
