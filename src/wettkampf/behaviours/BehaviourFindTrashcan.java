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
	private boolean s;
	private int min;
	private UltrasonicSensor ultrasonicSensor;

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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
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
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Motor.A.stop();
			//Motor.B.stop();
			min = 255;
			// rotate 45 degrees right
			Motor.A.setSpeed(50);
			Motor.B.setSpeed(50);
			Motor.A.backward();
			Motor.B.forward();
			s = true;
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					s = false;
				}
			});
			
			t1.start();
			while(s){
				
			}
			scanArea();
			Motor.A.setSpeed(300);
			Motor.B.setSpeed(300);
			
			int range = distanceListener.getRange();
			int distance = ultrasonicSensor.getDistance();
			if (distance < 30 && !throwBall) {
				Motor.A.setSpeed(150);
				Motor.B.setSpeed(150);
			}
			// TODO warten bis tastsensor gedrückt wird
			if (distance < model.getDistance() && !throwBall) {
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
		ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		ultrasonicSensor.getDistance();
		min = 255;
		
		findMin(ultrasonicSensor);

		if (min <= 150) {			
			scanning = true;
			findMinSecondTime(ultrasonicSensor);
		} else {
			findMin(ultrasonicSensor);
			findMinSecondTime(ultrasonicSensor);
		}
	}

	public void findMin(UltrasonicSensor ultrasonicSensor){
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.forward();
		Motor.B.backward();
		scanning = true;
		s = true;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scanning = false;
			}
		});
		t.start();
		min = 255;
		while (s) {
			int akt = ultrasonicSensor.getDistance();
			if (!scanning) {
				Motor.A.stop();
				Motor.B.stop();
				System.out.println("m: " + min + " a: " + akt);
				break;
			} else {
				if (akt < min) {
					min = akt;
				}
				System.out.println(akt);
			}
		}
	}
	
	public void findMinSecondTime(UltrasonicSensor ultrasonicSensor){
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.backward();
		Motor.B.forward();
		s = true;
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s = false;
			}
		});
		
		t1.start();
		
		while (s) {
			int actualPosition = ultrasonicSensor.getDistance();
			if (actualPosition == min + 2 || actualPosition == min + 1 || actualPosition == min - 1
					|| actualPosition == min - 2) {
				System.out.println("gefunden");
				Motor.A.stop();
				Motor.B.stop();
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