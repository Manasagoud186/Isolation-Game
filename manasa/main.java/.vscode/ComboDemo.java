import javax.swing.*;

public class ComboDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JComboBox Demo");
        String[] items = {"Red", "Green", "Blue"};
        JComboBox cb = new JComboBox(items);
        JLabel status = new JLabel("Status:");

        cb.addActionListener(e ->
                status.setText("Selected: " + cb.getSelectedItem())
        );

        f.add(cb, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
