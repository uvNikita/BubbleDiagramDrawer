package bubbleDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.DocumentHolder;

public class BubblePanel extends JPanel {
    private final DocumentHolder holder;
    private final BubbleDrawer bubbleDrawer;

    public BubblePanel(final DocumentHolder holder) {
        this.bubbleDrawer = new BubbleDrawer(holder);
        this.holder = holder;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.bubbleDrawer.draw(g);
    }

    public Image getImage() {
        BufferedImage img = new BufferedImage(this.getWidth(), this
                .getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) img.getGraphics();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, getSize().width, getSize().height);
        g2.setClip(0, 0, getSize().width, getSize().height);
        this.bubbleDrawer.draw(g2);
        g2.dispose();
        return img;
    }
}
