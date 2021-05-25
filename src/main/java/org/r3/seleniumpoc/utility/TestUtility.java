package org.r3.seleniumpoc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class TestUtility {

public static String readProperty(String fileName, String key) {
		Properties prop = new Properties();
		String value = null;
		try {
			prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "\\config\\" + fileName)));
			value = prop.getProperty(key);
			return value;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
