package uebung4.a;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Ziel dieser Übung ist es, einen Roboter zu konstruieren und zu programmieren,
 * der einer schwarzen Linie folgt.
 */
public class Linienfolger {

	public static void main(String[] args) {
		Behavior forward = new DriveForward();
		Behavior findLine = new FindLine();
		Behavior[] behaviorArray = { forward, findLine };
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		arbitrator.start();
		
	}

}
