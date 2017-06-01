package uebung5;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LightSensorListener implements SensorPortListener {

	private int min;
	private int max;
	private boolean firstStart;
	private LightSensor lightSensor;
	private int akutellerWert;

	public LightSensorListener() {
		firstStart = true;
		lightSensor = new LightSensor(SensorPort.S1);
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// eventuell nachher wieder rausnehmen
		aNewValue = lightSensor.getLightValue();
		this.akutellerWert = aNewValue;
		if (firstStart) {
			max = aNewValue;
			min = aNewValue;
			firstStart = false;
		} else {
			if (min > aNewValue) {
				min = aNewValue;
			} else if (max < aNewValue) {
				max = aNewValue;
			}
		}
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getAkutellerWert() {
		return akutellerWert;
	}

}
