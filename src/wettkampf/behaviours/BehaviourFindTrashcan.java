package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;
import wettkampf.listener.DistanceListener;
import wettkampf.listener.LeftTouchSensorListener;
import wettkampf.listener.RightTouchSensorListener;

public class BehaviourFindTrashcan implements Behavior {
	private static Model model;
	private boolean throwBall;
	private DistanceListener distanceListener;
	private RightTouchSensorListener rightTouchSensorListener;
	private LeftTouchSensorListener leftTouchSensorListener;

	public BehaviourFindTrashcan(DistanceListener distanceListener, RightTouchSensorListener rightTouchSensorListener,
			LeftTouchSensorListener leftTouchSensorListener) {
		this.distanceListener = distanceListener;
		this.rightTouchSensorListener = rightTouchSensorListener;
		this.leftTouchSensorListener = leftTouchSensorListener;
	}

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
		// TODO find trashcan
		while (!model.isTrashcanIsFound()) {
			Motor.A.forward();
			Motor.B.forward();
			int range = distanceListener.getRange();
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
				model.setTrashcanIsFound(true);
				// throwBall();
				// throwBall = true;
			}
			if (Button.RIGHT.isDown()) {
				Motor.A.setSpeed(300);
				Motor.B.setSpeed(300);
				Motor.A.forward();
				Motor.B.forward();
				throwBall = false;
			}
			if (Button.ESCAPE.isDown()) {
				model.setTrashcanIsFound(true);
			}
		}
	}

	@Override
	public void suppress() {
		Motor.A.stop();
		Motor.B.stop();
	}

}
