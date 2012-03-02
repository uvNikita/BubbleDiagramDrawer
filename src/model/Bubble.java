package model;

/**
 * @author nikita
 */
public class Bubble {
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

    public String toString() {
        return String.format("Bubble(%f, %f, %f)", x, y, radius);
    }

    /**
     * Get current value of x.
     * @return the x
     */
    public final double getX() {
        return x;
    }

    /**
     * Set new value of x.
     * @param x the x to set
     */
    public final void setX(double x) {
        this.x = x;
    }

    /**
     * Get current value of y.
     * @return the y
     */
    public final double getY() {
        return y;
    }

    /**
     * Set new value of y.
     * @param y the y to set
     */
    public final void setY(double y) {
        this.y = y;
    }

    /**
     * Get current value of radius.
     * @return the radius
     */
    public final double getRadius() {
        return radius;
    }

    /**
     * Set new value of radius.
     * @param radius the radius to set
     */
    public final void setRadius(double radius) {
        this.radius = radius;
    }

}
