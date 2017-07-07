package wettkampf;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import wettkampf.behaviours.BehaviourDriveToHomeField;
import wettkampf.behaviours.BehaviourFindTrashCan_2;
import wettkampf.behaviours.BehaviourFindTrashcan;
import wettkampf.behaviours.BehaviourNewRound;
import wettkampf.behaviours.BehaviourStart;
import wettkampf.behaviours.BehaviourTrashcanIsFoundThrowBall;
import wettkampf.listener.DistanceListener;
import wettkampf.listener.LeftTouchSensorListener;
import wettkampf.listener.LightSensorListener;
import wettkampf.listener.RightTouchSensorListener;

public class Main {
	private static Model model;

	public static void main(String[] args) {
		// throwBall();
		model = Model.getInstance();
		model.setStart(true);
		model.setTrashcanIsFound(false);
		LightSensorListener lightSensorListener = new LightSensorListener();
		SensorPort.S4.addSensorPortListener(lightSensorListener);
		RightTouchSensorListener rightTouchSensorListener = new RightTouchSensorListener();
		SensorPort.S2.addSensorPortListener(rightTouchSensorListener);
		LeftTouchSensorListener leftTouchSensorListener = new LeftTouchSensorListener();
		SensorPort.S3.addSensorPortListener(leftTouchSensorListener);
		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S1);
		RangeFeatureDetector featureDetector = new RangeFeatureDetector(sensor, 255, 1);
		DistanceListener distanceListener = new DistanceListener();
		featureDetector.addListener(distanceListener);

		Behavior behaviorStart = new BehaviourStart(lightSensorListener);
		Behavior behaviorNewRound = new BehaviourNewRound();
		Behavior behaviorTrashcan = new BehaviourFindTrashCan_2(rightTouchSensorListener,
				leftTouchSensorListener);
		Behavior behaviorDriveToHomeField = new BehaviourDriveToHomeField(lightSensorListener);
		Behavior behaviorTrashcanIsFoundThrowBall = new BehaviourTrashcanIsFoundThrowBall();
		Behavior[] behaviorArray = { behaviorStart, behaviorNewRound, behaviorTrashcan,
				behaviorTrashcanIsFoundThrowBall, behaviorDriveToHomeField };
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		arbitrator.start();
	}

}
