package uebung3;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;

public class UltraSonicSensorExtended extends UltrasonicSensor {

	private List<SensorPortListener> portListener;
	private SensorPort port;
	private int newValue;
	private int oldValue;

	public UltraSonicSensorExtended(SensorPort port) {
		super(port);
		this.port = port;
		portListener = new ArrayList<SensorPortListener>();
	}

	public void addSensorPortListener(SensorPortListener listener) {
		if (portListener.size() < 8) {
			port.addSensorPortListener(listener);
			portListener.add(listener);
		}
	}

	public void newValue(int newDistance) {
		this.newValue = newDistance;
		for (SensorPortListener sensorPortListener : portListener) {
			sensorPortListener.stateChanged(port, oldValue, newValue);
		}
		this.oldValue = newValue;
	}

}
