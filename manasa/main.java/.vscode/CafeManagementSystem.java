import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CafeManagementSystem extends JFrame {

    // ----- Inner class for Menu Items -----
    static class MenuItem {
        String name;
        String imagePath;
        int price;
        int quantity = 0;
        JLabel qtyLabel;

        MenuItem(String name, String imagePath, int price) {
            this.name = name;
            this.imagePath = imagePath;
            this.price = price;
        }
    }

    private List<MenuItem> menuItems = new ArrayList<>();
    private JTextArea receiptArea;
    private JRadioButton cashRadio, upiRadio, cardRadio;

    public CafeManagementSystem() {
        setTitle("☕ Cafe Management System");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Using absolute layout for simplicity

        // ---- Load Menu Items (same as Python list) ----
        addMenuItem("Coffee", "coffee.png", 50);
        addMenuItem("Tea", "tea.png", 30);
        addMenuItem("Juice", "juice.png", 60);
        addMenuItem("Pizza", "pizza.png", 150);
        addMenuItem("Burger", "burger.png", 120);
        addMenuItem("Sandwich", "sandwich.png", 80);
        addMenuItem("Pasta", "pasta.png", 100);
        addMenuItem("French Fries", "Frenchfries.png", 70);
        addMenuItem("Rolls", "Rolls.png", 70);
        addMenuItem("Shakes", "Shakes.png", 90);
        addMenuItem("Momos", "momos.png", 90);
        addMenuItem("Soup", "soup.png", 60);
        addMenuItem("Donut", "donut.png", 40);
        addMenuItem("Dessert", "dessert.png", 80);
        addMenuItem("Brownie", "brownie.png", 100);
        addMenuItem("Ice Cream", "icecream.png", 60);
        addMenuItem("Lassi", "lassi.png", 50);
        addMenuItem("Samosa", "samosa.png", 30);
        addMenuItem("Veg Puff", "vegpuff.png", 40);
        addMenuItem("Waffle", "waffle.png", 130);

        // ---- Title Label ----
        JLabel titleLabel = new JLabel("☕ Cafe Management System ☕", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(400, 20, 800, 50);
        add(titleLabel);

        // ---- Menu Panel (Left) ----
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBounds(50, 100, 750, 700);
        add(menuPanel);

        JLabel menuTitle = new JLabel("MENU", SwingConstants.CENTER);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 24));
        menuTitle.setOpaque(true);
        menuTitle.setBackground(Color.WHITE);
        menuPanel.add(menuTitle, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 items per row

        // Add menu item cards
        for (MenuItem item : menuItems) {
            itemsPanel.add(createItemCard(item));
        }

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        // ---- Receipt Panel (Right) ----
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(null);
        receiptPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        receiptPanel.setBackground(Color.BLACK);
        receiptPanel.setBounds(850, 100, 700, 700);
        add(receiptPanel);

        JLabel receiptTitle = new JLabel("RECEIPT", SwingConstants.CENTER);
        receiptTitle.setFont(new Font("Arial", Font.BOLD, 24));
        receiptTitle.setOpaque(true);
        receiptTitle.setBackground(Color.BLACK);
        receiptTitle.setForeground(Color.WHITE);
        receiptTitle.setBounds(200, 10, 300, 40);
        receiptPanel.add(receiptTitle);

        receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        receiptArea.setEditable(false);
        JScrollPane receiptScroll = new JScrollPane(receiptArea);
        receiptScroll.setBounds(50, 60, 600, 350);
        receiptPanel.add(receiptScroll);

        // ---- Payment Options ----
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new FlowLayout());
        paymentPanel.setBackground(Color.BLACK);
        paymentPanel.setForeground(Color.WHITE);
        paymentPanel.setBorder(new TitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Payment Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.WHITE
        ));
        paymentPanel.setBounds(50, 430, 600, 100);

        cashRadio = new JRadioButton("Cash", true);
        upiRadio = new JRadioButton("UPI");
        cardRadio = new JRadioButton("Card");

        cashRadio.setFont(new Font("Arial", Font.BOLD, 16));
        upiRadio.setFont(new Font("Arial", Font.BOLD, 16));
        cardRadio.setFont(new Font("Arial", Font.BOLD, 16));

        cashRadio.setBackground(Color.BLACK);
        upiRadio.setBackground(Color.BLACK);
        cardRadio.setBackground(Color.BLACK);

        cashRadio.setForeground(Color.WHITE);
        upiRadio.setForeground(Color.WHITE);
        cardRadio.setForeground(Color.WHITE);

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cashRadio);
        paymentGroup.add(upiRadio);
        paymentGroup.add(cardRadio);

        paymentPanel.add(cashRadio);
        paymentPanel.add(upiRadio);
        paymentPanel.add(cardRadio);

        receiptPanel.add(paymentPanel);

        // ---- Buttons: Generate Bill & Reset ----
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBounds(150, 550, 400, 80);

        JButton generateBtn = new JButton("Generate Bill");
        generateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        generateBtn.setBackground(Color.GREEN);
        generateBtn.setForeground(Color.BLACK);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(new Font("Arial", Font.BOLD, 16));
        resetBtn.setBackground(Color.RED);
        resetBtn.setForeground(Color.BLACK);

        btnPanel.add(generateBtn);
        btnPanel.add(resetBtn);
        receiptPanel.add(btnPanel);

        // ---- Button Actions ----
        generateBtn.addActionListener(e -> generateBill());
        resetBtn.addActionListener(e -> resetAll());
    }

    private void addMenuItem(String name, String imagePath, int price) {
        menuItems.add(new MenuItem(name, imagePath, price));
    }

    private JPanel createItemCard(MenuItem item) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Image
        JLabel imgLabel;
        File imgFile = new File(item.imagePath);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(item.imagePath);
            Image img = icon.getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(img));
        } else {
            imgLabel = new JLabel("No Image", SwingConstants.CENTER);
        }
        panel.add(imgLabel, BorderLayout.NORTH);

        // Name + Price
        JLabel nameLabel = new JLabel("<html><center>" + item.name + "<br>₹" + item.price + "</center></html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.CENTER);

        // Quantity & Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        item.qtyLabel = new JLabel("0", SwingConstants.CENTER);
        item.qtyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(item.qtyLabel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);

        JButton plusBtn = new JButton("＋");
        plusBtn.setFont(new Font("Arial", Font.BOLD, 16));
        JButton minusBtn = new JButton("－");
        minusBtn.setFont(new Font("Arial", Font.BOLD, 16));

        btnPanel.add(plusBtn);
        btnPanel.add(minusBtn);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        plusBtn.addActionListener(e -> {
            item.quantity++;
            item.qtyLabel.setText(String.valueOf(item.quantity));
        });

        minusBtn.addActionListener(e -> {
            if (item.quantity > 0) {
                item.quantity--;
                item.qtyLabel.setText(String.valueOf(item.quantity));
            }
        });

        return panel;
    }

    private void resetAll() {
        for (MenuItem item : menuItems) {
            item.quantity = 0;
            if (item.qtyLabel != null) {
                item.qtyLabel.setText("0");
            }
        }
        receiptArea.setText("");
        cashRadio.setSelected(true);
    }

    private void generateBill() {
        receiptArea.setText("");
        StringBuilder sb = new StringBuilder();

        sb.append("===== Cafe Receipt =====\n");
        String dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        sb.append("Date: ").append(dateTime).append("\n");
        sb.append("------------------------\n");

        int total = 0;
        for (MenuItem item : menuItems) {
            if (item.quantity > 0) {
                int price = item.quantity * item.price;
                sb.append(String.format("%s x%d = ₹%d\n", item.name, item.quantity, price));
                total += price;
            }
        }

        sb.append("------------------------\n");
        sb.append("Total: ₹").append(total).append("\n");

        String paymentMethod = "Cash";
        if (upiRadio.isSelected()) paymentMethod = "UPI";
        else if (cardRadio.isSelected()) paymentMethod = "Card";

        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("========================\n");
        sb.append("\nThank You! Visit Again ☕\n");

        receiptArea.setText(sb.toString());

        // If UPI selected – show a popup instead of QR
        if (upiRadio.isSelected()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pay ₹" + total + " using UPI.\n(UPI QR not implemented in Java demo.)",
                    "UPI Payment",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        // Run in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            CafeManagementSystem app = new CafeManagementSystem();
            app.setVisible(true);
        });
    }
}