package com.burksnet.code.games.rain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class MyProperties {
	public static boolean sound;
	public static int maxFrames;

	public MyProperties(){
		try {
			File file = new File(System.getProperty("user.dir") + "/data/properties.txt");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				
				parse(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parse(String key, String value){
		
		if(key.equalsIgnoreCase("SOUND")){
			MyProperties.sound = Boolean.parseBoolean(value);
		}
		if(key.equalsIgnoreCase("MAX_FRAMES")){
			MyProperties.maxFrames = Integer.parseInt(value);
		}
		
	}
	
}
