package wettkampf.behaviours;

import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;

public class BehaviourNewRound implements Behavior {

	private Model model;
	
	@Override
	public boolean takeControl() {
		// Start verhalten des Roboter
		return false;
	}

	@Override
	public void action() {
		// Lesen Farben ein und warte auf den letzten Click
		// TODO Auto-generated method stub
		// Heimatfeld
		// Gegner
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
