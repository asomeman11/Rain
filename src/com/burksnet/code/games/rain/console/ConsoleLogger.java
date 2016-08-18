package com.burksnet.code.games.rain.console;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConsoleLogger {
	public static boolean LogAll = true;
	public static boolean LogErrs = false;
	public static final Scanner out = new Scanner(System.console());
	public static void main(String[] args) throws Throwable, IOException{
	Logger logger = Logger.getLogger("Log");
	FileHandler fh;
	try{ 
		if (LogAll);
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
	} catch (IOException e) {
		
	}
}
	public void Logging(){
		log(out);
	}
	private void log(Scanner out) {

		
	}
}


	