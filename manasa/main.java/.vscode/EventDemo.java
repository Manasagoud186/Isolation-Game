import javax.swing.*;
import java.awt.event.*;

public class EventDemo {
    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("Swing Event Handling Demo");

        // Create button and label
        JButton btn = new JButton("Click Me!");
        JLabel label = new JLabel("Button not clicked yet.");

        // Event Handling using ActionListener
        btn.addActionListener(new ActionListener() {
            int count = 0;
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Button clicked " + count + " times.");
            }
        });

        // Adding components to frame
        frame.add(btn, "North");
        frame.add(label, "South");

        // Frame settings
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
