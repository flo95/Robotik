package wettkampf.linefollower;

public class PID_Controller {

	private Integrator integrator = null; 
	private Differentiator differentiator = null;

	PID_Controller() { 
		integrator = new Integrator(); 
		differentiator = new Differentiator(); 
	}
	
	public double calculate(PID_Parameter p, double setpoint, double current, double t, boolean reset) {

		double err = setpoint - current; 
		double integral = integrator.integrate(err, t, reset); 
		double gradient = differentiator.differentiate(err, t, reset);

		double result = err; 
		if (p.getTn() > 0.0) {
			result += integral / p.getTn(); 
		} 
		result += gradient * p.getTv(); 
		return result * p.getKp();
	}

}