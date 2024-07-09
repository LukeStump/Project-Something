import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.StepListener;
import org.dyn4j.world.listener.StepListenerAdapter;
import org.dyn4j.world.result.DetectResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    private final World<Section> world;

    public Simulation(World<Section> world, List<Component> components) {
        this.world = world; // change to a copy of the World to prevent tampering??
        ComponentHandler componentHandler = new ComponentHandler(components);
        this.world.addStepListener(componentHandler);
    }

    public List<Section> getBodies() {
        return world.getBodies();
    }



    public void step() { // One step in the simulation
        world.step(1);
    }

    public void update(double elapsedTime) {
        world.update(elapsedTime);
    }

    private Section getSectionAt(Vector2 point) {

        // detect sections near the point
        AABB aabb = new AABB(new Vector2(point.x, point.y), 0.0001);
        Iterator<DetectResult<Section, BodyFixture>> it = this.world.detectIterator(aabb, null);
        while (it.hasNext()) {
            Section section = it.next().getBody();

            // return the first section that contains the point
            if (section.contains(point)) {
                return section;
            }
        }

        // if no section contains the point
        return null;
    }

}
