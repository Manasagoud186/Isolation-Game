import javax.swing.*;

public class DisplayInfo {
    public static void showDetails(String s,String n,String g,String e,String p,String i){
        JTextArea t=new JTextArea();
        t.setEditable(false);
        t.setText("User Details\n\nSalutation: "+s+
                  "\nName: "+n+
                  "\nGender: "+g+
                  "\nEmail: "+e+
                  "\nPassword: "+p+
                  "\nInterests: "+i);

        JFrame f=new JFrame("User Information");
        f.setSize(350,300);
        f.add(t);
        f.setVisible(true);
    }
}