package com.burksnet.code.games.rain.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import com.burksnet.code.games.rain.MyProperties;

public class Sound {
	static final String classPath = System.getProperty("user.dir") + "/res/audio/";

	public static String mainUrl;
	
	String fileLocation;
	static FloatControl volume;
	//-35 to 5
	static float volPerc = -15F;
	
	public static Clip mainClip;

	public Clip clip;
	
	public static void stop() {
		MyProperties.music = false;
		
	}

	static Thread stopper = null;
	
	public static synchronized void changeVolume(float perc, boolean in){
		stopSound();
		volPerc = perc;
		MyProperties.music = in;
	}
	
	public static synchronized void playSoundOnce(final String url) {
		System.out.println("Tried Once");
		if(!MyProperties.sound)
			return;
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							new File(classPath + url));
					clip.open(inputStream);
					((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volPerc);
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
				mainClip = null;
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
					((FloatControl) mainClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volPerc);
					mainClip.start(); 
				} catch (Exception e) {
					e.printStackTrace();
				}

				while(true){
					if(!MyProperties.music){
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
		MyProperties.music = true;
		playSoundForever(mainUrl);

	}
	
	public static void stopSound(){
		MyProperties.music = false;
		if(mainClip != null)
			mainClip.stop();
		
		stopper = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(true){
					if(MyProperties.music){
						start();
						try {
							stopper.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		stopper.start();
		
	}

	public static float getVolume() {
		
		return volPerc;
	}
	
}
