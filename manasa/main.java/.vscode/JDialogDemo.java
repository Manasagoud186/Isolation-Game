import javax.swing.*;
import java.awt.*;

public class JDialogDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JDialog Demo");
        JButton btn = new JButton("Open Dialog");

        btn.addActionListener(e -> {
            JDialog dialog = new JDialog(f, "My Dialog", true);
            dialog.setSize(200, 150);
            dialog.add(new JLabel("This is a dialog!", SwingConstants.CENTER));
            dialog.setLocationRelativeTo(f);
            dialog.setVisible(true);
        });

        f.add(btn);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
