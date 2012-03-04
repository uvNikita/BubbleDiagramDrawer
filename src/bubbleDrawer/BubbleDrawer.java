package bubbleDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import model.Bubble;
import model.DocumentHolder;

public class BubbleDrawer implements Drawer {
    private static final int DEFAULT_MAX_RADIUS = 50;
    private static final int INITIAL_AXIS_SHIFT = 5;
    private final Logger log = Logger.getLogger(BubbleDrawer.class.getName());
    private final DocumentHolder holder;

    public BubbleDrawer(final DocumentHolder holder) {
        this.holder = holder;
    }

    @Override
    public final void draw(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final List<Bubble> doc = holder.getCurrentDocument();
        if (doc.isEmpty())
            return;
        final List<Bubble> scaledDoc = new ArrayList<Bubble>();

        final Rectangle bound = new Rectangle();
        g2d.getClipBounds(bound);

        final int maxHeight = bound.height - BubbleDrawer.DEFAULT_MAX_RADIUS
                - BubbleDrawer.INITIAL_AXIS_SHIFT;
        final int maxWidth = bound.width - BubbleDrawer.DEFAULT_MAX_RADIUS
                - BubbleDrawer.INITIAL_AXIS_SHIFT;
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
                    * BubbleDrawer.DEFAULT_MAX_RADIUS));
        }
        final Bubble bottomMost = Collections.min(scaledDoc,
                Bubble.getComparatorByY());
        double yCorrector = bottomMost.getRadius() - bottomMost.getY();
        if (yCorrector < 0) {
            yCorrector = BubbleDrawer.INITIAL_AXIS_SHIFT;
        }
        final Bubble leftMost = Collections.min(scaledDoc,
                Bubble.getComparatorByX());
        double xCorrector = bottomMost.getRadius() - bottomMost.getX();
        if (xCorrector < 0) {
            xCorrector = BubbleDrawer.INITIAL_AXIS_SHIFT;
        }
        g2d.scale(1, -1);
        g2d.translate(xCorrector, -bound.height + yCorrector);
        drawAxis(g, bound.height, bound.width);

        log.info(String.format("Scaled doc: %s", scaledDoc));
        for (final Bubble bubble : scaledDoc) {
            final Shape circle = new Ellipse2D.Double(bubble.getX()
                    - bubble.getRadius(), bubble.getY() - bubble.getRadius(),
                    bubble.getRadius() * 2, bubble.getRadius() * 2);
            g2d.fill(circle);
        }

    }

    protected void drawAxis(final Graphics g, final int height, final int width) {
        g.drawLine(0, 0, 0, height);
        g.drawLine(0, 0, width, 0);
    }

}
