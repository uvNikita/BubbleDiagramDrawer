package bubbleDrawer;

import java.awt.Graphics;

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
}
