import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final SoundManager soundManager;

    public GameFrame() {
        soundManager = new SoundManager();
        soundManager.playBackgroundMusic("D:/UBB/Halado_java/Projekt1/sounds/backgroundmusic.wav");

        setTitle("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new CardLayout());


        MainMenu mainMenu = new MainMenu(this, soundManager);
        add(mainMenu, "MainMenu");

        SettingsMenu settingsMenu = new SettingsMenu(this, soundManager);
        add(settingsMenu, "Settings");

        HighScoreMenu highScoreMenu = new HighScoreMenu(this, new HighScoreManager());
        add(highScoreMenu, "HighScore");

        HelpMenu helpMenu = new HelpMenu(this);
        add(helpMenu, "Help");


        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "MainMenu");
    }

    public void switchToGameBoard(int gridSize, boolean customMode) {
        GameBoard gameBoard = new GameBoard(gridSize, customMode, this, soundManager);
        getContentPane().add(gameBoard, "GameBoard");
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "GameBoard");
    }

    public void switchToMainMenu() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "MainMenu");
    }

    public void switchToSettings() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "Settings");
    }

    public void switchToHighScore() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        Component highScoreMenu = getContentPane().getComponent(2);
        if (highScoreMenu instanceof HighScoreMenu) {
            ((HighScoreMenu) highScoreMenu).refreshScores();
        }
        layout.show(getContentPane(), "HighScore");
    }


    public void switchToHelp() {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "Help");
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}
