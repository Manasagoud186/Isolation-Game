import javax.swing.*;

public class JScrollPaneDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JScrollPane Demo");

        JTextArea area = new JTextArea(10, 30);
        area.setText("Type here...\nScroll down...");

        JScrollPane sp = new JScrollPane(area);

        f.add(sp);
        f.setSize(300, 250);
        f.setVisible(true);
    }
}
