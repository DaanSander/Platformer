package com.daansander.platformer.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static Sound deathSound = new Sound("/sounds/Death.wav");
	
	private AudioClip clip;
	
	public Sound(String path) {
		clip = Applet.newAudioClip(Sound.class.getResource(path));
	}
	
	public void play() {
		clip.play();
	}
}