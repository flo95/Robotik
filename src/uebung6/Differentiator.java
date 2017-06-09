package uebung6;

public class Differentiator {
	float lastSignal = 0;
	private float lastTime;

	public Differentiator() {
		differentiate(0, true);
	}

	public float differentiate(float signal, boolean reset) {
		float timeMillis = (float) System.currentTimeMillis() / 1000;
		if ((!reset)) {
			float gradient = (signal - lastSignal) / ((timeMillis) - lastTime);
			lastSignal = signal;
			lastTime = timeMillis;
			return gradient;
		} else {
			lastTime = timeMillis;
			lastSignal = signal;
			return 0;
		}
	}
}
