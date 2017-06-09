package uebung6;

public class PidParameter {
	float Kp = 11;
	float Tn = (float) 0.15;
	float Tv = (float) 0.06;

	public PidParameter() {
	}

	public PidParameter(float kp, float tn, float tv) {
		Kp = kp;
		Tn = tn;
		Tv = tv;
	}

}
