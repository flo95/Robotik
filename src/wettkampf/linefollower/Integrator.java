package wettkampf.linefollower;

public class Integrator { 

	private double last_signal 	= 0; 
	private double integral 	= 0;

	public double integrate(double signal , double t, boolean reset) { 
		if(!reset) { 
			double new_signal = signal; 
			integral += ((new_signal + last_signal));
			last_signal = new_signal; 
			return integral; 
		} else { 
			last_signal = 0; 
			integral = 0; 
			return 0; 
		} 
	}

}