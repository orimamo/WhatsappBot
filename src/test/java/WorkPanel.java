import org.checkerframework.checker.units.qual.C;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WorkPanel extends JPanel {

    private ImageIcon background;

    public WorkPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setRequestFocusEnabled(true);
        this.background = new ImageIcon("whatsapp2.jpeg");


    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.background.paintIcon(this, graphics, 0, 0);
    }
}
