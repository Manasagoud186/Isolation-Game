/*
 * PongSwingGame.java
 * A simple single-file Pong game using Java Swing.
 * Controls: W/S or Up/Down to move the left paddle (player).
 * The right paddle is controlled by a simple AI.
 * Compile: javac PongSwingGame.java
 * Run:     java PongSwingGame
 * Requires Java 8+
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongSwingGame extends JFrame {
    public PongSwingGame() {
        setTitle("Pong - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.startGame();
    }

    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new PongSwingGame());
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int PREF_W = 800;
    private final int PREF_H = 500;
    private final int PADDLE_W = 12;
    private final int PADDLE_H = 100;
    private final int BALL_SIZE = 16;

    private int playerY, aiY;
    private int playerScore = 0, aiScore = 0;

    private int ballX, ballY, ballVX = 5, ballVY = 3;

    private Timer timer;
    private boolean upPressed = false, downPressed = false;
    private boolean running = false;

    public GamePanel() {
        setPreferredSize(new Dimension(PREF_W, PREF_H));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        resetPositions();
        timer = new Timer(16, this); // ~60 FPS
    }

    public void startGame() {
        running = true;
        timer.start();
    }

    private void resetPositions() {
        playerY = (PREF_H - PADDLE_H) / 2;
        aiY = (PREF_H - PADDLE_H) / 2;
        ballX = (PREF_W - BALL_SIZE) / 2;
        ballY = (PREF_H - BALL_SIZE) / 2;
        // randomize initial direction
        ballVX = Math.random() < 0.5 ? 5 : -5;
        ballVY = (int) (Math.random() * 6 - 3);
        if (ballVY == 0) ballVY = 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // center dashed line
        g2.setColor(Color.GRAY);
        Stroke old = g2.getStroke();
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));
        g2.drawLine(PREF_W / 2, 0, PREF_W / 2, PREF_H);
        g2.setStroke(old);

        // paddles
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(20, playerY, PADDLE_W, PADDLE_H, 8, 8);
        g2.fillRoundRect(PREF_W - 20 - PADDLE_W, aiY, PADDLE_W, PADDLE_H, 8, 8);

        // ball
        g2.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // scores
        g2.setFont(new Font("Consolas", Font.BOLD, 36));
        String scoreStr = playerScore + "    " + aiScore;
        int sw = g2.getFontMetrics().stringWidth(scoreStr);
        g2.drawString(scoreStr, (PREF_W - sw) / 2, 50);

        // hint
        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g2.drawString("Controls: W/S or Up/Down to move", 10, PREF_H - 10);

        if (!running) {
            g2.setFont(new Font("SansSerif", Font.BOLD, 28));
            String msg = "Press SPACE to start";
            int mw = g2.getFontMetrics().stringWidth(msg);
            g2.drawString(msg, (PREF_W - mw) / 2, PREF_H / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return;

        // Player movement
        if (upPressed) playerY -= 6;
        if (downPressed) playerY += 6;
        playerY = clamp(playerY, 0, PREF_H - PADDLE_H);

        // Simple AI: move towards ball with limited speed
        int aiCenter = aiY + PADDLE_H / 2;
        int ballCenter = ballY + BALL_SIZE / 2;
        if (aiCenter < ballCenter - 10) aiY += 4;
        else if (aiCenter > ballCenter + 10) aiY -= 4;
        aiY = clamp(aiY, 0, PREF_H - PADDLE_H);

        // Move ball
        ballX += ballVX;
        ballY += ballVY;

        // Collision with top/bottom
        if (ballY <= 0 || ballY + BALL_SIZE >= PREF_H) {
            ballVY = -ballVY;
            ballY = clamp(ballY, 0, PREF_H - BALL_SIZE);
        }

        // Collision with player paddle
        Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
        Rectangle playerRect = new Rectangle(20, playerY, PADDLE_W, PADDLE_H);
        Rectangle aiRect = new Rectangle(PREF_W - 20 - PADDLE_W, aiY, PADDLE_W, PADDLE_H);

        if (ballRect.intersects(playerRect)) {
            // reflect and add some vertical speed depending on hit position
            ballVX = Math.abs(ballVX);
            int hitPos = (ballY + BALL_SIZE/2) - (playerY + PADDLE_H/2);
            ballVY = hitPos / 8;
            if (ballVY == 0) ballVY = (Math.random() < 0.5) ? 2 : -2;
        }

        if (ballRect.intersects(aiRect)) {
            ballVX = -Math.abs(ballVX);
            int hitPos = (ballY + BALL_SIZE/2) - (aiY + PADDLE_H/2);
            ballVY = hitPos / 8;
            if (ballVY == 0) ballVY = (Math.random() < 0.5) ? 2 : -2;
        }

        // Score detection
        if (ballX < 0) {
            aiScore++;
            resetPositions();
        } else if (ballX > PREF_W) {
            playerScore++;
            resetPositions();
        }

        repaint();
    }

    private int clamp(int v, int a, int b) {
        return Math.max(a, Math.min(b, v));
    }

    // KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) upPressed = true;
        if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) downPressed = true;
        if (k == KeyEvent.VK_SPACE) running = true;
        // R to reset scores and positions
        if (k == KeyEvent.VK_R) {
            playerScore = 0; aiScore = 0; resetPositions();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) upPressed = false;
        if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) downPressed = false;
    }
}
