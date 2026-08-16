import javax.swing.*;
import java.awt.event.*;

public class ToggleDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JToggleButton Demo");
        JToggleButton toggle = new JToggleButton("OFF");
        JLabel status = new JLabel("Status:");

        toggle.addItemListener(e -> {
            if (toggle.isSelected())
                toggle.setText("ON");
            else
                toggle.setText("OFF");
            status.setText("Toggled: " + toggle.isSelected());
        });

        f.add(toggle, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
