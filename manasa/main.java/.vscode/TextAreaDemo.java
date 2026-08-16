import javax.swing.*;
import javax.swing.event.*;

public class TextAreaDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JTextArea Demo");
        JTextArea ta = new JTextArea(5, 20);
        JLabel status = new JLabel("Status:");

        ta.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { status.setText("Typing..."); }
            public void removeUpdate(DocumentEvent e) { status.setText("Removing..."); }
            public void changedUpdate(DocumentEvent e) {}
        });

        f.add(new JScrollPane(ta), "Center");
        f.add(status, "South");
        f.setSize(350, 250);
        f.setVisible(true);
    }
}
