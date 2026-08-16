import javax.swing.*;

public class ProgressDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JProgressBar Demo");

        JProgressBar pb = new JProgressBar(0, 100);
        JButton btn = new JButton("Start");

        btn.addActionListener(e -> {
            for (int i = 0; i <= 100; i += 10) {
                pb.setValue(i);
                try { Thread.sleep(200); } catch (Exception ex) {}
            }
        });

        f.add(pb, "North");
        f.add(btn, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
