package bubbleDrawer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import model.Bubble;
import model.DocumentHolder;

public class BubbleDrawer implements Drawer {
    private final Logger log = Logger.getLogger(BubbleDrawer.class.getName());
    private final DocumentHolder holder;

    public BubbleDrawer(final DocumentHolder holder) {
        this.holder = holder;
    }

    @Override
    public final void draw(final Graphics g) {
        final List<Bubble> doc = holder.getCurrentDocument();
        final List<Bubble> scaledDoc = new ArrayList<Bubble>();
        final Rectangle bound = new Rectangle();
        g.getClipBounds(bound);
        final int maxHeight = bound.height;
        final int maxWidth = bound.width;
        log.info(String.format("max height: %d", maxHeight));
        log.info(String.format("max width: %d", maxWidth));
        final double maxX = Collections.max(doc, Bubble.getComparatorByX())
                .getX();
        final double maxY = Collections.max(doc, Bubble.getComparatorByY())
                .getY();
        final double maxRadius = Collections.max(doc,
                Bubble.getComparatorByRadius()).getRadius();
        for (final Bubble bubble : doc) {
            scaledDoc.add(new Bubble(bubble.getX() / maxX * maxWidth, bubble
                    .getY() / maxY * maxHeight, bubble.getRadius() / maxRadius
                    * 100));
        }
        log.info(String.format("Scaled doc: %s", scaledDoc));
        log.info(String.format("Current doc", doc));
        for (final Bubble bubble : scaledDoc) {
            g.fillOval((int) bubble.getX(), (int) bubble.getY(),
                    (int) bubble.getRadius(), (int) bubble.getRadius());
        }
    }

}
