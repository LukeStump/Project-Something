import org.dyn4j.dynamics.Settings;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    private static SimulationDisplayFrame frame;

    public static void main(String[] args) throws InterruptedException {
        World<Section> world = new World<>();
        setupWorldBallPit(world);
        Settings settings = new Settings();
        settings.setStepFrequency(1/120.0);
        world.setSettings(settings);

        Simulation simulation = new Simulation(world, new ArrayList<>());

        TransformCamera camera = new TransformCamera(simulation);

        camera.translate(0,2);

        frame = new SimulationDisplayFrame(simulation, camera);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Timer

        //Scanner reader = new Scanner(System.in);

        Thread.sleep(2000);
        frame.render();
        long time = System.currentTimeMillis();
        while (true) {
            long currTime = System.currentTimeMillis();
            double elapsedTime = (currTime-time)/1000.0;
            time = currTime;
            frame.update(elapsedTime);
            frame.render();
        }

//        Thread.sleep(2000);
//        long time = System.nanoTime();
//        long targetDT = 1000000000; // 120 tps
//        while (true) {
//            frame.step();
//            frame.render();
//            long currTime = System.nanoTime();
//            long dTime = (currTime-time);// /1000000.0;
//
//            if (dTime >= targetDT) System.out.println("Frame Overtime: " + (dTime-targetDT));
//            else Thread.sleep(dTime/1000000);
//
//        }

    }

    private static void setupWorldConcave(World<Section> world) {
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

        box.setLinearVelocity(0.0, 10.0);
        box.setAngularVelocity(-16.0);

        world.addBody(box);

        world.setGravity(new Vector2(0,-9.8));
    }

    private static void setupWorldBallPit(World<Section> world) {
        //Pit
        Section pit = new Section();
        {
            Convex c = Geometry.createRectangle(18.0,1.0);
            c.translate(new Vector2(0,-4.5));
            Piece p = new Piece(c);
            pit.addFixture(p);
        }
        {
            Convex c = Geometry.createRectangle(1.0,8.0);
            c.translate(new Vector2(8.5,0.0));
            Piece p = new Piece(c);
            pit.addFixture(p);
        }
        {
            Convex c = Geometry.createRectangle(1.0,8.0);
            c.translate(new Vector2(-8.5,0.0));
            Piece p = new Piece(c);
            pit.addFixture(p);
        }
        pit.setMass(MassType.INFINITE);
        world.addBody(pit);


        { //Balls
            final int WIDTH = 12, HEIGHT = 12;
            final double RADIUS = 0.5;
            final double SPACING = RADIUS*2;
            final Vector2 CENTER = new Vector2(0.0,4.0);

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    Section ball = new Section();
                    ball.addFixture(new Piece(Geometry.createCircle(RADIUS)));
                    ball.translate(CENTER);
                    ball.translate((x-WIDTH/2.0)*SPACING,(y-HEIGHT/2.0)*SPACING);
                    ball.setMass(MassType.NORMAL);
                    ball.setAngularVelocity((Math.random()-0.5)/32);
                    world.addBody(ball);
                }
            }
        }

        //"Person"

//        Section person = new Section();
//        Piece p = new Piece(Geometry.createCapsule(2.0,4.0));
//        p.setDensity(8);
//        person.addFixture(p);
//        person.translate(0.0,100.0);
//        person.setMass(MassType.NORMAL);
//        world.addBody(person);

    }
}
