package uebung6;

public class PidParameter {
	float Kp = 11; // 18
	float Tn = (float) 0.15;
	float Tv = (float) 0.06; // 0.05

	public PidParameter() {
	}

	public PidParameter(float kp, float tn, float tv) {
		Kp = kp;
		Tn = tn;
		Tv = tv;
	}

}
