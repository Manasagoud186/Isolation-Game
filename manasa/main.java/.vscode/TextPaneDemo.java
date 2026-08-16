import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextPaneDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JTextPane Demo");

        JTextPane tp = new JTextPane();
        JLabel status = new JLabel("Status:");

        tp.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { status.setText("Typing..."); }
            public void removeUpdate(DocumentEvent e) { status.setText("Deleting..."); }
            public void changedUpdate(DocumentEvent e) {}
        });

        JButton bold = new JButton("Bold");
        bold.addActionListener(e -> {
            StyledDocument doc = tp.getStyledDocument();
            Style style = tp.addStyle("Bold", null);
            StyleConstants.setBold(style, true);
            doc.setCharacterAttributes(tp.getSelectionStart(),
                    tp.getSelectionEnd() - tp.getSelectionStart(), style, false);
        });

        f.add(new JScrollPane(tp), "Center");
        f.add(bold, "North");
        f.add(status, "South");
        f.setSize(400, 300);
        f.setVisible(true);
    }
}
