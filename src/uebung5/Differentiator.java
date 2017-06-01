package uebung5;

public class Differentiator {
	double lastSignal = 0;

	public double differentiate(double signal, double t, boolean reset) {
		if ((!reset) && (t > 0.0)) {
			double gradient = (signal - lastSignal) / t;
			lastSignal = signal;
			return gradient;
		} else {
			lastSignal = signal;
			return 0;
		}

	}
}
