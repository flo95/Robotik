package uebung6;

public class Differentiator {
	float lastSignal = 0;
	private float lastTime;

	public Differentiator() {
		differentiate(0, true);
	}

	public float differentiate(float signal, boolean reset) {
		if ((!reset)) {
			float gradient = (signal + lastSignal) / ((System.currentTimeMillis()) - lastTime);
			lastSignal = signal;
			lastTime = System.currentTimeMillis();
			return gradient;
		} else {
			lastTime = System.currentTimeMillis();
			lastSignal = signal;
			return 0;
		}

	}
}
