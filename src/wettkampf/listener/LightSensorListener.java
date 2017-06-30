package wettkampf.listener;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LightSensorListener implements SensorPortListener {

	private int lastValue;
	private LightSensor lightSensor;

	public LightSensorListener() {
		lightSensor = new LightSensor(SensorPort.S4);
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// TODO Auto-generated method stub
		int light = lightSensor.getLightValue();
		lastValue = light;
	}

	public int getLastValue() {
		return lastValue;
	}

	public void setLastValue(int lastValue) {
		this.lastValue = lastValue;
	}

}
