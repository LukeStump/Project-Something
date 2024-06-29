import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TransformCamera extends Camera {

    /** The transform that  **/
    private Transform transform;

    /** The width and height of the resulting image **/
    private int width, height;

    /** The scale (zoom) in pixels per meter */
    private double scale;

    public TransformCamera(Simulation simulation) {
        this.transform = new Transform();
        this.width = 512;
        this.height = 512;
        this.scale = 20.0;
        setSimulation(simulation);
    }

    public TransformCamera(Transform transform, double scale, int width, int height, Simulation simulation) {
        this.transform = transform;
        this.width = width;
        this.height = height;
        this.scale = scale;
        setSimulation(simulation);
    }

    @Override
    public BufferedImage render() {
        BufferedImage out = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = out.createGraphics();

        graphics.transform(getAffineTransform());



        for (Section section : simulation.getBodies()) {
                section.render(graphics, scale);
        }
        //graphics.drawString("Hello, World!", 100, 100);

        return out;
        //return null;
    }

    protected AffineTransform getAffineTransform() {
        //TODO: check if this is actually equivlent
        AffineTransform out = new AffineTransform();
        out.translate(transform.getTranslationX(), transform.getTranslationY());

        //put the origin at the center of the image instead of the corner
        out.translate(width/2.0,height/2.0);

        out.rotate(transform.getRotation().toRadians());
        out.scale(1.0,-1.0);
        //out.scale(scale,scale);
        return out;
    }

    @Override
    int getWidth() {
        return width;
    }

    @Override
    int getHeight() {
        return height;
    }

    @Override
    Vector2 toWorldCoordinates(Point cameraPoint) {
        return null;
    }

    @Override
    Point toCameraCoordinates(Vector2 worldVector) {
        return null;
    }
}
