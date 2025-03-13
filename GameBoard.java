import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

public class GameBoard extends JPanel {
    SoundManager soundManager;
    private GameFrame frame;
    private int gridSize;
    private ArrayList<Tile> tiles;
    private boolean customMode;
    private int timeRemaining;
    private Timer timer;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private int score;
    private boolean gameStarted = false;
    private Image backgroundImage;
    private Tile Imageone;
    private int flipped;
    private int foundPairs;
    private int foundrow;
    private int customtime;


    private JButton backButton;
    private JButton newGameButton;
    private JButton playButton;


    private JPanel gridPanel;


    private boolean isProcessing = false;

    public GameBoard(int gridSize, boolean customMode, GameFrame frame, SoundManager soundManager) {
        this.frame = frame;
        this.gridSize = gridSize;
        this.customMode = customMode;
        this.tiles = new ArrayList<>();
        this.score = 0;
        foundPairs = 0;
        foundrow = 0;
        this.soundManager = soundManager;
        customtime=120;


        if (gridSize == 4) {
            this.timeRemaining = 120;
        } else if (gridSize == 6) {
            this.timeRemaining = 180;
        } else if (gridSize == 8) {
            this.timeRemaining = 240;
        }


        setLayout(new BorderLayout());


        try {
            backgroundImage = ImageIO.read(new File("D:/UBB/Halado_java/Projekt1/resources/background.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        gridPanel.setLayout(new GridLayout(gridSize, gridSize, 20, 20));
        add(gridPanel, BorderLayout.CENTER);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 2));
        timeLabel = new JLabel("Time: " + timeRemaining + "s");
        scoreLabel = new JLabel("Score: " + score);
        infoPanel.add(timeLabel);
        infoPanel.add(scoreLabel);
        add(infoPanel, BorderLayout.NORTH);


        if (customMode) {
            loadCustomImages();
        } else {
            loadDefaultImages();
        }


        shuffleAndAddTiles(gridPanel);

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        backButton = new JButton("Back to Menu");
        newGameButton = new JButton("New Game");
        playButton = new JButton("Start Game");


        backButton.setBackground(Color.RED);
        newGameButton.setBackground(Color.RED);
        playButton.setBackground(Color.RED);


        buttonPanel.add(backButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(playButton);


        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        add(separator, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);


        backButton.addActionListener(e -> backToMenu());
        newGameButton.addActionListener(e -> startNewGame());
        playButton.addActionListener(e -> startGame());
    }


    private void handleTileClick(Tile imageone, Tile imagetwo) {

        if (!gameStarted) {
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean matchFound = false;
                if (imageone.getFrontImageIcon().equals(imagetwo.getFrontImageIcon())) {

                    matchFound = true;
                    score += 5;
                    foundrow++;
                    if (foundrow % 3 == 0) {
                        score += 10;
                    }
                    foundPairs++;


                    soundManager.playSound("D:/UBB/Halado_java/Projekt1/sounds/correct.wav");


                    if (foundPairs == (gridSize * gridSize) / 2) {
                        winGame();
                    }
                } else {

                    score -= 2;
                    foundrow = 0;
                    soundManager.playSound("D:/UBB/Halado_java/Projekt1/sounds/wrong (mp3cut.net).wav");


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imageone.flip();
                            imagetwo.flip();
                            imageone.setProcessing(false);
                            imagetwo.setProcessing(false);
                        }
                    });
                }


                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        scoreLabel.setText("Score: " + score);
                    }
                });
            }
        }).start();
    }


    private void winGame() {
        timer.stop();
        soundManager.playSound("D:/UBB/Halado_java/Projekt1/sounds/win.wav");
        String playerName = JOptionPane.showInputDialog(null, "Enter your name:");
        if (playerName != null && !playerName.isEmpty()) {
            HighScoreManager.saveHighScore(playerName, score, false);
        }
        JOptionPane.showMessageDialog(null, "Congratulations! You've found all pairs! Final score: " + score);
        frame.switchToMainMenu();
    }


    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timeLabel.setText("Time: " + timeRemaining + "s");
                if (timeRemaining == 10) {
                    soundManager.playSound("D:/UBB/Halado_java/Projekt1/sounds/warning.wav");
                }
                if (timeRemaining <= 0) {
                    endGame();
                }
            }
        });
        timer.start();
    }


    private void endGame() {
        timer.stop();
        soundManager.playSound("D:/UBB/Halado_java/Projekt1/sounds/lost.wav");
        String playerName = JOptionPane.showInputDialog(null, "Enter your name:");
        if (playerName != null && !playerName.isEmpty()) {
            HighScoreManager.saveHighScore(playerName, score, true);
        }
        JOptionPane.showMessageDialog(null, "Game over! Final score: " + score);
        frame.switchToMainMenu();
    }


    private void loadDefaultImages() {
        ImageIcon backImage = new ImageIcon("D:/UBB/Halado_java/Projekt1/resize/red_back.png");
        String[] defaultPaths = {
                "D:/UBB/Halado_java/Projekt1/resize/AD.png",
                "D:/UBB/Halado_java/Projekt1/resize/AH.png",
                "D:/UBB/Halado_java/Projekt1/resize/AC.png",
                "D:/UBB/Halado_java/Projekt1/resize/AS.png",
                "D:/UBB/Halado_java/Projekt1/resize/KD.png",
                "D:/UBB/Halado_java/Projekt1/resize/KH.png",
                "D:/UBB/Halado_java/Projekt1/resize/KC.png",
                "D:/UBB/Halado_java/Projekt1/resize/KS.png",
                "D:/UBB/Halado_java/Projekt1/resize/QD.png",
                "D:/UBB/Halado_java/Projekt1/resize/QH.png",
                "D:/UBB/Halado_java/Projekt1/resize/QC.png",
                "D:/UBB/Halado_java/Projekt1/resize/QS.png",
                "D:/UBB/Halado_java/Projekt1/resize/JD.png",
                "D:/UBB/Halado_java/Projekt1/resize/JH.png",
                "D:/UBB/Halado_java/Projekt1/resize/JC.png",
                "D:/UBB/Halado_java/Projekt1/resize/JS.png",
        };

        int pairsNeeded = (gridSize * gridSize) / 2;
        for (int i = 0; i < pairsNeeded; i++) {
            ImageIcon frontImage = new ImageIcon(defaultPaths[i % defaultPaths.length]);
            tiles.add(new Tile(frontImage, backImage));
            tiles.add(new Tile(frontImage, backImage));
        }
    }

    private void loadCustomImages() {
        ImageIcon backImage = new ImageIcon("D:/UBB/Halado_java/Projekt1/resize/red_back.png");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                ImageIcon frontImage = new ImageIcon(file.getAbsolutePath());
                tiles.add(new Tile(frontImage, backImage));
                tiles.add(new Tile(frontImage, backImage));
            }
        }

        int requiredPairs = (gridSize * gridSize) / 2;
        if (tiles.size() / 2 < requiredPairs) {
            JOptionPane.showMessageDialog(this, "Not enough images selected. Loading default images.");
            loadDefaultImages();
        }

        
        String inputTime = JOptionPane.showInputDialog(this, "Set the time limit (in seconds):");
        try {
            int customTime = Integer.parseInt(inputTime);

            if (customTime > 0) {
                customtime=customTime;
                this.timeRemaining = customTime;
                timeLabel.setText("Time: " + timeRemaining + "s");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid time entered. Using default time.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Using default time.");
        }
    }



    private void backToMenu() {
        frame.switchToMainMenu();
    }

    private void startGame() {
        gameStarted = true;
        startTimer();


        for (Tile tile : tiles) {
            tile.setGameStarted(true);
            if (tile.isFlipped()) {
                tile.flip();
            }
        }


    }


    private void startNewGame() {
        if (gameStarted) {
            timer.stop();

            gridPanel.removeAll();


            score = 0;
            foundPairs = 0;
            foundrow = 0;
            scoreLabel.setText("Score: " + score);


            timeRemaining = (gridSize == 4) ? 120 : (gridSize == 6) ? 180 : 240;
            if(customMode){
                timeRemaining=customtime;
            }
            timeLabel.setText("Time: " + timeRemaining + "s");


            shuffleAndAddTiles(gridPanel);

            for (Tile tile : tiles) {
                if (!tile.isFlipped()) {
                    tile.flip();
                }
                tile.setProcessing(false);

            }


            gridPanel.revalidate();
            gridPanel.repaint();

            gameStarted = false;
        }
    }

    private void shuffleAndAddTiles(JPanel gridPanel) {
        Collections.shuffle(tiles);


        for (Tile tile : tiles) {
            tile.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!tile.getProcessing() && gameStarted) {
                        if (flipped == 1) {
                            tile.setProcessing(true);
                            handleTileClick(Imageone, tile);
                            flipped = 0;
                        } else {
                            flipped++;
                            Imageone = tile;
                            tile.setProcessing(true);
                        }
                    }
                }
            });

            gridPanel.add(tile);
        }
    }
}
