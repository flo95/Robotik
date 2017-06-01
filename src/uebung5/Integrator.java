package uebung5;

public class Integrator {

	double lastSignal = 0;
	double integral = 0;

	public double integrate(double signal, double t, boolean reset) {
		if (!reset) {
			double newSignal = signal;
			integral += ((newSignal + lastSignal) * t) / 2;
			lastSignal = newSignal;
			return integral;
		} else {
			lastSignal = 0;
			integral = 0;
			return 0;
		}
	}
}
