import javax.swing.*;
import java.awt.*;

/**
 * Demonstrates BorderLayout: five regions (NORTH, SOUTH, EAST, WEST, CENTER).
 */
public class BorderLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BorderLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            frame.setLayout(new BorderLayout(8, 8)); // gaps between components

            frame.add(new JButton("North"), BorderLayout.NORTH);
            frame.add(new JButton("South"), BorderLayout.SOUTH);
            frame.add(new JButton("East"), BorderLayout.EAST);
            frame.add(new JButton("West"), BorderLayout.WEST);

            // Center gets a larger panel
            JPanel center = new JPanel();
            center.add(new JLabel("<html><center>Center Area<br>Usually grows to fill space</center></html>"));
            frame.add(center, BorderLayout.CENTER);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
