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

	private int x;
	private int xi;
	private int w;
	private int e;
	private boolean endFindMittelwert;
	private PIDController controller;
	private double lastCalculate = 0;;

	public LightSensorListener() {
		firstStart = true;
		lightSensor = new LightSensor(SensorPort.S1);
		controller = new PIDController();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// eventuell nachher wieder rausnehmen
		aNewValue = lightSensor.getLightValue();
		akutellerWert = aNewValue;
		if (!endFindMittelwert) {
			findMittelwert(aNewValue);
		} else {
			x = aNewValue;
			e = w - x;
			PIDParameter parameter = new PIDParameter();
			parameter.setKp(1);
			parameter.setTn(0);
			parameter.setTv(0);
			double calculate = controller.calculate(parameter, w, x, System.currentTimeMillis() - lastCalculate, false);
			lastCalculate = System.currentTimeMillis();
			System.out.println("calculated : " + calculate);
		}
	}

	private void findMittelwert(int aNewValue) {
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

	public void endFindMittelwert(int mittelWert) {
		this.endFindMittelwert = true;
		this.w = mittelWert;
	}

}
