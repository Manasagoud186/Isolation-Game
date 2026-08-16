import javax.swing.*;
import javax.swing.tree.*;

public class TreeDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("JTree Demo");

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        root.add(new DefaultMutableTreeNode("Child 1"));
        root.add(new DefaultMutableTreeNode("Child 2"));

        JTree tree = new JTree(root);
        JLabel status = new JLabel("Status:");

        tree.addTreeSelectionListener(e ->
                status.setText("Selected: " + tree.getLastSelectedPathComponent())
        );

        f.add(new JScrollPane(tree), "Center");
        f.add(status, "South");
        f.setSize(300, 300);
        f.setVisible(true);
    }
}
