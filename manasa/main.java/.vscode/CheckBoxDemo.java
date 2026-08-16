import javax.swing.*;
import java.awt.event.*;

public class CheckBoxDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JCheckBox Demo");
        JCheckBox cb = new JCheckBox("Accept?");
        JLabel status = new JLabel("Status:");

        cb.addItemListener(e ->
                status.setText("Checked: " + cb.isSelected())
        );

        f.add(cb, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
