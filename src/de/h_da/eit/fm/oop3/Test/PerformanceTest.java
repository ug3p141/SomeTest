package de.h_da.eit.fm.oop3.Test;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class PerformanceTest {

	private static Random seed = new Random();
	private double mill = Math.pow(10, 6);
	
	public static final String outFile = "SampleOutput.txt";

	public void run() {
	    Long begin, end;
	    double ms;
	    int size = 500000;
	    
	    begin = System.nanoTime();
		for(int i = 0; i < size; i++) {
			this.getRandomChar();
		}
		end = System.nanoTime();
		ms = (end - begin)/mill;
		System.out.println("Overhead: " + ms + " ms");
	    
		begin = System.nanoTime();
		this.writeRandomFile(size, false);
		end = System.nanoTime();
		ms = (end - begin)/mill;
		System.out.println("Unbuffered: " + ms + " ms");
		begin = System.nanoTime();
		this.writeRandomFile(size, true);
		end = System.nanoTime();
		ms = (end - begin)/mill;
		System.out.println("Buffered: " + ms + " ms");
	}
	
	public static void main(String[] args) {
		PerformanceTest pt = new PerformanceTest();
		pt.run();
	}

	public void writeRandomFile(int size, boolean buffered) {
		OutputStream os = getOutputStream(buffered);
		char c;
		for(int i = 0; i < size; i++) {
			c = getRandomChar();
			try {
				os.write(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public char getRandomChar() {
		char c = (char)seed.nextInt(256);
		return c;
	}

	public OutputStream getOutputStream(boolean buffered) {
		OutputStream fis;
		try {
			fis = new FileOutputStream(outFile);
			if (buffered) {
				fis = new BufferedOutputStream(fis);
			}
			return fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}