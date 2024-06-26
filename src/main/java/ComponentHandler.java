import org.dyn4j.dynamics.PhysicsBody;
import org.dyn4j.dynamics.TimeStep;
import org.dyn4j.world.PhysicsWorld;
import org.dyn4j.world.listener.StepListener;
import org.dyn4j.world.listener.StepListenerAdapter;

import java.util.List;

public class ComponentHandler extends StepListenerAdapter<Section> {

    List<Component> Components;

    public ComponentHandler(List<Component> components) {
        Components = components;
    }

    @Override
    public void begin(TimeStep step, PhysicsWorld world) {

    }

//    public void updatePerformed(TimeStep step, PhysicsWorld world) {}
//    public void postSolve(TimeStep step, PhysicsWorld world) {}
//    public void end(TimeStep step, PhysicsWorld world) {}
}
