package uebung5;

public class PIDController {
	
	Integrator integrator = null;
	Differentiator differentiator = null;

	PIDController() {
		integrator = new Integrator();
		differentiator = new Differentiator();
	}

	public double calculate(PIDParameter p, double setpoint, double current, double t, boolean reset) {
		double err = setpoint - current;
		double integral = integrator.integrate(err, t, reset);
		double gradient = differentiator.differentiate(err, t, reset);
		double result = err;
		if (p.getTn() > 0.0) {
			// Verhindere Division durch 0 ...
			result += integral / p.getTn();
		}
		result += gradient * p.getTv();
		return result * p.getKp();
	}

}
