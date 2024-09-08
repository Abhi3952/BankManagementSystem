import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {
    JButton dep,back;
    String pinnumber;
    JTextField amount;
    Deposit(String pinnumber){
        this.pinnumber=pinnumber;
        setSize(900,900);
        setLocation(300,0);
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        JLabel text=new JLabel("Enter the amount you want to deposit");
        text.setBounds(170,300,400,20);
        text.setForeground(Color.WHITE);
        image.add(text);
        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);
        dep=new JButton("Deposit");
        dep.setBounds(355,485,150,30);
        dep.addActionListener(this);
        image.add(dep);
        back=new JButton("Back");
       back.setBounds(355,520,150,30);
       back.addActionListener(this);
        image.add(back);
        
    }
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==dep){
            String number = amount.getText();
            Date date=new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter the amount you want to Deposit");
            }else {
                try {
                    con c = new con();
                    String query = "insert into bank values('" + pinnumber + "','" + date + "','Deposit','" + number + "')";
                    c.st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs" + number +" "+ "Deposited Succesfully");
                    setVisible(false);
                    new transaction(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            // Implement the logic to handle the deposit (e.g., update the database)
            // For now, let's just show a message

        } else if (ae.getSource()==back) {
            System.out.println("opening transac");
            setVisible(false);
            new transaction(pinnumber).setVisible(true);
        }
    }
    public static void main(String[] args) {
       new Deposit("").setVisible(true);
    }
}
