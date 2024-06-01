import java.awt.geom.Area;

public class Section {
    private final Area boundingBox;
    private final Component[] components;

    public Section (Component[] components) {
        this.components = components;
        boundingBox = calcBoundingBox(components);
    }
    protected static Area calcBoundingBox(Component[] components) {
        Area boundingBox = new Area();
        for (Component c : components) {
            boundingBox.add(c.boundingBox);
        }
        return boundingBox;
    }
}
