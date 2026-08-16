import javax.swing.*;
import java.awt.event.*;

public class MenuDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("Menu Demo");
        JLabel status = new JLabel("Status: Choose menu item");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.addActionListener(e -> status.setText("Open clicked"));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        f.setJMenuBar(menuBar);
        f.add(status);

        f.setSize(300, 200);
        f.setVisible(true);
    }
}
