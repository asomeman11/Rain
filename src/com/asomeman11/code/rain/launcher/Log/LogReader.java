package com.asomeman11.code.rain.launcher.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.logging.SimpleFormatter;
import com.asomeman11.code.rain.launcher.Launcher;

public class LogReader extends Launcher{
	
	public void logging() {
	Logger logger = Logger.getLogger("Log");
	FileHandler fh;
		try{
			fh = new FileHandler("/data/Log.");
			logger.addHandler(fh);
			SimpleFormatter formater = new SimpleFormatter();
			fh.setFormatter(formater);
		} catch (SecurityException s) {
			s.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
