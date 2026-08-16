import javax.swing.*;
import javax.swing.event.*;

public class EditorPaneDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JEditorPane Demo");

        JEditorPane ep = new JEditorPane();
        ep.setContentType("text/html");
        ep.setText("<a href='https://example.com'>Click Me</a>");
        ep.setEditable(false);

        JLabel status = new JLabel("Status:");

        ep.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                status.setText("Clicked link: " + e.getURL());
        });

        f.add(new JScrollPane(ep), "Center");
        f.add(status, "South");
        f.setSize(350, 250);
        f.setVisible(true);
    }
}
