package wettkampf.linefollower;

public class PID_Parameter {
	
	private double Tn;
	private double Tv;
	private double Kp;
	
	public PID_Parameter(double Tn, double Tv, double Kp) {
		this.Tn = Tn;
		this.Tv = Tv;
		this.Kp = Kp;
	}
	
	public void setTn(double tn) {
		Tn = tn;
	}
	
	public double getTn() {
		return Tn;
	}
	
	public void setTv(double tv) {
		Tv = tv;
	}
	
	public double getTv() {
		return Tv;
	}

	public void setKp(double kp) {
		Kp = kp;
	}

	public double getKp() {
		return Kp;
	}

}