import javax.swing.*;
import java.text.*;

public class FormattedFieldDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JFormattedTextField Demo");
        JFormattedTextField ft = new JFormattedTextField(DateFormat.getDateInstance());
        ft.setValue(new java.util.Date());
        JLabel status = new JLabel("Status:");

        ft.addActionListener(e ->
                status.setText("Date: " + ft.getText())
        );

        f.add(ft, "North");
        f.add(status, "South");
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
