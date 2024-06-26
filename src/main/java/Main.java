import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        World<Section> world = new World<>();
        setupWorld(world);

        Simulation simulation = new Simulation(world, new ArrayList<>());

        Camera camera = new BasicCamera(simulation);

        SimulationDisplayFrame frame = new SimulationDisplayFrame(simulation, camera);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    private static void setupWorld(World<Section> world) {
        // Ground
        Section ground = new Section();
        ground.addFixture(new Piece(Geometry.createRectangle(15.0, 1.0)));
        ground.setMass(MassType.INFINITE);
        world.addBody(ground);

        // Concave
        Section table = new Section();
        {
            Convex c = Geometry.createRectangle(3.0, 1.0);
            c.translate(new Vector2(0.0, 0.5));
            Piece p = new Piece(c);
            table.addFixture(p);
        }
        {
            Convex c = Geometry.createRectangle(1.0, 1.0);
            c.translate(new Vector2(-1.0, -0.5));
            Piece p = new Piece(c);
            table.addFixture(p);
        }
        {
            Convex c = Geometry.createRectangle(1.0, 1.0);
            c.translate(new Vector2(1.0, -0.5));
            Piece p = new Piece(c);
            table.addFixture(p);
        }
        table.translate(new Vector2(0.0, 4.0));
        table.setMass(MassType.NORMAL);
        world.addBody(table);

        // Body3
        Section box = new Section();
        box.addFixture(new Piece(Geometry.createSquare(0.5)));
        box.translate(new Vector2(0.0, 1.0));
        box.setMass(MassType.NORMAL);
        world.addBody(box);
    }
}
