import javax.swing.*;
import java.awt.event.*;

public class LabelDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JLabel Demo");
        JLabel label = new JLabel("Click me!");
        JLabel status = new JLabel("Status:");

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                status.setText("Label clicked");
            }
        });

        f.add(label, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
