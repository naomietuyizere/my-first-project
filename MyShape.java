import java.awt.*;
import java.awt.event.*;

public class MyShape extends Frame {

    // Constructor
    public MyShape() {
        setTitle("My Shape"); // Title at the top of the window
        setSize(400, 400);    // Window size
        setBackground(Color.white);
        setVisible(true);

        // Close window when 'X' is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    // Paint method to draw outlined smiley face
    public void paint(Graphics g) {
        // Draw face outline (not filled)
        g.setColor(Color.YELLOW);
        g.drawOval(100, 100, 200, 200);

        // Draw eyes
        g.setColor(Color.BLACK);
        g.fillOval(150, 160, 20, 30);  // Left eye
        g.fillOval(230, 160, 20, 30);  // Right eye

        // Draw smiling mouth (outline only)
        g.setColor(Color.RED);
        g.drawArc(150, 210, 100, 60, 180, 180);

        // Optional caption text (removed emoji to avoid box)
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("", 120, 330);
    }

    // Main method
    public static void main(String[] args) {
        new MyShape();
    }
}
