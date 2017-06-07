package uebung6;

import lejos.nxt.ADSensorPort;
import lejos.nxt.SensorConstants;

public class GyroSensor implements SensorConstants {
	ADSensorPort sp;

	GyroSensor(ADSensorPort s) {
		this.sp = s;
		sp.setTypeAndMode(TYPE_LIGHT_INACTIVE, MODE_RAW);
	}

	public int readValue() {
		return sp.readRawValue();
	}

}
