package uebung6;

public class PidController {

	private Integrator integrator;
	private Differentiator differentiator;

	public PidController() {
		integrator = new Integrator();
		differentiator = new Differentiator();
	}

	public float calculate(PidParameter p, float setpoint, float signal, boolean reset) {
		float err = setpoint - signal;
		float xa = (float) (p.Kp * (err + (1 / p.Tn) * integrator.integrate(err, reset)
				+ p.Tv * (differentiator.differentiate(err, reset))));
		return xa;
	}

}
