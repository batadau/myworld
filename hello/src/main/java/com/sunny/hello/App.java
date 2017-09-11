package com.sunny.hello;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static String BASIC_PATH = "";
	public static void main (String args[])  throws IOException {  
		  String jarpath="D:\\repository-risk\\com\\google\\collections\\google-collections\\1.0\\google-collections-1.0.jar";  
		  JarFile jarFile = new JarFile(jarpath);  
		  Enumeration enu = jarFile.entries();  
		  while (enu.hasMoreElements()) {  
		      process(enu.nextElement());  
		  }  
		}  
		  
		private static void process(Object obj) {  
		    JarEntry entry = (JarEntry)obj;  
		    String name = entry.getName();  
		    long size = entry.getSize();  
		    long compressedSize = entry.getCompressedSize();  
		    System.out.println(name);  
		  }  
		
}
