package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;
import wettkampf.Model;
import wettkampf.linefollower.PID_Controller;
import wettkampf.linefollower.PID_Parameter;
import wettkampf.listener.LightSensorListener;

public class BehaviourDriveToHomeField implements Behavior {

	private static Model model;
	private LightSensor lightSensor = new LightSensor(SensorPort.S4);

	public BehaviourDriveToHomeField(LightSensorListener lightSensorListener) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean takeControl() {
		// Wenn der Ball geworfen wurde
		model = Model.getInstance();
		// TODO Auto-generated method stub
		return model.isDriveToHomeField();
	}

	@Override
	public void action() {
		System.out.println("drive to home fieldg");
		// TODO Auto-generated method stub
		double color = 0;
		int[] contrast = calculateContrast();
		double threshold = (contrast[0] + contrast[1]) / 2;
		position(threshold);

		PID_Controller pc = new PID_Controller();
		PID_Parameter pp = new PID_Parameter(100000, 75, 20);

		while (!Button.ESCAPE.isPressed()) {

			color = lightSensor.getLightValue();
			double result = pc.calculate(pp, color, threshold, 1, true);

			Motor.A.setSpeed(new Double(275 - result).intValue());
			Motor.A.forward();
			Motor.B.setSpeed(new Double(275 + result).intValue());
			Motor.B.forward();
			if (lightSensor.getLightValue() == Model.getInstance().getHomeFieldColor()) {
				Motor.A.stop();
				Motor.B.stop();
				break;
			}
		}
	}

	public int[] calculateContrast() {
		int lineColor = Model.getInstance().getLineColor();
		int fieldColor = Model.getInstance().getFieldColor();
		int radius = 0;
		Motor.A.setSpeed(400);
		while (radius < 5000) {
			Motor.A.forward();
			if (lightSensor.getLightValue() < lineColor) {
				lineColor = lightSensor.getLightValue();
			}
			if (lightSensor.getLightValue() > fieldColor) {
				fieldColor = lightSensor.getLightValue();
			}
			radius += 1;
		}
		Motor.A.stop();
		int[] contrast = new int[2];
		contrast[0] = lineColor;
		contrast[1] = fieldColor;
		return contrast;

	}

	public void position(double threshold) {
		Motor.A.setSpeed(400);
		while (lightSensor.getLightValue() != (int) (threshold)) {
			Motor.A.forward();
		}
		Motor.A.stop();
	}

	@Override
	public void suppress() {
		Motor.A.stop();
		Motor.B.stop();
	}

}
