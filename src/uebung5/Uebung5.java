package uebung5;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import main.robotik.Driver;

public class Uebung5 {
	private static BTConnection btConnector = null;

	public static void main(String[] args) {
		LightSensorListener lightSensorListener = new LightSensorListener();
		SensorPort.S1.addSensorPortListener(lightSensorListener);
		Driver driver = Driver.getInstance();
		driver.rotateRightDegrees(360);
		System.out.println("min = " + lightSensorListener.getMin());
		System.out.println("max = " + lightSensorListener.getMax());
		int mittelWert = (lightSensorListener.getMax() + lightSensorListener.getMin()) / 2;
		lightSensorListener.endFindMittelwert(mittelWert);
		System.out.println("mittelwert = " + mittelWert);
		// find start position
		while (lightSensorListener.getAkutellerWert() <= mittelWert + 2
				&& lightSensorListener.getAkutellerWert() >= mittelWert - 2) {
			driver.rotateRightDegrees(1);
		}
		// while (true) {
		// driver.forward();
		// }
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				handleSlave();
			}
		});
		t.start();
		Behavior forward = new DriveForward();
		Behavior findLine = new FindLine(lightSensorListener);
		Behavior[] behaviorArray = { forward, findLine };
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		arbitrator.start();
	}

	private static void handleSlave() {
		// Auf Connection Warten
		System.out.println("Warte ...");
		btConnector = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		readSlave();
	}

	private static void readSlave() {
		int counter = 0;
		// counter = 0 -> Kp
		// counter = 1 -> Tn
		// counter = 2 -> Tv
		while (!Button.ESCAPE.isDown()) {
			byte[] b = new byte[100];
			int l = btConnector.readPacket(b, b.length);
			String cmd = new String(b, 0, l);
			System.out.println("empfangen : " + cmd);
			if (counter == 2) {
				counter = 0;
			} else {

			}
			counter++;
		}
	}

}
