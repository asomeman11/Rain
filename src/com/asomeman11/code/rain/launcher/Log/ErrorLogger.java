package com.asomeman11.code.rain.launcher.Log;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ErrorLogger {

	   
		public static void main(String[] args) throws Throwable, IOException{
		Logger logger = Logger.getLogger("Log");
		FileHandler fh;
		try{
			fh = new FileHandler("/data/Log.txt");
			logger.addHandler(fh);
			SimpleFormatter formater = new SimpleFormatter();
			fh.setFormatter(formater);
			
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		logger.info("Hello!");
	}
		public void Logging(){
			
		}

	}


