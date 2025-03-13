import javax.swing.*;
import java.awt.*;

public class HelpMenu extends JPanel {
    public HelpMenu(GameFrame frame) {
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("D:/UBB/Halado_java/Projekt1/resources/background.jpg"));
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);


        JLabel titleLabel = new JLabel("Help - Memory Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);


        JTextArea helpText = new JTextArea();
        helpText.setText("Welcome to Memory Game!\n\n"
                + "Rules:\n"
                + "1. Flip two tiles at a time to find matching pairs.\n"
                + "2. You get points for correct matches and lose points for mismatches.\n"
                + "3. Complete the game before time runs out!\n\n"
                + "Good luck and have fun!");
        helpText.setEditable(false);
        helpText.setOpaque(false);
        helpText.setForeground(Color.WHITE);
        helpText.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(helpText);
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
}
