package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import uebung3.UltraSensorListener;
import uebung3.UltraSonicSensorExtended;
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

	private boolean scanning;
	private int min;

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
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					Thread.sleep(1000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanArea();
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

	private void scanArea() {
		// Thread sleep für eine Minute
		// um nicht sofot den Abstand zu scannen
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.forward();
		Motor.B.backward();
		scanning = true;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scanning = false;
			}
		});
		t.start();
		boolean s = true;
		min = 255;
		UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		while (s) {
			int akt = ultrasonicSensor.getDistance();
			System.out.println(" : " + akt);
			if (!scanning) {
				Motor.A.stop();
				Motor.B.stop();
				System.out.println("m: " + min + " a: " + akt);
			} else {
				if (distanceListener.getRange() < min) {
					min = distanceListener.getRange();
				}
				System.out.println(distanceListener.getRange());
			}
		}
		Motor.A.backward();
		Motor.B.forward();
		t.start();
		while(s) {
			int actualPosition = distanceListener.getRange();
			if (actualPosition == min){
				break;
			}
		}
	}

	@Override
	public void suppress() {
		Motor.A.stop();
		Motor.B.stop();
	}

}
//