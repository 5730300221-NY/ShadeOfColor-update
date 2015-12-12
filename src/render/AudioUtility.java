package render;

import java.applet.Applet;
import java.applet.AudioClip;

public class AudioUtility {
	private static AudioClip Intro,Click,Attack,GameSound;
	
	static{
		try {
			ClassLoader cl = AudioUtility.class.getClassLoader();
			Intro = Applet.newAudioClip(cl.getResource("audio/Intro.wav").toURI().toURL());
			Click = Applet.newAudioClip(cl.getResource("audio/Click.wav").toURI().toURL());
			Attack = Applet.newAudioClip(cl.getResource("audio/Attack.wav").toURI().toURL());
			GameSound = Applet.newAudioClip(cl.getResource("audio/GameSound.wav").toURI().toURL());
		} catch (Exception e) {
			Intro=null;
			Click=null;
			Attack=null;
			System.out.println("Can't Load");
		}
		
	}
	
	public static void playSound(String identifier){
		/* fill code */
		if(identifier.equalsIgnoreCase("Intro")){
			Intro.loop();
		}
		else if(identifier.equalsIgnoreCase("Click")){
			Click.play();
		}
		else if(identifier.equalsIgnoreCase("Attack")){
			Attack.play();
		}
		else if(identifier.equalsIgnoreCase("GameSound")){
			GameSound.loop();
		}
	}
	
	public static void stopSound(String identifier){
		if(identifier.equalsIgnoreCase("Intro")){
			Intro.stop();
		}
		else if(identifier.equalsIgnoreCase("Click")){
			Click.stop();
		}
		else if(identifier.equalsIgnoreCase("Attack")){
			Attack.stop();
		}
		else if(identifier.equalsIgnoreCase("GameSound")){
			GameSound.stop();
		}
	}
}
