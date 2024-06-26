import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

abstract class Camera {

    protected Simulation simulation;

    public BufferedImage render() {
        BufferedImage out = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = out.createGraphics();

        graphics.transform(getTransform());

        AABB cameraAABB = getCameraBounds().createAABB();

        for (Section section : simulation.getBodies()) {
            //if (cameraAABB.overlaps(section.createAABB())) // only drawing sections that could be in view
                section.render(graphics, getScale());
            //else System.out.println("Section out of camera bounds");
        }
        //graphics.drawString("Hello, World!", 100, 100);

        return out;
    }

    abstract int getWidth();
    abstract int getHeight();

    abstract double getScale();

    abstract AffineTransform getTransform();

    abstract Vector2 toWorldCoordinates(Point cameraPoint);
    abstract Point toCameraCoordinates(Vector2 worldVector);
    abstract Rectangle getCameraBounds();

    public Simulation getSimulation() {
        return simulation;
    }
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
