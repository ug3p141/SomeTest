package de.h_da.eit.fm.oop3.Test;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TimeClient {

	private static final int PORT = 37;
	private static final int MY_PORT = 42924;
	private static int port = MY_PORT;

	private static final String TIME_SERVER = "time.nist.gov";
	private static final String MY_SERVER = "localhost";
	private static String time_server = MY_SERVER;
	
	public void run() {
		this.getTime();
		// this.testit();
	}

	public void getTime() {
		try ( Socket timeSocket = new Socket(TimeClient.time_server, TimeClient.port) ) {
			InputStream is = new BufferedInputStream(timeSocket.getInputStream());
			long l = this.readBytes(is);
			System.out.println(l);
			System.out.println(Long.toHexString(l));
			double year = ((double) l) / (365.25*24*3600) + 1900;
			System.out.println(year);
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testit() {
		byte[] bl = { 0x11, 0x22, 0x33, 0x44 };
/*		try (InputStream fis = new BufferedInputStream(System.in)){
			int c;
			int j = 0;
			while ( (c = fis.read()) != -1) {
				bl[j]= (byte) c;
				j++;
				if (j == 3) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
*/		
		
		InputStream is = new ByteArrayInputStream(bl);
		try {
			long l = this.readBytes(is);
			System.out.println(l);
			System.out.println(Long.toHexString(l));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	long readBytes(InputStream in) throws IOException {
		byte b;
		long l = 0;
		for(int i = 0; i < 4; i++) {
			b = (byte) in.read();
			System.out.println("Byte " + i + ": " + (b & 0xFF));
			l = l ^ (b & 0xFF);
			if (i < 3) {
				l = l << 8;
			}
		}
		return l;
	}
	
	public static void main(String[] args) {
		(new TimeClient()).run();
	}
}