package wettkampf.listener;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;

public class RightTouchSensorListener implements SensorPortListener {

	private TouchSensor rigth;

	public RightTouchSensorListener() {
		rigth = new TouchSensor(SensorPort.S2);
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (rigth.isPressed()) {
			System.out.println("rigth");
		}
	}
}
