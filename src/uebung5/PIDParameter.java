package uebung5;

public class PIDParameter {

	private double Tn;
	private double Kp;
	private double Tv;
	private static PIDParameter self;

	public static PIDParameter getInstance() {
		if (self == null) {
			self = new PIDParameter();
		}
		return self;
	}

	public double getTn() {
		return Tn;
	}

	public void setTn(double tn) {
		Tn = tn;
	}

	public double getKp() {
		return Kp;
	}

	public void setKp(double kp) {
		Kp = kp;
	}

	public double getTv() {
		return Tv;
	}

	public void setTv(double tv) {
		Tv = tv;
	}

}
