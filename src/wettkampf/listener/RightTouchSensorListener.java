package wettkampf.listener;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import wettkampf.Model;

public class RightTouchSensorListener implements SensorPortListener {

	private TouchSensor rigth;
	private Model model;

	public RightTouchSensorListener() {
		rigth = new TouchSensor(SensorPort.S2);
		model = Model.getInstance();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (rigth.isPressed()) {
			/*System.out.println("rigth");
			Motor.A.setSpeed(50);
			Motor.B.setSpeed(50);
			System.out.println("rigth");
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
			model.setTrashcanIsFound(true);*/
		}
	}

	public TouchSensor getRigth() {
		return rigth;
	}

	public void setRigth(TouchSensor rigth) {
		this.rigth = rigth;
	}
	
	
}
