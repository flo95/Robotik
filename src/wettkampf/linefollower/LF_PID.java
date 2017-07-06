package wettkampf.linefollower;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class LF_PID {
	
	private static LightSensor lightSensor = new LightSensor(SensorPort.S1);
	
	public static void main(String args[]) {
		double color = 0;
		int [] contrast = calculateContrast();
		double threshold = (contrast[0] + contrast[1]) / 2;
		position(threshold);
		
		PID_Controller pc = new PID_Controller();
		PID_Parameter  pp = new PID_Parameter(100000, 75, 20);
		
		while(!Button.ESCAPE.isPressed()) {
			
			color = lightSensor.getLightValue();
			double result = pc.calculate(pp, color, threshold, 1, true);
			
			Motor.B.setSpeed(new Double(275-result).intValue());
			Motor.B.forward();
			Motor.C.setSpeed(new Double(275+result).intValue());
			Motor.C.forward();
		}
	}
	
	public static int[] calculateContrast() {
		int b = 100;
		int w = 0;
		int radius = 0;
		Motor.B.setSpeed(400);
		while(radius < 5000) {
			Motor.B.forward();
			if(lightSensor.getLightValue() < b) {
				b = lightSensor.getLightValue();
			}
			if(lightSensor.getLightValue() > w) {
				w = lightSensor.getLightValue();
			}
			radius += 1;
		}
		Motor.B.stop();
		int [] contrast = new int[2];
		contrast[0] = b;
		contrast[1] = w;
		return contrast;
		
	}
	
	public static void position(double threshold) {
		Motor.B.setSpeed(400);
		while(lightSensor.getLightValue() != (int)(threshold)) {
			Motor.B.forward();
		}
		Motor.B.stop();
	}
	
}
