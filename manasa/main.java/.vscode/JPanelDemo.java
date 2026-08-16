import javax.swing.*;
import java.awt.*;

public class JPanelDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JPanel Demo");
        JPanel panel = new JPanel();
        JLabel status = new JLabel("Status: Click button");

        JButton btn = new JButton("Click Me");
        btn.addActionListener(e -> status.setText("Button clicked inside JPanel"));

        panel.add(btn);

        f.add(panel, BorderLayout.CENTER);
        f.add(status, BorderLayout.SOUTH);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}