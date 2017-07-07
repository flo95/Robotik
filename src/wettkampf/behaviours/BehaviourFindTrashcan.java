package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;
import wettkampf.listener.DistanceListener;
import wettkampf.listener.LeftTouchSensorListener;
import wettkampf.listener.RightTouchSensorListener;

public class BehaviourFindTrashcan implements Behavior {
	private static Model model;
	private boolean throwBall;
	private RightTouchSensorListener rightTouchSensorListener;
	private LeftTouchSensorListener leftTouchSensorListener;

	private boolean scanning;
	private boolean s;
	private int min;
	private UltrasonicSensor ultrasonicSensor;
	private boolean stop;

	public BehaviourFindTrashcan(DistanceListener distanceListener, RightTouchSensorListener rightTouchSensorListener,
			LeftTouchSensorListener leftTouchSensorListener) {
		this.rightTouchSensorListener = rightTouchSensorListener;
		this.leftTouchSensorListener = leftTouchSensorListener;
	}

	@Override
	public boolean takeControl() {
		model = Model.getInstance();
		// Sobald der Roboter loslegen kann
		// TODO Auto-generated method stub
		// return !model.isStart();
		return false;
	}

	@Override
	public void action() {
		ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		ultrasonicSensor.continuous();
		// Thread schläft um nicht sofort die Distanz zu messen
		threadSleepForMilliSeconds(2000);

		scanArea();

		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);

		// TODO find trashcan
		while (!model.isTrashcanIsFound()) {
			Motor.A.forward();
			Motor.B.forward();

			threadSleepForMilliSeconds(2000);

			Motor.A.stop();
			Motor.B.stop();

			min = 255;

			// rotate45DegreesRight();
			// rotate 45 degrees right
			Motor.A.setSpeed(50);
			Motor.B.setSpeed(50);
			Motor.A.backward();
			Motor.B.forward();
			threadSleepForMilliSeconds(2500);
			// s = true;
			Motor.A.stop();
			Motor.B.stop();

			scanArea();

			Motor.A.setSpeed(300);
			Motor.B.setSpeed(300);

			int distance = ultrasonicSensor.getDistance();
			// if (distance < 30 && !throwBall) {
			// Motor.A.setSpeed(150);
			// Motor.B.setSpeed(150);
			// }
			// TODO warten bis tastsensor gedr�ckt wird
			boolean rightPressed = rightTouchSensorListener.getRigth().isPressed();
			boolean leftPressed = leftTouchSensorListener.getLeft().isPressed();
			if (((rightPressed || leftPressed) || (rightPressed && leftPressed)) && !throwBall) {
				Motor.A.stop();
				Motor.B.stop();
				if (rightPressed && leftPressed) {

				} else if (rightPressed) {
					// rightIsPressed();
				} else if (leftPressed) {
					// leftIsPressed();
				}
				// TODO check Eimer oder Gegner?
				// TODO ausrichten
				// TODO werfen
				s = false;
				scanning = false;
				model.setTrashcanIsFound(true);
				// throwBall();
				throwBall = true;
				break;
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

	private void leftIsPressed() {
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		System.out.println("left");
		Motor.A.backward();
		Motor.B.backward();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Motor.A.forward();
		Motor.B.backward();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rightIsPressed() {
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		System.out.println("left");
		Motor.A.backward();
		Motor.B.backward();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Motor.A.backward();
		Motor.B.forward();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void threadSleepForMilliSeconds(int ms) {
		stop = false;
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stop = true;
			}
		});
		thread.start();
		while (!stop) {
			boolean rightPressed = rightTouchSensorListener.getRigth().isPressed();
			boolean leftPressed = leftTouchSensorListener.getLeft().isPressed();
			if (rightPressed || leftPressed) {
				Motor.A.stop();
				Motor.B.stop();
				scanning = false;
				s = false;
				model.setTrashcanIsFound(true);
			}
		}

	}

	private void scanArea() {
		ultrasonicSensor.getDistance();
		min = 255;

		findMinRotateLeft();

		if (min <= 150) {
			scanning = true;
			findMinSecondTimeRotateRight();
		} else {
			findMinRotateLeft();
			findMinSecondTimeRotateRight();
		}
	}

	public void findMinRotateLeft() {
		scanning = true;
		s = true;
		min = 255;
		rotate45DegreesLeft();
		int akt = min;
		while (scanning) {
			boolean rightPressed = rightTouchSensorListener.getRigth().isPressed();
			boolean leftPressed = leftTouchSensorListener.getLeft().isPressed();
			if (rightPressed || leftPressed) {
				scanning = false;
				s = false;

				model.setTrashcanIsFound(true);
				break;
			}
			akt = ultrasonicSensor.getDistance();
			if (akt < min) {
				System.out.println("new min:" + akt);
				min = akt;
			}
			System.out.println(akt);
		}
		Motor.A.stop();
		Motor.B.stop();
		System.out.println("m: " + min + " a: " + akt);
		// DEBUG
		threadSleepForMilliSeconds(1000);
	}

	private void rotate45DegreesLeft() {
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.forward();
		Motor.B.backward();
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

	}

	public void findMinSecondTimeRotateRight() {
		s = true;
		rotate45DegreesRight();
		while (s) {
			boolean rightPressed = rightTouchSensorListener.getRigth().isPressed();
			boolean leftPressed = leftTouchSensorListener.getLeft().isPressed();
			if (rightPressed || leftPressed) {
				scanning = false;
				s = false;
				model.setTrashcanIsFound(true);
				break;
			}
			int actualPosition = ultrasonicSensor.getDistance();
			if (actualPosition <= min + 2 && actualPosition > min - 2 && actualPosition != 255) {
				System.out.println("gefunden bei " + actualPosition);
				// DEBUG
				actualPosition = ultrasonicSensor.getDistance();
				if (actualPosition > 150) {
					// rotate45DegreesLeft();
				} else {
					Motor.A.stop();
					Motor.B.stop();
					threadSleepForMilliSeconds(1000);
					break;
				}
			}
		}
	}

	private void rotate45DegreesRight() {
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.backward();
		Motor.B.forward();
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
	}

	@Override
	public void suppress() {
		Motor.A.stop();
		Motor.B.stop();
	}

}
//