import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class layoutbutton
{
    public static void main(String args[])
    {
        JFrame frame = new JFrame("Flow Layout Demo");
        frame.setLayout(new FlowLayout());

        JButton btn1 = new JButton("First");

        // Add Action Listener
        
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");
            }
        });
        

        JButton btn2 = new JButton("Second");

        // Add Action Listener
        
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");
            }
        });

        JButton btn3 = new JButton("Third");
        // Add Action Listener
        
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand()+"clicked");
            }
        });
        JButton btn4 = new JButton("Fourth");
        // Add Action Listener
        
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");
            }
        });
        JButton btn5 = new JButton("Fifth");
        // Add Action Listener
        
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button is pressed");
            }
        });

        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
        frame.add(btn5);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}