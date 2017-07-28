package com.example_newsfinal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringUtils {
	public static String Is2String(InputStream is) throws IOException{		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
		byte[] arr =new byte[1024];
		while ((len = is.read(arr)) !=-1) {
			baos.write(arr, 0, len);
		}
		
		return baos.toString();
		
	} 
}
