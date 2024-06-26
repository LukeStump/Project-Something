import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Section extends Body {

    public void render(Graphics2D g, double scale) {
        // point radius
        final int pr = 4;

        // save the original transform
        AffineTransform ot = g.getTransform();

        // transform the coordinate system from world coordinates to local coordinates
        AffineTransform lt = new AffineTransform();
        lt.translate(this.transform.getTranslationX() * scale, this.transform.getTranslationY() * scale);
        lt.rotate(this.transform.getRotationAngle());

        // apply the transform
        g.transform(lt);

        // loop over all the body fixtures for this body
        System.out.println("Section Rendering");
        for (BodyFixture fixture : this.fixtures) {
            if (fixture instanceof Piece) ((Piece)fixture).render(g, scale);
            else System.out.println("\tNon-Piece Fixture");
        }
        /*
        // draw a center point
        Ellipse2D.Double ce = new Ellipse2D.Double(
                this.getLocalCenter().x * scale - pr * 0.5,
                this.getLocalCenter().y * scale - pr * 0.5,
                pr,
                pr);
        g.setColor(Color.WHITE);
        g.fill(ce);
        g.setColor(Color.DARK_GRAY);
        g.draw(ce);
        */

        // set the original transform
        g.setTransform(ot);
    }
}
