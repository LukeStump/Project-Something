import org.dyn4j.world.World;
import org.dyn4j.world.listener.StepListener;
import org.dyn4j.world.listener.StepListenerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

}
