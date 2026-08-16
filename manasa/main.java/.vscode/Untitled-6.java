import javax.swing.*;

public class TextFieldDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JTextField Demo");
        JTextField tf = new JTextField(15);
        JLabel status = new JLabel("Status:");

        tf.addActionListener(e ->
                status.setText("Entered: " + tf.getText())
        );

        f.add(tf, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
