package wettkampf;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import wettkampf.behaviours.BehaviourDriveToHomeField;
import wettkampf.behaviours.BehaviourFindTrashcan;
import wettkampf.behaviours.BehaviourNewRound;
import wettkampf.behaviours.BehaviourStart;
import wettkampf.listener.LightSensorListener;

public class Main {
	private static Model model;

	public static void main(String[] args) {
		// throwBall();
		model = Model.getInstance();
		model.setStart(true);
		LightSensorListener lightSensorListener = new LightSensorListener();
		SensorPort.S4.addSensorPortListener(lightSensorListener);
		Behavior behaviorStart = new BehaviourStart(lightSensorListener);
		Behavior behaviorNewRound = new BehaviourNewRound();
		Behavior behaviorTrashcan = new BehaviourFindTrashcan();
		Behavior behaviorDriveToHomeField = new BehaviourDriveToHomeField();
		Behavior[] behaviorArray = { behaviorStart, behaviorNewRound, behaviorTrashcan, behaviorDriveToHomeField };
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		arbitrator.start();
	}

}
