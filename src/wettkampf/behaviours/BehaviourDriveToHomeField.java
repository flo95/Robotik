package wettkampf.behaviours;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;

public class BehaviourDriveToHomeField implements Behavior {

	private static Model model;
	
	@Override
	public boolean takeControl() {
		// Wenn der Ball geworfen wurde
		model = Model.getInstance();
		// TODO Auto-generated method stub
		return model.isDriveToHomeField();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Motor.A.stop();
		Motor.B.stop();
		System.out.println("drive to home field");
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
