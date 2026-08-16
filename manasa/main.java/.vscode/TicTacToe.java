import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] board = new JButton[3][3];
    private boolean player1Turn = true;
    private boolean gameOver = false;

    private JLabel status, scoreLabel, timerLabel;
    private JTextArea historyArea;

    private String player1Name, player2Name;
    private int player1Score = 0, player2Score = 0;

    private Timer turnTimer;
    private int timeLeft = 10;

    private boolean vsComputer = false;

    // Colors
    private final Color PEACH = new Color(255, 218, 185);
    private final Color LIGHT_GREEN = new Color(144, 238, 144);

    public TicTacToe() {
        chooseGameMode();   // ⭐ ADDED
        setupUI();
        startTimer();
    }

    // ---------------- GAME MODE ----------------
    private void chooseGameMode() {

        String[] options = {"Player vs Player", "Player vs Computer"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Select Game Mode",
                "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 1) {
            vsComputer = true;
            player1Name = JOptionPane.showInputDialog("Enter Your Name:");
            player2Name = "Computer";
        } else {
            player1Name = JOptionPane.showInputDialog("Enter Player 1 Name:");
            player2Name = JOptionPane.showInputDialog("Enter Player 2 Name:");
        }

        if (player1Name == null || player1Name.isEmpty()) player1Name = "Player 1";
        if (player2Name == null || player2Name.isEmpty()) player2Name = "Player 2";
    }

    // ---------------- UI SETUP ----------------
    private void setupUI() {
        setTitle("Tic Tac Toe");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        status = new JLabel(player1Name + " (X) Turn", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 18));
        status.setOpaque(true);
        status.setBackground(new Color(220, 230, 250));

        timerLabel = new JLabel("Time Left: 10 sec", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);

        topPanel.add(status);
        topPanel.add(timerLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 3, 8, 8));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font font = new Font("Arial", Font.BOLD, 50);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(font);
                board[i][j].setBackground(PEACH);
                board[i][j].setFocusPainted(false);
                board[i][j].addActionListener(this);
                grid.add(board[i][j]);
            }
        }

        add(grid, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));

        scoreLabel = new JLabel(getScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(200, 255, 200));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBorder(BorderFactory.createTitledBorder("Game History"));

        rightPanel.add(scoreLabel, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(historyArea), BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    // ---------------- TIMER ----------------
    private void startTimer() {
        turnTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " sec");

            if (timeLeft == 0) {
                JOptionPane.showMessageDialog(this, "Time Up! Turn Changed.");
                switchTurn();
            }
        });
        turnTimer.start();
    }

    private void resetTimer() {
        timeLeft = 10;
        timerLabel.setText("Time Left: 10 sec");
    }

    private void switchTurn() {
        player1Turn = !player1Turn;
        status.setText(player1Turn
                ? player1Name + " (X) Turn"
                : player2Name + " (O) Turn");
        resetTimer();
    }

    // ---------------- GAME LOGIC ----------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameOver) return;

        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return;

        if (player1Turn) {
            btn.setText("X");
            btn.setForeground(new Color(0, 0, 139));
        } else {
            btn.setText("O");
            btn.setForeground(new Color(139, 0, 0));
        }

        resetTimer();

        if (checkWinner()) {
            endGame(player1Turn ? player1Name : player2Name);
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "Game Draw! New game starts.");
            resetGame();
            return;
        }

        player1Turn = !player1Turn;
        status.setText(player1Turn
                ? player1Name + " (X) Turn"
                : player2Name + " (O) Turn");

        // ⭐ COMPUTER MOVE
        if (vsComputer && !player1Turn) {
            computerMove();
        }
    }

    // ---------------- COMPUTER ----------------
    private void computerMove() {

        Random r = new Random();
        int i, j;

        do {
            i = r.nextInt(3);
            j = r.nextInt(3);
        } while (!board[i][j].getText().equals(""));

        board[i][j].setText("O");
        board[i][j].setForeground(new Color(139, 0, 0));

        if (checkWinner()) {
            endGame("Computer");
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "Game Draw! New game starts.");
            resetGame();
            return;
        }

        player1Turn = true;
        status.setText(player1Name + " (X) Turn");
    }

    // ---------------- WIN CHECK ----------------
    private boolean checkWinner() {

        for (int i = 0; i < 3; i++) {
            if (same(board[i][0], board[i][1], board[i][2])) {
                colorWinning(board[i][0], board[i][1], board[i][2]);
                return true;
            }
            if (same(board[0][i], board[1][i], board[2][i])) {
                colorWinning(board[0][i], board[1][i], board[2][i]);
                return true;
            }
        }

        if (same(board[0][0], board[1][1], board[2][2])) {
            colorWinning(board[0][0], board[1][1], board[2][2]);
            return true;
        }

        if (same(board[0][2], board[1][1], board[2][0])) {
            colorWinning(board[0][2], board[1][1], board[2][0]);
            return true;
        }

        return false;
    }

    private void colorWinning(JButton b1, JButton b2, JButton b3) {

        for (JButton[] row : board)
            for (JButton b : row)
                b.setBackground(PEACH);

        if (player1Turn) {
            b1.setBackground(LIGHT_GREEN);
            b2.setBackground(LIGHT_GREEN);
            b3.setBackground(LIGHT_GREEN);
        }
    }

    private boolean same(JButton a, JButton b, JButton c) {
        return !a.getText().equals("") &&
               a.getText().equals(b.getText()) &&
               b.getText().equals(c.getText());
    }

    private boolean isDraw() {
        for (JButton[] row : board)
            for (JButton b : row)
                if (b.getText().equals("")) return false;
        return true;
    }

    // ---------------- END / RESET ----------------
    private void endGame(String winner) {
        gameOver = true;
        turnTimer.stop();
        updateScore();
        historyArea.append(winner + " won the game\n");
        JOptionPane.showMessageDialog(this, winner + " Wins!");
        resetGame();
    }

    private void resetGame() {
        for (JButton[] row : board)
            for (JButton b : row) {
                b.setText("");
                b.setBackground(PEACH);
            }
        gameOver = false;
        resetTimer();
        turnTimer.start();
        player1Turn = true;
        status.setText(player1Name + " (X) Turn");
    }

    private void updateScore() {
        if (player1Turn) player1Score++;
        else player2Score++;
        scoreLabel.setText(getScoreText());
    }

    private String getScoreText() {
        return "<html><center>Scoreboard<br>" +
                player1Name + ": " + player1Score + "<br>" +
                player2Name + ": " + player2Score + "</center></html>";
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
