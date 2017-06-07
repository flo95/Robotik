package uebung6;

public class Integrator {

	double lastSignal = 0;
	double integral = 0;
	double lastTime;

	public Integrator() {
		integrate(0, true);
	}

	public double integrate(double signal, boolean reset) {
		if (!reset) {
			double newSignal = signal;
			integral += ((newSignal + lastSignal) * ((System.currentTimeMillis() / 1000) - lastTime)) / 2;
			lastSignal = newSignal;
			lastTime = System.currentTimeMillis();
			return integral;
		} else {
			lastSignal = 0;
			integral = 0;
			lastTime = System.currentTimeMillis() / 1000;
			return 0;
		}
	}
}
