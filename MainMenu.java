import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final GameFrame frame;

    public MainMenu(GameFrame frame, SoundManager soundManager) {
        this.frame = frame;


        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("D:/UBB/Halado_java/Projekt1/resources/background.jpg"));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);


        JLabel titleLabel = new JLabel("Memory Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton easyButton = new JButton("Easy (4x4)");
        JButton mediumButton = new JButton("Medium (6x6)");
        JButton hardButton = new JButton("Hard (8x8)");
        JButton customButton = new JButton("Custom Game");
        JButton settingsButton = new JButton("Settings");
        JButton highScoreButton = new JButton("High Score");
        JButton helpButton = new JButton("Help");


        Dimension buttonSize = new Dimension(200, 50);
        easyButton.setMaximumSize(buttonSize);
        mediumButton.setMaximumSize(buttonSize);
        hardButton.setMaximumSize(buttonSize);
        customButton.setMaximumSize(buttonSize);
        settingsButton.setMaximumSize(buttonSize);
        highScoreButton.setMaximumSize(buttonSize);
        helpButton.setMaximumSize(buttonSize);

        easyButton.addActionListener(e -> frame.switchToGameBoard(4, false));
        mediumButton.addActionListener(e -> frame.switchToGameBoard(6, false));
        hardButton.addActionListener(e -> frame.switchToGameBoard(8, false));
        customButton.addActionListener(e -> frame.switchToGameBoard(4, true));


        settingsButton.addActionListener(e -> frame.switchToSettings());
        highScoreButton.addActionListener(e -> frame.switchToHighScore());
        helpButton.addActionListener(e -> frame.switchToHelp());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        easyButton.setBackground(Color.yellow);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setBackground(Color.orange);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setBackground(Color.red);
        customButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customButton.setBackground(Color.blue);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalStrut(50));
        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(mediumButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(hardButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(customButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(settingsButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(highScoreButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(helpButton);


        background.add(Box.createVerticalGlue());
        background.add(titleLabel);
        background.add(Box.createVerticalStrut(20));
        background.add(buttonPanel);
        background.add(Box.createVerticalGlue());
    }
}
