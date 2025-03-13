import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "D:/UBB/Halado_java/Projekt1/highscores.txt";

    public static void saveHighScore(String playerName, int score, boolean lost) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE, true))) {
            if (!lost) {
                writer.write(playerName + ":" + score);
            } else {
                writer.write(playerName + "(lost) :" + score);
            }
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    public static List<String> loadHighScores() {
        List<String> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading high scores: " + e.getMessage());
        }
        return highScores;
    }

    public static List<String> getSortedHighScores() {
        List<String> highScores = loadHighScores();
        highScores.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(":")[1]);
            int scoreB = Integer.parseInt(b.split(":")[1]);
            return Integer.compare(scoreB, scoreA);
        });
        return highScores;
    }


    public String[] getScores() {
        List<String> sortedScores = getSortedHighScores();
        return sortedScores.toArray(new String[0]);
    }
}
