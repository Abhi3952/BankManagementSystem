
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
public class PinChange extends JFrame implements ActionListener{
JButton change,back;
JPasswordField pin,repin;
String pinnumber;
    PinChange(String pinnumber) {
        this.pinnumber=pinnumber;

        setSize(900, 900);
        setLayout(null);
        setLocation(300, 0);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        JLabel text = new JLabel("Change Your PIN");
        text.setBounds(250, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);
        JLabel pintext = new JLabel("New PIN");
        pintext.setBounds(165, 320, 80, 35);
        pintext.setForeground(Color.WHITE);
        image.add(pintext);
        pin=new JPasswordField();
        pin.setBounds(330,325,180,25);
        image.add(pin);
        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setBounds(165, 360, 120, 35);
        repintext.setForeground(Color.WHITE);
        image.add(repintext);
        repin=new JPasswordField();
        repin.setBounds(330,360,180,25);

image.add(repin);
change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);
        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
    }
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == change) {
                try {
                    String npin = pin.getText();
                    String rpin = repin.getText();
                    if (!npin.equals(rpin)) {
                        JOptionPane.showMessageDialog(null, "Enter pin does not Matched");
                        return;
                    }
                    if (npin.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please Enter New PIN");
                        return;
                    }
                    if (rpin.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please re-enter new pin");
                    }
                    con c = new con();
                    String query1 = "update bank set pin='" + rpin + "' where pin='" + pinnumber + "'";

                    String query2 = "update login set pin='" + rpin + "' where pin='" + pinnumber + "'";
                    String query3 = "update signupthree set pin='" + rpin + "' where pin='" + pinnumber + "'";
                    //jis jis table m pin h un sb m pin ko update kra
                    c.st.executeUpdate(query1);

                    c.st.executeUpdate(query2);

                    c.st.executeUpdate(query3);

                    JOptionPane.showMessageDialog(null,"PIN Changed Succesfully");
                    setVisible(false);
                    new transaction(rpin).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                setVisible(false);
                new transaction(pinnumber).setVisible(true);
            }
        }
    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
