package com.burksnet.code.games.rain.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import com.burksnet.code.games.rain.MyProperties;
	
public class Sound {
	static final String classPath = System.getProperty("user.dir") + "/res/audio/";
	public static boolean running = MyProperties.sound;

	public static String mainUrl;

	String fileLocation;

	public static void stop() {
		running = false;
	}

	public static synchronized void playSoundOnce(final String url) {
		System.out.println("Tried Once");
		if(running == false)
			return;
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							new File(classPath + url));
					clip.open(inputStream);
					clip.start(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, url).start();
	}
	public static synchronized void playSoundForever(final String url) {
		mainUrl = url;
		System.out.println("Tried forever");
		Thread t = new Thread(new Runnable() {
			public void run() {
				Clip mainClip = null;
				try {
					mainClip = AudioSystem.getClip();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {

					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(classPath + url));
					mainClip.open(inputStream);
					mainClip.loop(Clip.LOOP_CONTINUOUSLY);
					mainClip.start(); 
				} catch (Exception e) {
					e.printStackTrace();
				}

				while(true){
					if(!running){
						mainClip.stop();
					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, url);
		t.start();
	}

	public static void start() {
		running = true;
		playSoundForever(mainUrl);

	}
}
