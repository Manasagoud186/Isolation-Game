import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;

/**
 * AllComponentsDemo
 * Demonstrates event handling for many Swing components.
 * Each tab contains one component and a status label that updates on events.
 */
public class AllComponentsDemo extends JFrame {

    public AllComponentsDemo() {
        super("Swing Components — Event Handling Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(920, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("JLabel", labelTab());
        tabs.addTab("JButton", buttonTab());
        tabs.addTab("JToggleButton", toggleTab());
        tabs.addTab("JCheckBox", checkBoxTab());
        tabs.addTab("JRadioButton", radioTab());
        tabs.addTab("JTextField", textFieldTab());
        tabs.addTab("JPasswordField", passwordTab());
        tabs.addTab("JTextArea", textAreaTab());
        tabs.addTab("JFormattedTextField", formattedTab());
        tabs.addTab("JComboBox", comboTab());
        tabs.addTab("JList", listTab());
        tabs.addTab("JTable", tableTab());
        tabs.addTab("JTree", treeTab());
        tabs.addTab("JSlider", sliderTab());
        tabs.addTab("JProgressBar", progressTab());
        tabs.addTab("JSpinner", spinnerTab());
        tabs.addTab("JEditorPane", editorPaneTab());
        tabs.addTab("JTextPane", textPaneTab());

        add(tabs);
    }

    private JPanel wrap(Component main, JLabel status) {
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(main, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JSeparator(), BorderLayout.NORTH);
        bottom.add(status, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    // JLabel (Mouse events)
    private JPanel labelTab() {
        JLabel lbl = new JLabel("<html><center><b>Click / hover / right-click here</b></center></html>", SwingConstants.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(16f));
        JLabel status = new JLabel("Status: waiting for mouse events...");
        lbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                status.setText("Clicked: button=" + e.getButton() + ", clicks=" + e.getClickCount());
            }
            public void mouseEntered(MouseEvent e) { status.setText("Mouse entered label"); }
            public void mouseExited(MouseEvent e) { status.setText("Mouse exited label"); }
            public void mousePressed(MouseEvent e) { if (SwingUtilities.isRightMouseButton(e)) status.setText("Right-clicked"); }
        });
        return wrap(lbl, status);
    }

    // JButton (Action)
    private JPanel buttonTab() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btn = new JButton("Press Me");
        JLabel status = new JLabel("Status: nothing yet");
        btn.addActionListener(e -> status.setText("Button pressed at " + System.currentTimeMillis()));
        p.add(btn);
        p.add(status);
        return wrap(p, status);
    }

    // JToggleButton (Item)
    private JPanel toggleTab() {
        JToggleButton tog = new JToggleButton("OFF");
        JLabel status = new JLabel("Status: OFF");
        tog.addItemListener(e -> {
            boolean sel = tog.isSelected();
            tog.setText(sel ? "ON" : "OFF");
            status.setText("Toggle is " + (sel ? "ON" : "OFF"));
        });
        return wrap(tog, status);
    }

    // JCheckBox (Item)
    private JPanel checkBoxTab() {
        JCheckBox cb = new JCheckBox("I agree");
        JLabel status = new JLabel("Status: unchecked");
        cb.addItemListener(e -> status.setText("Checkbox: " + (cb.isSelected() ? "checked" : "unchecked")));
        return wrap(cb, status);
    }

    // JRadioButton (Action, grouped)
    private JPanel radioTab() {
        JPanel p = new JPanel(new GridLayout(0,1,4,4));
        JRadioButton r1 = new JRadioButton("Option 1");
        JRadioButton r2 = new JRadioButton("Option 2");
        JRadioButton r3 = new JRadioButton("Option 3");
        ButtonGroup bg = new ButtonGroup(); bg.add(r1); bg.add(r2); bg.add(r3);
        JLabel status = new JLabel("Status: none selected");
        ActionListener al = e -> status.setText("Selected: " + ((JRadioButton)e.getSource()).getText());
        r1.addActionListener(al); r2.addActionListener(al); r3.addActionListener(al);
        p.add(r1); p.add(r2); p.add(r3);
        return wrap(p, status);
    }

    // JTextField (Action + Document)
    private JPanel textFieldTab() {
        JPanel p = new JPanel(new BorderLayout(6,6));
        JTextField tf = new JTextField(20);
        JLabel status = new JLabel("Status: type and press Enter");
        tf.addActionListener(e -> status.setText("Enter pressed: " + tf.getText()));
        tf.getDocument().addDocumentListener(new DocumentListener(){
            void upd(){ status.setText("Typing... len=" + tf.getText().length()); }
            public void insertUpdate(DocumentEvent e){ upd();}
            public void removeUpdate(DocumentEvent e){ upd();}
            public void changedUpdate(DocumentEvent e){}
        });
        p.add(new JLabel("Enter text then press Enter"), BorderLayout.NORTH);
        p.add(tf, BorderLayout.CENTER);
        return wrap(p, status);
    }

    // JPasswordField (Action)
    private JPanel passwordTab() {
        JPasswordField pf = new JPasswordField(20);
        JLabel status = new JLabel("Status: enter password and press Enter");
        pf.addActionListener(e -> {
            char[] pwd = pf.getPassword();
            status.setText("Password length: " + pwd.length);
            java.util.Arrays.fill(pwd,'\0');
        });
        return wrap(pf, status);
    }

    // JTextArea (Document + Key binding)
    private JPanel textAreaTab() {
        JTextArea ta = new JTextArea(8,40);
        ta.setLineWrap(true); ta.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(ta);
        JLabel status = new JLabel("Status: editing...");
        ta.getDocument().addDocumentListener(new DocumentListener(){
            void upd(){ status.setText("Text length = " + ta.getText().length()); }
            public void insertUpdate(DocumentEvent e){ upd(); }
            public void removeUpdate(DocumentEvent e){ upd(); }
            public void changedUpdate(DocumentEvent e){}
        });
        // Ctrl+Enter to "submit"
        ta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), "submit");
        ta.getActionMap().put("submit", new AbstractAction(){ public void actionPerformed(ActionEvent e){ status.setText("Submitted: length=" + ta.getText().length()); }});
        return wrap(sp, status);
    }

    // JFormattedTextField (Action)
    private JPanel formattedTab() {
        JFormattedTextField ftf = new JFormattedTextField(DateFormat.getDateInstance());
        ftf.setColumns(12);
        ftf.setValue(new java.util.Date());
        JLabel status = new JLabel("Status: edit date and press Enter");
        ftf.addActionListener(e -> status.setText("Formatted field: " + ftf.getText()));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Date:"));
        p.add(ftf);
        return wrap(p, status);
    }

    // JComboBox (Action)
    private JPanel comboTab() {
        String[] items = {"Red","Green","Blue","Yellow"};
        JComboBox<String> combo = new JComboBox<>(items);
        JLabel status = new JLabel("Status: none selected");
        combo.addActionListener(e -> status.setText("Selected: " + combo.getSelectedItem()));
        return wrap(combo, status);
    }

    // JList (Selection)
    private JPanel listTab() {
        String[] fruits = {"Apple","Banana","Cherry","Date","Elderberry"};
        JList<String> list = new JList<>(fruits);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(list);
        JLabel status = new JLabel("Status: none selected");
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String s = list.getSelectedValue();
                status.setText("Selected: " + (s==null ? "none" : s));
            }
        });
        return wrap(sp, status);
    }

    // JTable (Selection + double-click)
    private JPanel tableTab() {
        String[] cols = {"ID","Name"};
        Object[][] data = {{"1","Manasa"},{"2","Rahul"},{"3","Priya"}};
        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        JLabel status = new JLabel("Status: select a row");
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int r = table.getSelectedRow();
                if (r >= 0) status.setText("Selected row " + r + ": " + table.getValueAt(r,1));
                else status.setText("Selection cleared");
            }
        });
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2) {
                    int r = table.rowAtPoint(e.getPoint());
                    if (r >= 0) {
                        status.setText("Double-clicked row " + r + " - editing");
                        table.editCellAt(r,1);
                    }
                }
            }
        });
        // small control to add a row
        JButton add = new JButton("Add Row");
        add.addActionListener(e -> {
            int id = model.getRowCount() + 1;
            model.addRow(new Object[]{String.valueOf(id), "New" + id});
            status.setText("Added row " + id);
        });
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(sp, BorderLayout.CENTER);
        wrapper.add(add, BorderLayout.SOUTH);
        return wrap(wrapper, status);
    }

    // JTree (Selection)
    private JPanel treeTab() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode india = new DefaultMutableTreeNode("India");
        india.add(new DefaultMutableTreeNode("Delhi"));
        india.add(new DefaultMutableTreeNode("Mumbai"));
        root.add(india);
        root.add(new DefaultMutableTreeNode("USA"));
        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane sp = new JScrollPane(tree);
        JLabel status = new JLabel("Status: select node");
        tree.addTreeSelectionListener(e -> status.setText("Selected: " + tree.getLastSelectedPathComponent()));
        return wrap(sp, status);
    }

    // JSlider (Change)
    private JPanel sliderTab() {
        JSlider slider = new JSlider(0,100,50);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JLabel status = new JLabel("Value: " + slider.getValue());
        slider.addChangeListener(e -> status.setText("Value: " + slider.getValue()));
        return wrap(slider, status);
    }

    // JProgressBar (Worker + buttons)
    private JPanel progressTab() {
        JPanel p = new JPanel(new BorderLayout(6,6));
        JProgressBar pb = new JProgressBar(0,100);
        pb.setStringPainted(true);
        JLabel status = new JLabel("Status: idle");
        JButton start = new JButton("Start");
        JButton reset = new JButton("Reset");
        start.addActionListener(e -> {
            start.setEnabled(false);
            status.setText("Running...");
            SwingWorker<Void,Integer> w = new SwingWorker<>() {
                protected Void doInBackground() throws Exception {
                    for (int i=0;i<=100;i+=5){ Thread.sleep(90); publish(i); setProgress(i);}
                    return null;
                }
                protected void process(java.util.List<Integer> chunks){
                    int last = chunks.get(chunks.size()-1);
                    pb.setValue(last);
                    status.setText("Progress: " + last + "%");
                }
                protected void done(){ status.setText("Finished"); start.setEnabled(true); }
            };
            w.execute();
        });
        reset.addActionListener(e -> { pb.setValue(0); status.setText("Reset to 0"); });
        JPanel ctrl = new JPanel(); ctrl.add(start); ctrl.add(reset);
        p.add(pb, BorderLayout.NORTH); p.add(ctrl, BorderLayout.CENTER);
        return wrap(p, status);
    }

    // JSpinner (Change)
    private JPanel spinnerTab() {
        JSpinner sp = new JSpinner(new SpinnerNumberModel(5, 0, 20, 1));
        JLabel status = new JLabel("Value: " + sp.getValue());
        sp.addChangeListener(e -> status.setText("Value: " + sp.getValue()));
        return wrap(sp, status);
    }

    // JEditorPane (HyperlinkEvent)
    private JPanel editorPaneTab() {
        JEditorPane ep = new JEditorPane();
        ep.setContentType("text/html");
        ep.setText("<html><h3>EditorPane</h3><p>Click <a href='https://example.com'>this link</a></p></html>");
        ep.setEditable(false);
        JScrollPane sp = new JScrollPane(ep);
        JLabel status = new JLabel("Status: waiting for hyperlink");
        ep.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                status.setText("Link clicked: " + e.getURL());
            }
        });
        return wrap(sp, status);
    }

    // JTextPane (Styled Document)
    private JPanel textPaneTab() {
        JTextPane tp = new JTextPane();
        tp.setText("Type here and select text, then press 'Bold Selection' to apply style.");
        JScrollPane sp = new JScrollPane(tp);
        JLabel status = new JLabel("Status: length = " + tp.getText().length());
        tp.getDocument().addDocumentListener(new DocumentListener(){
            void upd(){ status.setText("Length = " + tp.getText().length()); }
            public void insertUpdate(DocumentEvent e){ upd(); }
            public void removeUpdate(DocumentEvent e){ upd(); }
            public void changedUpdate(DocumentEvent e){}
        });
        JButton bold = new JButton("Bold Selection");
        bold.addActionListener(e -> {
            int start = tp.getSelectionStart(), end = tp.getSelectionEnd();
            if (start == end) { status.setText("No selection"); return; }
            StyledDocument doc = tp.getStyledDocument();
            Style style = tp.addStyle("bold" + System.nanoTime(), null);
            StyleConstants.setBold(style, true);
            doc.setCharacterAttributes(start, end - start, style, false);
            status.setText("Applied bold to selection");
        });
        JPanel ctrl = new JPanel(new FlowLayout(FlowLayout.LEFT)); ctrl.add(bold);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(sp, BorderLayout.CENTER);
        wrapper.add(ctrl, BorderLayout.SOUTH);
        return wrap(wrapper, status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllComponentsDemo frame = new AllComponentsDemo();
            frame.setVisible(true);
        });
    }
}
