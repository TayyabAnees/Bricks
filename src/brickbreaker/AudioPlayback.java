package brickbreaker;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayback {
    private static final String BG_MUSIC_PATH = "assets/bg-music.wav";
    private static final String BREAK_SOUND_PATH = "assets/break.wav";

    public static void playMusic() {
        playAudio(BG_MUSIC_PATH, true);
    }

    public static void playSoundEffect() {
        playAudio(BREAK_SOUND_PATH, false);
    }

    // Extract Method: Reduce duplication in audio playback logic
    private static void playAudio(String filePath, boolean loop) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath))) {
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("File not supported: " + filePath);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Audio line is unavailable");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to open file: " + filePath);
            e.printStackTrace();
        }
    }
}
// Refactors Applied:
// 1. Extract Method: Consolidated audio playback logic
// 2. Duplicated Code: Removed repetition in audio handling