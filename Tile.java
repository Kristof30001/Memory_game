import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tile extends JComponent {
    private final ImageIcon frontImage;
    private final ImageIcon backImage;
    private boolean isFlipped;
    private boolean isProcessing;
    private boolean gameStarted;


    public Tile(ImageIcon front, ImageIcon back) {
        this.frontImage = front;
        this.backImage = back;
        this.isFlipped = true;
        this.isProcessing = false;
        this.gameStarted = false;
        setPreferredSize(new Dimension(100, 160));


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isProcessing && gameStarted) {
                    flip();
                }
            }
        });
    }


    public void flip() {
        isFlipped = !isFlipped;
        repaint();
    }


    public boolean isFlipped() {
        return isFlipped;
    }


    public ImageIcon getFrontImageIcon() {
        return frontImage;
    }


    public void setProcessing(boolean processing) {
        this.isProcessing = processing;
    }


    public void setGameStarted(boolean started) {
        this.gameStarted = started;
    }

    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    public boolean getProcessing() {
        return isProcessing;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isFlipped) {
            g.drawImage(frontImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(backImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
