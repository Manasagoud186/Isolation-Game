import javax.swing.*;
import java.awt.event.*;

public class RadioDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JRadioButton Demo");
        JRadioButton r1 = new JRadioButton("Male");
        JRadioButton r2 = new JRadioButton("Female");
        JLabel status = new JLabel("Status:");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        r1.addActionListener(e -> status.setText("Selected: Male"));
        r2.addActionListener(e -> status.setText("Selected: Female"));

        JPanel p = new JPanel();
        p.add(r1);
        p.add(r2);

        f.add(p, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
