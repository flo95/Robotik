package wettkampf.behaviours;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;

public class BehaviourTrashcanIsFoundThrowBall implements Behavior {

	private static Model model;

	@Override
	public boolean takeControl() {
		model = Model.getInstance();
		return model.isTrashcanIsFound();
	}

	@Override
	public void action() {
		System.out.println("throw ball");
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
		model.setTrashcanIsFound(false);
		model.setDriveToHomeField(true);
	}

	@Override
	public void suppress() {
		// Find out why this code never is executed
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("BehaviourTrashcanIsFoundThrowBall false");
		model.setTrashcanIsFound(false);
		model.setDriveToHomeField(true);
	}

}
