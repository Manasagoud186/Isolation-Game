import javax.swing.*;

public class ListDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JList Demo");
        String[] data = {"Apple", "Banana", "Cherry"};
        JList list = new JList(data);
        JLabel status = new JLabel("Status:");

        list.addListSelectionListener(e ->
                status.setText("Selected: " + list.getSelectedValue())
        );

        f.add(new JScrollPane(list), "Center");
        f.add(status, "South");
        f.setSize(300, 250);
        f.setVisible(true);
    }
}
