import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BasicCamera extends Camera {
    /** The scale (zoom) in pixels per meter */
    private double scale;

    /** The center of the camera in the world */
    private Vector2 cameraOrigin;

    /** The width and height of the resulting image **/
    private int width, height;

    private final static int dtl = 32;
    public BasicCamera(Simulation simulation) {
        this(24, new Vector2(dtl,dtl), 512, 512, simulation);
    }



    public BasicCamera(double scale, Vector2 cameraOrigin, int width, int height, Simulation simulation) {
        this.scale = scale;
        this.cameraOrigin = cameraOrigin;
        this.width = width;
        this.height = height;
        this.simulation = simulation;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public double getScale() {
        return scale;
    }

    @Override
    AffineTransform getTransform() {
        AffineTransform out = new AffineTransform();
        out.translate(cameraOrigin.x,cameraOrigin.y);
        return out;
    }

    @Override
    public Vector2 toWorldCoordinates(Point cameraPoint) {
        if (cameraPoint == null) return null;

        Vector2 out = new Vector2();

        out.x = (cameraPoint.x - getWidth()/2.0)/scale;
        out.y = (getHeight()/2.0 - cameraPoint.y)/scale; // y axis is reversed

        out.add(cameraOrigin);

        return out;
    }

    @Override
    public Point toCameraCoordinates(Vector2 worldVector) {
        Vector2 v = new Vector2(cameraOrigin, worldVector); // subtract camera origin

        Point out = new Point();

        out.x = (int)(v.x*scale + getWidth()/2.0);
        out.y = (int)(getHeight()/2.0 - v.y*scale);

        return out;
    }

    @Override
    public Rectangle getCameraBounds() {
        Rectangle out = new Rectangle(width/scale, height/scale);
        out.translate(cameraOrigin);
        return out;
    }
}
