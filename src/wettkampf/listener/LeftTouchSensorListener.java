package wettkampf.listener;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import main.robotik.Driver;

public class LeftTouchSensorListener implements SensorPortListener {
	private TouchSensor left;

	public LeftTouchSensorListener() {
		left = new TouchSensor(SensorPort.S3);
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (left.isPressed()) {
			System.out.println("left");
		}
	}

}
