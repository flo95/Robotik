package uebung6;

public class Differentiator {
	float lastSignal = 0;
	private float lastTime;

	public Differentiator() {
		differentiate(0, true);
	}

	public float differentiate(float signal, boolean reset) {
		if ((!reset)) {
			float timeMillis = (float) System.currentTimeMillis();
			float gradient = (signal - lastSignal) / ((timeMillis / 1000) - lastTime);
			lastSignal = signal;
			lastTime = System.currentTimeMillis() / 1000;
			return gradient;
		} else {
			lastTime = System.currentTimeMillis() / 1000;
			lastSignal = signal;
			return 0;
		}
	}
}
