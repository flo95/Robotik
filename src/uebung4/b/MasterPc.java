package uebung4.b;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class MasterPc {

	private static NXTInfo[] nxts;
	private static NXTConnector conn = new NXTConnector();

	public static void main(String[] args) {
		System.out.println("Suche BT NXTs ...");
		nxts = conn.search("", null, NXTCommFactory.BLUETOOTH);
		int i = 1;
		for (NXTInfo nxtInfo : nxts) {
			System.out.println(i + ": " + nxtInfo.name + " " + nxtInfo.deviceAddress);
			i++;
		}
	}

}
