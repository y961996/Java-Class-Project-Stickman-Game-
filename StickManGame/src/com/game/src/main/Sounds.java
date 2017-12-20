package com.game.src.main;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	
	private AudioInputStream sound;
	
	public Sounds(String path){
		try {
			Clip clip = AudioSystem.getClip();
			sound = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			clip.open(sound);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SoundLoader(AudioInputStream sound){
		
	}
}
