package bubbleDrawer;

import java.awt.Graphics;
import java.util.List;

import model.Bubble;
import model.DocumentHolder;

public class BubbleDrawer implements Drawer {

    private final DocumentHolder holder;

    public BubbleDrawer(final DocumentHolder holder) {
        this.holder = holder;
    }

    @Override
    public final void draw(final Graphics g) {
        final List<Bubble> doc = holder.getCurrentDocument();
        for (final Bubble bubble : doc) {
            g.fillOval((int) bubble.getX(), (int) bubble.getY(),
                    (int) bubble.getRadius(), (int) bubble.getRadius());
        }
    }

}
