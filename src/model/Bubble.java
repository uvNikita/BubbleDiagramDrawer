package model;

import java.util.Comparator;

/**
 * @author Nikita Uvarov 
 * Class represents Bubble unit of bubble diagram.
 */
public class Bubble {
    /**
     * Number of Bubble properties.
     */
    public static final int NUMBER_OF_PROPERTIES = 3;

    /**
     * @author Nikita Uvarov
     * Class using to compare Bubble by value of Radius.
     */
    static class ComparatorByRadius implements Comparator<Bubble> {

        /*
         * (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getRadius() < b2.getRadius())
                return -1;
            if (b1.getRadius() > b2.getRadius())
                return 1;
            return 0;
        }
    }

    /**
     * @author Nikita Uvarov
     * Class using to compare Bubble by value of X coordinate.
     */
    static class ComparatorByX implements Comparator<Bubble> {

        /*
         * (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getX() < b2.getX())
                return -1;
            if (b1.getX() > b2.getX())
                return 1;
            return 0;
        }
    }

    /**
     * @author Nikita Uvarov
     * Class using to compare Bubble by value of Y coordinate.
     */
    static class ComparatorByY implements Comparator<Bubble> {

        /*
         * (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final Bubble b1, final Bubble b2) {
            if (b1.getY() < b2.getY())
                return -1;
            if (b1.getY() > b2.getY())
                return 1;
            return 0;
        }
    }

    /**
     * Get Bubble Comparator by Radius values.
     * @return Comparator compares by Radius property.
     */
    public static Comparator<Bubble> getComparatorByRadius() {
        return new ComparatorByRadius();
    }

    /**
     * Get Bubble Comparator by X values.
     * @return Comparator compares by X property.
     */
    public static Comparator<Bubble> getComparatorByX() {
        return new ComparatorByX();
    }

    /**
     * Get Bubble Comparator by Y values.
     * @return Comparator compares by Y property.
     */
    public static Comparator<Bubble> getComparatorByY() {
        return new ComparatorByY();
    }

    /**
     * X coordinate value of Bubble.
     */
    private double x;
    /**
     * Y coordinate value of Bubble.
     */
    private double y;

    /**
     * Radius of Bubble
     */
    private double radius;

    /**
     * Create Bubble using specified coordinates and radius.
     * @param x
     *        X coordinate of Bubble.
     * @param y
     *        Y coordinate of Bubble.
     * @param radius
     *        Radius of Bubble
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Bubble(%f, %f, %f)", x, y, radius);
    }

}
