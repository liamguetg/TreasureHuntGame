//package model;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import java.net.URL;
//import java.security.spec.ECField;
//
//public class Sound {
//
//    Clip clip ;
//    URL soundURL[] = new URL[30];
//
//    public Sound() {
//        soundURL[0] =getClass().getResourceAsStream("filepath");
//        soundURL[1] =getClass().getResourceAsStream("filepath");
//        soundURL[2] =getClass().getResourceAsStream("filepath");
//        soundURL[3] =getClass().getResourceAsStream("filepath");
//    }
//
//    public void setFile(int i) {
//        try {
//            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
//            clip = AudioSystem.getClip();
//            clip.open(ais);
//        } catch (Exception e) {
//        }
//    }
//
//    public void play() {
//        clip.start();
//    }
//
//    public void loop() {
//        clip.loop(clip.LOOP_CONTINUOUSLY);
//    }
//
//    public void stop() {
//        clip.stop();
//    }
//}
