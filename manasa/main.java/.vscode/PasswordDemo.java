import javax.swing.*;

public class PasswordDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JPasswordField Demo");
        JPasswordField pf = new JPasswordField(15);
        JLabel status = new JLabel("Status:");

        pf.addActionListener(e ->
                status.setText("Password length: " + pf.getPassword().length)
        );

        f.add(pf, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
