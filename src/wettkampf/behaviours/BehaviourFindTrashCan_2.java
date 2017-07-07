package wettkampf.behaviours;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;
import wettkampf.listener.LeftTouchSensorListener;
import wettkampf.listener.RightTouchSensorListener;

public class BehaviourFindTrashCan_2 implements Behavior {

	private boolean isDriving;
	private LeftTouchSensorListener left;
	private RightTouchSensorListener right;

	public BehaviourFindTrashCan_2(RightTouchSensorListener right, LeftTouchSensorListener left) {
		this.right = right;
		this.left = left;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return !Model.getInstance().isStart();
	}

	@Override
	public void action() {
		// bis zur mitte fahren
		// 90 grad drehen und merken falls hit
		// zweites mal drehen zur konotrolle
		// zum hit drehen und starten
		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);
		Motor.A.forward();
		Motor.B.forward();
		checkTouchSensorThread();
		isDriving = true;
		// pi * 56
		while (isDriving) {
			// zur mitte fahren ca,
			driveCentimeters(150);
		}
		Motor.A.stop();
		Motor.B.stop();
		isDriving = true;
		while (isDriving) {
			// zur mitte fahren ca,
			for (int i = 0; i < 4; i++) {
				// rotiere 4 mal 90 grad
				rotate90Degrees();
				
			}
		}
		while (isDriving) {
			// zur mitte fahren ca,
			driveCentimeters(150);
			for (int i = 0; i < 4; i++) {
				// rotiere 4 mal 90 grad
				rotate90Degrees();
				Motor.A.stop();
				Motor.B.stop();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// TODO Auto-generated method stub

	}

	private void rotate90Degrees() {
		// TODO Auto-generated method stub
		Motor.A.setSpeed(50);
		Motor.B.setSpeed(50);
		Motor.A.forward();
		Motor.B.backward();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();

	}

	private void driveCentimeters(int cm) {
		// 56 durchmesser
		// umfang 17.6
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Motor.A.resetTachoCount();
				if (Motor.A.getTachoCount() > cm * 17.6) {
					isDriving = false;
				}
			}
		});
		t.start();
	}

	private void checkTouchSensorThread() {
		Thread checkTouchSensor = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					boolean leftPressed = left.getLeft().isPressed();
					boolean rightPressed = right.getRigth().isPressed();
					if (leftPressed || rightPressed) {
						System.out.println("button pressed");
						isDriving = false;
					} else {
						isDriving = true;
					}
				}
			}
		});
		checkTouchSensor.start();
	}

	@Override
	public void suppress() {
		System.out.println("suppress");
		// TODO Auto-generated method stub

	}

}
