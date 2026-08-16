import javax.swing.*;
import java.awt.*;

public class JColorChooserDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JColorChooser Demo");
        JButton btn = new JButton("Pick Color");
        JPanel panel = new JPanel();

        btn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(f, "Choose a Color", Color.WHITE);
            if (c != null) panel.setBackground(c);
        });

        f.add(btn, "North");
        f.add(panel, "Center");

        f.setSize(300, 250);
        f.setVisible(true);
    }
}
