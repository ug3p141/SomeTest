package de.h_da.eit.fm.oop3.Test;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

public class TestInputStreamReader {

	public static void main(String[] args) {
		String fname = "SampleText.txt";
		try (InputStreamReader is = new InputStreamReader(new FileInputStream(fname))){
			int c;
			while ( (c = is.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
