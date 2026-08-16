import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EduChatBotGUI_NoDB extends JFrame {

    JTextArea chatArea;
    JTextField inputField;

    public EduChatBotGUI_NoDB() {
        buildGUI();
    }

    // ---------- GUI ----------
    private void buildGUI() {
        setTitle("AI ChatBot for Education System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        JButton sendBtn = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout(10,10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendBtn, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendBtn.addActionListener(this::handleMessage);
        inputField.addActionListener(this::handleMessage);

        chatArea.append("EduBot: Hello! I am an AI ChatBot for Education System.\n");
        chatArea.append("EduBot: Ask about courses, exams, library, canteen, office, lab.\n\n");
    }

    // ---------- MESSAGE HANDLER ----------
    private void handleMessage(ActionEvent e) {
        String msg = inputField.getText().trim().toLowerCase();
        if (msg.isEmpty()) return;

        chatArea.append("You: " + msg + "\n");
        chatArea.append("EduBot: " + respond(msg) + "\n\n");
        inputField.setText("");
    }

    // ---------- CHATBOT LOGIC (NO DATABASE) ----------
    private String respond(String input) {

        if (input.contains("bye") || input.contains("exit")) {
            System.exit(0);
        }

        if (input.contains("hi") || input.contains("hello")) {
            return "Hello! How can I help you?";
        }

        if (input.contains("course") || input.contains("class") || input.contains("timetable")) {
            return """
Courses & Class Timetable:
CS101 - Programming → Mon & Wed : 9:00–10:30
MA201 - Discrete Mathematics → Tue & Thu : 11:00–12:30
DB301 - DBMS → Fri : 10:00–12:00
OS401 - Operating Systems → Mon & Thu : 2:00–3:30
""";
        }

        if (input.contains("exam")) {
            return """
Exam Timetable:
CS101 → 10-Apr-2025 (10:00–1:00)
MA201 → 12-Apr-2025 (10:00–1:00)
DB301 → 15-Apr-2025 (2:00–5:00)
OS401 → 18-Apr-2025 (2:00–5:00)
""";
        }

        if (input.contains("library")) {
            return """
Library Timings:
Mon–Fri : 8:00 AM – 8:00 PM
Sat : 9:00 AM – 5:00 PM
""";
        }

        if (input.contains("canteen")) {
            return """
Canteen Timings:
Breakfast : 8:00 – 10:00 AM
Lunch : 12:30 – 3:00 PM
Snacks : 4:00 – 6:00 PM
""";
        }

        if (input.contains("office")) {
            return "Office Timings:\nMon–Fri : 10:00 AM – 4:00 PM";
        }

        if (input.contains("lab")) {
            return "Lab Timings:\nMon–Sat : 9:00 AM – 7:00 PM";
        }

        if (input.contains("feedback")) {
            return "Thank you for your feedback! 😊";
        }

        return "Sorry, I didn't understand. Try asking about courses, exams, library, canteen, office, or lab.";
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EduChatBotGUI_NoDB().setVisible(true));
    }
}
