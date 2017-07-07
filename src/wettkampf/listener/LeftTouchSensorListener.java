package wettkampf.listener;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import wettkampf.Model;

public class LeftTouchSensorListener implements SensorPortListener {
	private TouchSensor left;
	private Model model;

	public LeftTouchSensorListener() {
		left = new TouchSensor(SensorPort.S3);
		model = Model.getInstance();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (left.isPressed()) {
			/*Motor.A.setSpeed(50);
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
			model.setTrashcanIsFound(true);*/
		}
	}

	public TouchSensor getLeft() {
		return left;
	}

	public void setLeft(TouchSensor left) {
		this.left = left;
	}
	
	

}
