package uebung4.b;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class TicTacToe {

	private static String[] field = { " ", " ", " ", " ", " ", " ", " ", " ", " ", };

	private static BTConnection btConnector = null;

	private static NXTConnector nxtConnector;

	public static void main(String[] args) {
		// example Vorlesung
		boolean isTypSelected = false;
		// while Schleife um den Typ zu bestimmen
		// rechts -> Master, links -> Slave
		while (!isTypSelected) {
			if (Button.RIGHT.isDown()) {
				handleMaster();
				isTypSelected = true;
			} else if (Button.LEFT.isDown()) {
				handleSlave();
				isTypSelected = true;
			} else if (Button.ESCAPE.isDown()) {
				isTypSelected = true;
			}
		}
	}

	private static void handleSlave() {
		// Auf Connection Warten
		btConnector = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		// TODO
		while (!Button.ESCAPE.isDown()) {
			readSlave();
			try {
				byte[] b = new byte[100];
				int l = btConnector.read(b, b.length);
				String cmd = new String(b, 0, l);
				System.out.println(cmd);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private static void readSlave() {
		try {
			byte[] b = new byte[100];
			int l = btConnector.read(b, b.length);
			String cmd = new String(b, 0, l);
			field[Integer.valueOf(cmd) - 1] = "O";
			printGame();
			writeSlave();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void writeSlave() {
		int position = 0;
		while (!Button.ENTER.isDown()) {
			if (Button.RIGHT.isDown()) {
				position++;
				System.out.println(position);
			} else if (Button.LEFT.isDown()) {
				position--;
				System.out.println(position);
			}
		}
		String toSend = String.valueOf(position);
		btConnector.sendPacket(toSend.getBytes(), toSend.getBytes().length);
		readSlave();
	}

	private static void handleMaster() {
		// search for connections and display them
		NXTInfo[] nxts;
		nxtConnector = new NXTConnector();
		System.out.println("Suche BT NXTs ...");
		nxts = nxtConnector.search("", null, NXTCommFactory.BLUETOOTH);
		int i = 1;
		for (NXTInfo nxtInfo : nxts) {
			System.out.println(i + ": " + nxtInfo.name + " " + nxtInfo.deviceAddress);
			i++;
		}
		boolean deviceToConnectSelected = false;
		int selected = 0;
		while (!deviceToConnectSelected) {
			if (Button.RIGHT.isDown()) {
				selected++;
				System.out.println(selected);
			} else if (Button.LEFT.isDown()) {
				selected--;
				System.out.println(selected);
			} else if (Button.ENTER.isDown()) {
				// try to connect to device with index selected
				if (!nxtConnector.connectTo(nxts[selected], NXTComm.PACKET)) {
					System.out.println("Konnte nicht verbinden ...");
					System.exit(0);
				}
				System.out.println("Verbindung erfolgreich, das Spiel kann beginnen!");
				playGameMaster();

			}
		}
	}

	private static void playGameMaster() {
		System.out.println("Wählen Sie eine Position:\n");
		printGame();
		// Get NXTComm Object for I / O
		NXTComm comm = nxtConnector.getNXTComm();
		while (true) {
			// select position
			write(comm);
		}
	}

	private static void write(NXTComm comm) {
		int position = 0;
		while (!Button.ENTER.isDown()) {
			if (Button.RIGHT.isDown()) {
				position++;
				System.out.println(position);
			} else if (Button.LEFT.isDown()) {
				position--;
				System.out.println(position);
			}
		}
		String toSend = String.valueOf(position);
		try {
			// check if value is possible
			if (field[position--].equals(" ")) {
				field[position--] = "X";
				printGame();
			} else {
				System.out.println("Fehler: try again");
				write(comm);
			}
			comm.write(toSend.getBytes());
			read(comm);
			// wait for answer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void read(NXTComm comm) {
		try {
			byte[] b = comm.read();
			String readIn = String.valueOf(b);
			Integer poisition = Integer.valueOf(readIn);
			field[poisition--] = "O";
			printGame();
			write(comm);
		} catch (Exception e) {
		}

	}

	private static void printGame() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				sb.append(field[i] + "\n");
				sb.append("-----");
			} else {
				sb.append(field[i] + " | ");
			}
		}
	}

}
