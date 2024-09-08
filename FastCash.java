import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton deposit, fastcash, balanceenquiry, ministatement, exit, withdrawl, pinchange;
    String pinnumber;

    public FastCash(String pinnumber) {
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

        JLabel text = new JLabel("Select Withdrawl Amount");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        deposit = new JButton("Rs 100");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Rs 500");
        withdrawl.setBounds(355, 415, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Rs 1000");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Rs 2000");
        ministatement.setBounds(355, 450, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Rs 5000");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Rs 10000");
        balanceenquiry.setBounds(355, 485, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Back");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);
    }

  /*  public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == exit) {
            setVisible(false);
            new transaction(pinnumber).setVisible(true);

        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);
            con c = new con();
            try {
                ResultSet rs = c.st.executeQuery("select * from bank where pin='" + pinnumber + "' and type='Deposit'");
                long balance = 0;
                while (rs.next()) {
                    balance += Long.parseLong(rs.getString("amount"));
                }
                if (ae.getSource() != exit && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdrawl','" + amount + "')";
                c.st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs" + amount + " " + "Debited Succesfully");
                setVisible(false);
                new transaction(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}


   */public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == exit) {
          setVisible(false);
          new transaction(pinnumber).setVisible(true);
      } else {
          String amount = ((JButton) ae.getSource()).getText().substring(3);
          con c = new con();
          try {
              // Calculate balance considering only deposit transactions
              ResultSet rs = c.st.executeQuery("select * from bank where pin='" + pinnumber + "' and type='Deposit'");
              long balance = 0;
              while (rs.next()) {
                  balance += Long.parseLong(rs.getString("amount"));
              }

              // Check if the balance is sufficient for withdrawal
              if (balance < Long.parseLong(amount)) {
                  JOptionPane.showMessageDialog(null, "Insufficient Balance");
                  return;
              }

              // Perform the withdrawal
              Date date = new Date();
              String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdrawl','" + amount + "')";
              c.st.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Rs" + amount + " " + "Debited Successfully");
              setVisible(false);
              new transaction(pinnumber).setVisible(true);
          } catch (Exception e) {
              System.out.println(e);
          }
      }
  }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
