package wettkampf.linefollower;

public class Differentiator { 
	
	private double last_signal = 0;

	public double differentiate(double signal , double t, boolean reset) { 
		if((!reset) && (t > 0.0)) { 
			double gradient = (signal - last_signal); 
			last_signal = signal; 
			return gradient; 
		} else { 
			last_signal = signal; 
			return 0; 
		} 
	}

}