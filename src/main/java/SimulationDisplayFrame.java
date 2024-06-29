import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.image.BufferedImage;

public class SimulationDisplayFrame extends JFrame {
    private Camera camera;
    private Simulation simulation;
    private JLabel display = new JLabel();

    public SimulationDisplayFrame(Simulation simulation, Camera camera) {
        this.camera = camera;
        this.simulation = simulation;
        setSize(camera.getWidth(),camera.getHeight());
        this.add(display);
        displayFrame(camera.render());
    }

    public void render() {
        displayFrame(camera.render());
    }
    public void step() {
        simulation.step();
        render();
    }

    private void displayFrame(BufferedImage frame) {
        display.setIcon(new ImageIcon(frame));
    }


}
