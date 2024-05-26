import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

abstract class Component {
    final Area boundingBox; // Relative to Section
    public Component(Area boundingBox) {
        this.boundingBox = boundingBox;
    }
}
