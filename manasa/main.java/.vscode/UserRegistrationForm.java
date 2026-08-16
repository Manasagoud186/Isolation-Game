import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;

public class UserRegistrationForm extends JFrame implements ActionListener {

    JComboBox<String> salutation;
    JTextField name, email;
    JPasswordField password;
    JRadioButton male, female;
    JCheckBox sports, movies, fiction, music;
    JTextArea output;

    public UserRegistrationForm() {

        setTitle("Registration Form");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Salutation"));
        salutation = new JComboBox<>(new String[]{"Mr", "Ms", "Mrs", "Master"});
        add(salutation);

        add(new JLabel("Name"));
        name = new JTextField();
        add(name);

        add(new JLabel("Gender"));
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male); bg.add(female);
        JPanel gp = new JPanel();
        gp.add(male); gp.add(female);
        add(gp);

        add(new JLabel("Mail ID"));
        email = new JTextField();
        add(email);

        add(new JLabel("Password"));
        password = new JPasswordField();
        add(password);

        add(new JLabel("Interest"));
        sports = new JCheckBox("Sports");
        movies = new JCheckBox("Movies");
        fiction = new JCheckBox("Fictions");
        music = new JCheckBox("Music");
        JPanel ip = new JPanel();
        ip.add(sports); ip.add(movies); ip.add(fiction); ip.add(music);
        add(ip);

        JButton ok = new JButton("OK");
        ok.addActionListener(this);
        add(ok);

        output = new JTextArea();
        add(output);

        setVisible(true);
    }

    String encrypt(String p) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(p.getBytes());
            String s = "";
            for (byte x : b) s += String.format("%02x", x);
            return s;
        } catch (Exception e) { return "Error"; }
    }

    public void actionPerformed(ActionEvent e) {
        output.setText(
            "Name: " + salutation.getSelectedItem() + " " + name.getText() +
            "\nGender: " + (male.isSelected() ? "Male" : "Female") +
            "\nMail: " + email.getText() +
            "\nEncrypted Password: " + encrypt(new String(password.getPassword()))
        );
    }

    public static void main(String[] args) {
        new UserRegistrationForm();
    }
}
