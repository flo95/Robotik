package uebung1_aufgabe4;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {

	public static void main(String[] args) throws InterruptedException {
//		Motor.A.setSpeed(720);
//		Motor.B.setSpeed(720);
//		Motor.A.resetTachoCount();
//		Motor.B.resetTachoCount();
//		int tachoCountA = Motor.A.getTachoCount();
//		int tachoCountB = Motor.B.getTachoCount();
//		Motor.A.forward();
//		Motor.B.forward();
//		while (tachoCountA < 10000 || tachoCountB < 10000) {
//			System.out.println(tachoCountA);
//			tachoCountA = Motor.A.getTachoCount();
//			tachoCountB = Motor.B.getTachoCount();
//		}
//		Motor.A.stop();
//		Motor.B.stop();
	
		DifferentialPilot pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B);
		pilot.setRotateSpeed(30);
		pilot.travel(25);
		pilot.rotate(128);
		pilot.travel(35);
		pilot.rotate(-123);
		pilot.travel(25);
		pilot.rotate(-38);
		pilot.travel(17);
		pilot.rotate(-83);
		pilot.travel(17);
		pilot.rotate(-123);
		pilot.travel(25);
		pilot.rotate(128);
		pilot.travel(35);
		pilot.rotate(-123);
		pilot.travel(25);
		pilot.rotate(180);
		pilot.travel(25);
		
		pilot.stop();
	}

}
