import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UserForm extends JFrame implements ActionListener {

    JComboBox salutation;
    JTextField name, mail;
    JPasswordField pass;
    JRadioButton male, female;
    JCheckBox sports, movies, fiction, music;
    JTextArea result;

    UserForm() {

        setTitle("User Form");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Salutation"));
        salutation = new JComboBox(new String[]{"Mr", "Ms", "Mrs", "Master"});
        add(salutation);

        add(new JLabel("Name"));
        name = new JTextField();
        add(name);

        add(new JLabel("Gender"));
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup g = new ButtonGroup();
        g.add(male);
        g.add(female);
        JPanel gp = new JPanel();
        gp.add(male);
        gp.add(female);
        add(gp);

        add(new JLabel("Mail ID"));
        mail = new JTextField();
        add(mail);

        add(new JLabel("Password"));
        pass = new JPasswordField();
        add(pass);

        add(new JLabel("Interest"));
        sports = new JCheckBox("Sports");
        movies = new JCheckBox("Movies");
        fiction = new JCheckBox("Fictions");
        music = new JCheckBox("Music");
        JPanel ip = new JPanel();
        ip.add(sports);
        ip.add(movies);
        ip.add(fiction);
        ip.add(music);
        add(ip);

        JButton ok = new JButton("OK");
        ok.addActionListener(this);
        add(ok);

        result = new JTextArea();
        add(result);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String interest = "";
        if (sports.isSelected()) interest += "Sports ";
        if (movies.isSelected()) interest += "Movies ";
        if (fiction.isSelected()) interest += "Fictions ";
        if (music.isSelected()) interest += "Music ";

        result.setText(
            "Name: " + salutation.getSelectedItem() + " " + name.getText() +
            "\nGender: " + (male.isSelected() ? "Male" : "Female") +
            "\nMail: " + mail.getText() +
            "\nPassword: " + new String(pass.getPassword()) +
            "\nInterest: " + interest
        );
    }

    public static void main(String[] args) {
        new UserForm();
    }
}
