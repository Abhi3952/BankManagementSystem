import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class Withdrawl extends JFrame implements ActionListener {
    JButton withdraw, back;
    String pinnumber;
    JTextField amount;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setSize(900, 900);
        setLocation(300, 0);
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter the amount you want to Withdraw");
            } else {
                try {
                    con c = new con();

                    // Calculate balance considering only deposit transactions
                    ResultSet rs = c.st.executeQuery("select * from bank where pin='" + pinnumber + "' and type='Deposit'");
                    long balance = 0;
                    while (rs.next()) {
                        balance += Long.parseLong(rs.getString("amount"));
                    }

                    // Check if the withdrawal amount is greater than the balance
                    if (Long.parseLong(number) > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    // Perform the withdrawal
                    String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdrawal','" + number + "')";
                    c.st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs" + number + " " + "Withdrawn Successfully");
                    setVisible(false);
                    new transaction(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}

