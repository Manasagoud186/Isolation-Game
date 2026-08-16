import javax.swing.*;
import java.io.File;

public class JFileChooserDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JFileChooser Demo");
        JButton btn = new JButton("Choose File");

        btn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(f);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(f, "Selected: " + selected.getAbsolutePath());
            }
        });

        f.add(btn);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
