package uebung6;

import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;

public class Segway {
	public static void main(String[] args) {

		GyroSensor sensor = new GyroSensor(SensorPort.S2);
		PidController controller = new PidController();

		PidParameter parameter = new PidParameter(18, (float) 0.15, (float) 0.05);
		NXTMotor rechtsMotor = new NXTMotor(MotorPort.B);
		NXTMotor linksMotor = new NXTMotor(MotorPort.C);
		int setpoint = 600;
		while (!Button.ESCAPE.isDown()) {
			int result = 0;
			if (Button.ENTER.isDown()) {
				result = (int) controller.calculate(parameter, setpoint, sensor.readValue(), true);
			} else {
				result = (int) controller.calculate(parameter, setpoint, sensor.readValue(), false);
			}
			if (result > 127) {
				result = 126;
			} else if (result < -127) {
				result = -126;
			}

			rechtsMotor.setPower(result);
			linksMotor.setPower(result);
			rechtsMotor.forward();
			linksMotor.forward();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// Integrator integrator = new Integrator();
		// boolean reset = false;
		// while (true) {
		// double integrate = integrator.integrate(sensor.readValue(), reset);
		// String valueOf = String.valueOf(integrate);
		// if (valueOf.length() > 10) {
		// valueOf = valueOf.substring(0, 10);
		// }
		// System.out.println(valueOf);
		// if (reset) {
		// reset = false;
		// }
		// if (Button.ENTER.isDown()) {
		// reset = true;
		// }
		// }
		// Differentiator d = new Differentiator();
		// while (!Button.ESCAPE.isDown()) {
		// long func = System.currentTimeMillis();
		// float derivative = d.differentiate(((float) func) / 1000,
		// Button.ENTER.isDown());
		// System.out.println("Ab : " + derivative);
		// try {
		// Thread.sleep(25);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}
}
