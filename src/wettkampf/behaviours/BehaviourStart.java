package wettkampf.behaviours;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;
import wettkampf.EnumTrashcanPosition;
import wettkampf.Model;
import wettkampf.listener.LightSensorListener;

public class BehaviourStart implements Behavior {

	private static Model model;
	private LightSensorListener lightSensorListener;
		private LightSensor lightSensor = new LightSensor(SensorPort.S4);

	public BehaviourStart(LightSensorListener lightSensorListener) {
		this.lightSensorListener = lightSensorListener;
	}

	@Override
	public boolean takeControl() {
		// Start verhalten des Roboter
		model = Model.getInstance();
		return model.isStart();
	}

	@Override
	public void action() {
		// Lesen Farben ein und warte auf den letzten Click
		// Heimatfeld
		lightSensorListener.getLastValue();
		System.out.print("Heimatfeld: ");
		Button.ENTER.waitForPressAndRelease();
		int homeFieldColor = lightSensor.getLightValue();
		System.out.print(homeFieldColor + "\n");
		model.setHomeFieldColor(homeFieldColor);
		// Gegner
		System.out.print("Gegner: ");
		Button.ENTER.waitForPressAndRelease();
		int enemyFieldColor = lightSensor.getLightValue();
		System.out.print(enemyFieldColor + "\n");
		model.setEnemyFieldColor(enemyFieldColor);
		// Feld
		System.out.print("Feld: ");
		Button.ENTER.waitForPressAndRelease();
		int fieldColor = lightSensor.getLightValue();
		System.out.print(fieldColor + "\n");
		model.setFieldColor(fieldColor);
		// Linie
		System.out.print("Linie: ");
		Button.ENTER.waitForPressAndRelease();
		int lineColor = lightSensor.getLightValue();
		System.out.print(lineColor + "\n");
		model.setLineColor(lineColor);
		model.setStart(false);
		int waitForAnyPress = Button.waitForAnyPress();
		/*if (Button.ENTER.getId() == waitForAnyPress) {
			model.setPosition(EnumTrashcanPosition.MIDDLE);
		} else if (Button.RIGHT.getId() == waitForAnyPress) {
			model.setPosition(EnumTrashcanPosition.RIGHT);
		} else if (Button.LEFT.getId() == waitForAnyPress) {
			model.setPosition(EnumTrashcanPosition.LEFT);
		}*/
	}

	@Override
	public void suppress() {
		model.setStart(false);
	}

}
