package de.h_da.eit.fm.oop3.Test;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;

public class TimeServer {

	private static final int PORT = 42924;
	private static int port = PORT;
	
	public static void main(String[] args) {
		TimeServer ts = new TimeServer();
		ts.run();

	}

	public byte[] getTimeBytes() {
		byte[] tb = new byte[4];
		long msEpoch = System.currentTimeMillis();
		long msOut = msEpoch/1000 + 2208988800L;
		System.out.println(msOut);
		System.out.println(Long.toHexString(msOut));
		tb[0] = (byte) (msOut >> 24);
		tb[1] = (byte) (msOut >> 16);
		tb[2] = (byte) (msOut >> 8);
		tb[3] = (byte) (msOut);
		System.out.println(tb[0] + " " + tb[1] + " " + tb[2] + " " + tb[3]);
		return tb;
	}

	public void run() {
		try (ServerSocket ss = new ServerSocket(port)) {
			while(true) {
				try(Socket conn = ss.accept()) {
					OutputStream out = conn.getOutputStream();
					byte[] timeBytes = this.getTimeBytes();
					out.write(timeBytes);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
