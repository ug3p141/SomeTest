package de.h_da.eit.fm.oop3.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

public class TestFileAccess2 {

	public static void main(String[] args) {
		String fname = "SampleText.txt";
		Charset ch = Charset.defaultCharset();
		System.out.println(ch);
		Charset iso88591ch = Charset.forName("ISO-8859-1");
		List<Byte> bl = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(fname)){
			int c;
			while ( (c = fis.read()) != -1) {
				bl.add((byte) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Byte[] bla = bl.toArray(new Byte[bl.size()]);
		byte[] bla2 = new byte[bla.length];
		int j=0;
		for(Byte b: bla)
		    bla2[j++] = b.byteValue();
		try {
			String decodeBytes = new String(bla2, "UTF-8");
			System.out.println(decodeBytes);
		}	catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
