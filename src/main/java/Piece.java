import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import java.awt.*;

public class Piece  extends BodyFixture {
    private double duribility;
    private Color color;

    public Piece(Convex shape) {
        super(shape);
        color = Color.WHITE;
    }

    //TODO add a method to be called during collisions

    public void render(Graphics2D g, double scale) {
        // get the shape on the fixture
        Convex convex = getShape();

        // render the fixture
        System.out.println("\tPiece Rendering");
        Graphics2DRenderer.render(g, convex, scale, color);
    }
}
