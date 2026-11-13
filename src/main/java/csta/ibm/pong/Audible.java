/*
* Author: Cary
* Date: 2026-11-12
* */

package csta.ibm.pong;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * <p>An intermediate class for all objects that can make a sound, such as a ball or a power up object</p>
 * */
public class Audible extends GameObject {
    /**
     * Used to create a sound file from Google Classroom example
     * Used AI to help debug my file path issue, and it turns out the file path is wrong. I need to use getResource()
     * to get the correct path.
     *
     * @param fileName the file path from the source root starting with a "/"
     */
    public static void initSound(String fileName) throws MalformedURLException, LineUnavailableException,
            UnsupportedAudioFileException, IOException {
        // System.out.println(Pong.class.getResource(fileName));
        File url = new File(fileName);
        Clip clip = AudioSystem.getClip();

        // Uses the class's resource path instead of system path
        AudioInputStream ais = AudioSystem.getAudioInputStream(
                Pong.class.getResource(fileName)
        );
        clip.open(ais);
        clip.start();
    }

    /**
     * Method to play sound effect
     *
     * @param fileName the file path from the source root starting with a "/"
     */
    public void playSound(String fileName){
        try {
            initSound(fileName);
        } catch (LineUnavailableException | UnsupportedAudioFileException
                 | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void act() {}
}
