package uebung6;

import lejos.nxt.Button;

public class Segway {
	public static void main(String[] args) {
		/*
		 * GyroSensor sensor = new GyroSensor(SensorPort.S2); Integrator
		 * integrator = new Integrator(); boolean reset = false; while (true) {
		 * double integrate = integrator.integrate(sensor.readValue(), reset);
		 * String valueOf = String.valueOf(integrate); if (valueOf.length() >
		 * 10) { valueOf = valueOf.substring(0, 10); }
		 * System.out.println(valueOf); if (reset) { reset = false; } if
		 * (Button.ENTER.isDown()) { reset = true; } }
		 */
		Differentiator d = new Differentiator();
		while (!Button.ESCAPE.isDown()) {
			long func = System.currentTimeMillis();
			float derivative = d.differentiate((float) func, Button.ENTER.isDown());
			System.out.println(" Ableitung : " + derivative);
		}
	}
}
