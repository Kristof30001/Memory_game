import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private boolean musicMuted = false;
    private boolean effectsMuted = false;
    private Clip backgroundMusicClip;


    public void playBackgroundMusic(String musicPath) {
        if (musicMuted) return;

        try {
            File musicFile = new File(musicPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void playSound(String soundFile) {
        if (effectsMuted) return;

        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }


    public boolean isMusicMuted() {
        return musicMuted;
    }

    public void setMusicMuted(boolean muted) {
        this.musicMuted = muted;
        if (muted && backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        } else if (!muted && backgroundMusicClip != null && !backgroundMusicClip.isRunning()) {
            playBackgroundMusic("D:/UBB/Halado_java/Projekt1/sounds/backgroundmusic.wav");
        }
    }

    public boolean isEffectsMuted() {
        return effectsMuted;
    }

    public void setEffectsMuted(boolean muted) {
        this.effectsMuted = !effectsMuted;
    }
}
