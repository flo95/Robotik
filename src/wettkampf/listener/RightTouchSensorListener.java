package wettkampf.listener;

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
			System.out.println("rigth");
			model.setTrashcanIsFound(true);
		}
	}
}
