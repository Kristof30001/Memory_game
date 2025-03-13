import javax.swing.*;
import java.awt.*;

public class SettingsMenu extends JPanel {
    private final JCheckBox musicToggle;
    private final JCheckBox soundToggle;

    public SettingsMenu(GameFrame frame, SoundManager soundManager) {
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("D:/UBB/Halado_java/Projekt1/resources/background.jpg"));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);


        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);


        musicToggle = new JCheckBox("Mute Music");
        musicToggle.setFont(new Font("Arial", Font.PLAIN, 16));
        musicToggle.setOpaque(false);
        musicToggle.setForeground(Color.WHITE);
        musicToggle.setSelected(soundManager.isMusicMuted());


        soundToggle = new JCheckBox("Mute Sound Effects");
        soundToggle.setFont(new Font("Arial", Font.PLAIN, 16));
        soundToggle.setOpaque(false);
        soundToggle.setForeground(Color.WHITE);
        soundToggle.setSelected(soundManager.isEffectsMuted());


        musicToggle.addActionListener(e -> soundManager.setMusicMuted(musicToggle.isSelected()));
        soundToggle.addActionListener(e -> soundManager.setEffectsMuted(soundToggle.isSelected()));

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setMaximumSize(new Dimension(200, 50));
        backButton.addActionListener(e -> frame.switchToMainMenu());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(musicToggle);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(soundToggle);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(backButton);

        background.add(Box.createVerticalGlue());
        background.add(titleLabel);
        background.add(Box.createVerticalStrut(20));
        background.add(buttonPanel);
        background.add(Box.createVerticalGlue());
    }
}
