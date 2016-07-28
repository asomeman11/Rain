package com.burksnet.code.games.rain.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable {
	String classPath = System.getProperty("user.dir") + "/res/audio/";
	private static boolean running = true;

	String fileLocation;

	public static void stop() {
		running = false;
	}

	public Sound(String fileName) {
		Thread t = new Thread(this);
		fileLocation = fileName;
		t.start();
	}

	public void run() {
		playSound(fileLocation);
	}

	private void playSound(String fileName) {

		File soundFile = new File(classPath + fileName);
		AudioInputStream audioInputStream = null;

		while (running) {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			}

			AudioFormat audioFormat = audioInputStream.getFormat();

			SourceDataLine line = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

			try {
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(audioFormat);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			line.start();
			int nBytesRead = 0;
			byte[] abData = new byte[128000];
			while (nBytesRead != -1) {
				if (!running)
					return;
				try {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (nBytesRead >= 0) {
					int nBytesWritten = line.write(abData, 0, nBytesRead);
				}
			}
			line.drain();
			line.close();
		}
	}
}