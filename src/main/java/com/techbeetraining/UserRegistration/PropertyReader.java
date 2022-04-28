package com.techbeetraining.UserRegistration;

import java.util.Properties;
import java.io.File;
import java.net.URL;
import java.io.FileInputStream;
import java.io.IOException;

public class PropertyReader {

	private Properties properties = new Properties();

	//private final String path1 = "C:/Users/TimothyPolke/Desktop/UserRegistration_OFFLINE/config.properties";
	private final String path2 = "/home/ubuntu/config.properties";

	public PropertyReader() { 
		loadFile();
	}
	
	public void loadFile() {

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(path2));
			properties.load(fileInputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readValue(String key) {
		
		return properties.getProperty(key);
	}
}
