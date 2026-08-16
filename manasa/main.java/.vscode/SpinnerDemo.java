import javax.swing.*;

public class SpinnerDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JSpinner Demo");

        JSpinner sp = new JSpinner(new SpinnerNumberModel(1, 0, 10, 1));
        JLabel status = new JLabel("Value: 1");

        sp.addChangeListener(e ->
                status.setText("Value: " + sp.getValue())
        );

        f.add(sp, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
