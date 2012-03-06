package bubbleDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.DocumentHolder;

/**
 * @author Nikita Uvarov
 * Panel used to represent bubble diagram.
 */
public class BubblePanel extends JPanel {
    /**
     * Generated serialBersionUID.
     */
    private static final long serialVersionUID = -1270368110739421155L;
    /**
     * Container of data to visualize.
     */
    private final DocumentHolder holder;
    /**
     * Drawer which knows how to draw bubble diagram.
     */
    private final BubbleDrawer bubbleDrawer;

    /**
     * Creates BubblePanel using specified holder of data.
     * @param holder
     *        Container of data to visualize.
     */
    public BubblePanel(final DocumentHolder holder) {
        this.bubbleDrawer = new BubbleDrawer(holder);
        this.holder = holder;
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.bubbleDrawer.draw(g);
    }

    /**
     * Convert panel Graphics to Image.
     * @return Image containing whole diagram.
     */
    public final Image getImage() {
        BufferedImage img = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) img.getGraphics();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, getSize().width, getSize().height);
        g2.setClip(0, 0, getSize().width, getSize().height);
        this.bubbleDrawer.draw(g2);
        g2.dispose();
        return img;
    }
}
