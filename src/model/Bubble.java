package model;

import java.util.Comparator;

/**
 * @author nikita
 */
public class Bubble {
    public static final int NUMBER_OF_PROPERTIES = 3;

    static class ComparatorByRadius implements Comparator<Bubble> {

        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getRadius() < b2.getRadius())
                return -1;
            if (b1.getRadius() > b2.getRadius())
                return 1;
            return 0;
        }
    }

    static class ComparatorByX implements Comparator<Bubble> {

        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getX() < b2.getX())
                return -1;
            if (b1.getX() > b2.getX())
                return 1;
            return 0;
        }
    }

    static class ComparatorByY implements Comparator<Bubble> {

        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getY() < b2.getY())
                return -1;
            if (b1.getY() > b2.getY())
                return 1;
            return 0;
        }
    }

    public static Comparator<Bubble> getComparatorByRadius() {
        return new ComparatorByRadius();
    }

    public static Comparator<Bubble> getComparatorByX() {
        return new ComparatorByX();
    }

    public static Comparator<Bubble> getComparatorByY() {
        return new ComparatorByY();
    }

    private double x;

    private double y;

    private double radius;

    /**
     * @param x
     * @param y
     * @param radius
     */
    public Bubble(final double x, final double y, final double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Get current value of radius.
     * @return the radius
     */
    public final double getRadius() {
        return radius;
    }

    /**
     * Get current value of x.
     * @return the x
     */
    public final double getX() {
        return x;
    }

    /**
     * Get current value of y.
     * @return the y
     */
    public final double getY() {
        return y;
    }

    /**
     * Set new value of radius.
     * @param radius
     *        the radius to set
     */
    public final void setRadius(final double radius) {
        this.radius = radius;
    }

    /**
     * Set new value of x.
     * @param x
     *        the x to set
     */
    public final void setX(final double x) {
        this.x = x;
    }

    /**
     * Set new value of y.
     * @param y
     *        the y to set
     */
    public final void setY(final double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Bubble(%f, %f, %f)", x, y, radius);
    }

}
