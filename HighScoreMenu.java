import javax.swing.*;
import java.awt.*;

public class HighScoreMenu extends JPanel {
    private final HighScoreManager manager;
    private JTextArea scoreText;
    private JScrollPane scrollPane;

    public HighScoreMenu(GameFrame frame, HighScoreManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("D:/UBB/Halado_java/Projekt1/resources/background.jpg"));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);

        JLabel titleLabel = new JLabel("High Scores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        scoreText = new JTextArea();
        scoreText.setEditable(false);
        scoreText.setOpaque(false);
        scoreText.setFont(new Font("Arial", Font.PLAIN, 16));
        scoreText.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(scoreText);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setMaximumSize(new Dimension(200, 50));
        backButton.addActionListener(e -> frame.switchToMainMenu());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(backButton);

        background.add(Box.createVerticalGlue());
        background.add(titleLabel);
        background.add(Box.createVerticalStrut(20));
        background.add(scrollPane);
        background.add(Box.createVerticalStrut(20));
        background.add(buttonPanel);
        background.add(Box.createVerticalGlue());
    }

    public void refreshScores() {
        scoreText.setText("");
        for (String score : manager.getScores()) {
            scoreText.append(score + "\n");
        }
    }
}
