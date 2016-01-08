package de.h_da.eit.fm.oop3.Test;

import java.io.File;

public class TestFileAccess {

	public static void main(String[] args) {
		File myFile = new File("C:\\Windows");
		System.out.println("Existiert: " + myFile.exists());
		if (myFile.exists()) {
			System.out.println("Ordner: " + myFile.isDirectory());
			for(File f: myFile.listFiles()) {
				System.out.println(f.getName());
			}
		}
		
	}

}
