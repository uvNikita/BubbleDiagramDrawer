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

/**
 * @author Nikita Uvarov 
 * Drawer class to represent bubbles as bubbles diagram.
 */
public class BubbleDrawer implements Drawer {
    /**
     * Default value of maximum radius on diagram.
     */
    private static final int DEFAULT_MAX_RADIUS = 50;
    /**
     * Initial shift of axis when panel has been drawed.
     */
    private static final int INITIAL_AXIS_SHIFT = 5;
    private final Logger log = Logger.getLogger(BubbleDrawer.class.getName());
    /**
     * Container of data to visualize.
     */
    private final DocumentHolder holder;

    /**
     * Create BubbleDrawer using specific data holder.
     * @param holder
     *        Container of data to visualize.
     */
    public BubbleDrawer(final DocumentHolder holder) {
        this.holder = holder;
    }

    /*
     * (non-Javadoc)
     * @see bubbleDrawer.Drawer#draw(java.awt.Graphics)
     */
    @Override
    public final void draw(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final List<Bubble> doc = holder.getCurrentDocument();
        // Check whether the data is empty. And draw nothing if it is.
        if (doc.isEmpty())
            return;
        final List<Bubble> scaledDoc = new ArrayList<Bubble>();

        // Get rectangle representing the borders of allowed to draw area.
        final Rectangle bound = new Rectangle();
        g2d.getClipBounds(bound);

        // Find maximum allowed height and width. Take into account max allowed
        // radius.
        final int maxHeight = bound.height - BubbleDrawer.DEFAULT_MAX_RADIUS
                - BubbleDrawer.INITIAL_AXIS_SHIFT;
        final int maxWidth = bound.width - BubbleDrawer.DEFAULT_MAX_RADIUS
                - BubbleDrawer.INITIAL_AXIS_SHIFT;

        // Find max X and Y values of current data.
        final double maxX = Collections.max(doc, Bubble.getComparatorByX())
                .getX();
        final double maxY = Collections.max(doc, Bubble.getComparatorByY())
                .getY();
        final double maxRadius = Collections.max(doc,
                Bubble.getComparatorByRadius()).getRadius();

        // Scale all current data bubbles.
        for (final Bubble bubble : doc) {
            scaledDoc.add(new Bubble(bubble.getX() / maxX * maxWidth, bubble
                    .getY() / maxY * maxHeight, bubble.getRadius() / maxRadius
                    * BubbleDrawer.DEFAULT_MAX_RADIUS));
        }
        // Find bottommost and leftmost bubbles to correct axis shift.
        final Bubble bottomMost = Collections.min(scaledDoc,
                Bubble.getComparatorByY());
        double yCorrector = bottomMost.getRadius() - bottomMost.getY();
        if (yCorrector < 0) {
            yCorrector = BubbleDrawer.INITIAL_AXIS_SHIFT;
        }
        final Bubble leftMost = Collections.min(scaledDoc,
                Bubble.getComparatorByX());
        double xCorrector = leftMost.getRadius() - leftMost.getX();
        if (xCorrector < 0) {
            xCorrector = BubbleDrawer.INITIAL_AXIS_SHIFT;
        }
        g2d.scale(1, -1);
        g2d.translate(xCorrector, -bound.height + yCorrector);
        drawAxis(g, bound.height, bound.width);

        // Create and draw Bubbles.
        log.info(String.format("Scaled doc: %s", scaledDoc));
        for (final Bubble bubble : scaledDoc) {
            final Shape circle = new Ellipse2D.Double(bubble.getX()
                    - bubble.getRadius(), bubble.getY() - bubble.getRadius(),
                    bubble.getRadius() * 2, bubble.getRadius() * 2);
            g2d.fill(circle);
        }

    }

    /**
     * Draws axis on Graphics using specified height and width.
     * @param g
     *        Graphics to draw axis on.
     * @param yLength
     *        Length of y axis.
     * @param xLength
     *        Length of x axis. length
     */
    protected void drawAxis(final Graphics g, final int yLength,
            final int xLength) {
        g.drawLine(0, 0, 0, yLength);
        g.drawLine(0, 0, xLength, 0);
    }

}
