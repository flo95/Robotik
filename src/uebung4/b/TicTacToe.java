package uebung4.b;

import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class TicTacToe {

	private static BTConnection conn = null;

	public static void main(String[] args) {
		// example Vorlesung
		conn = Bluetooth.waitForConnection(0, NXTConnection.PACKET);

		while (!Button.ESCAPE.isDown()) {
			try {
				byte[] b = new byte[100];
				int l = conn.read(b, b.length);
				String cmd = new String(b, 0, l);
				System.out.println(cmd);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
