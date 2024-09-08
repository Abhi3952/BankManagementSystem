import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String pinnumber;
    JButton back;
    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setSize(900, 900);
        setLayout(null);
        setLocation(300, 0);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        con c = new con();
        long balance = 0;
        try {
            ResultSet rs = c.st.executeQuery("select * from bank where pin='" + pinnumber + "'");

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Long.parseLong(rs.getString("amount"));
                } else {
                    balance -= Long.parseLong(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        JLabel text=new JLabel("Your Current Account Balance is Rs"+balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170,300,400,30);
        image.add(text);
    }
    public void actionPerformed(ActionEvent ae){
if(ae.getSource()==back){
    setVisible(false);
    new transaction(pinnumber).setVisible(true);
}
        //balance enquiry ka action isme isliye implement nhi kro kyoki hum chahte the ke jese hi balanceenquiry button pr click ho siddha balance dikhaye na ke koi or frame khule iske liye humne action constructor ke under hi implement kr diya
    }
    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
