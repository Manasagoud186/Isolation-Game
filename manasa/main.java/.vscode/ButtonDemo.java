import javax.swing.*;
import java.awt.event.*;

public class ButtonDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JButton Demo");
        JButton btn = new JButton("Click Me");
        JLabel status = new JLabel("Status:");

        btn.addActionListener(e -> status.setText("Button clicked"));

        f.add(btn, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
