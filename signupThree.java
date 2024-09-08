import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class signupThree extends JFrame implements ActionListener{
    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5;
    JButton submit,cancel;
    String formno;
    signupThree(String formno){
        this.formno=formno;
        setSize(800,800);
setLocation(350,0);

        setLayout(null);

        JLabel l1=new JLabel("Page 3:Account details");
       l1.setBounds(260,40,400,40);
        add(l1);

        JLabel type=new JLabel("Account Type");
       type.setBounds(100,140,200,30);
        add(type);
        r1=new JRadioButton("Saving Account");
        r1.setBounds(100,180,150,20);
        add(r1);
        r2=new JRadioButton("Fixed Diposit");
        r2.setBounds(350,180,150,20);
        add(r2);
        r3=new JRadioButton("Current  Account");
        r3.setBounds(100,220,150,20);
        add(r3);
        r4=new JRadioButton("Recurring Deposit  Account");
        r4.setBounds(350,220,200,20);
        add(r4);
        ButtonGroup groupaccount =new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JLabel card=new JLabel("Card Number");
       card.setBounds(100,300,200,30);
        add(card);

        JLabel number=new JLabel("xxxx-xxxx-xxxx-4156");
       number.setBounds(300,300,200,30);
        add(number);
        JLabel carddetails=new JLabel("This is your 16 digit card no.");
        carddetails.setBounds(100,330,200,13);
        add(carddetails);
        JLabel pin=new JLabel("Pin Number");
        pin.setBounds(100,350,200,30);
        add(pin);
        JLabel pindetails=new JLabel("This is your 4 digit pin no.");
        pindetails.setBounds(100,380,200,13);
        add(pindetails);
        JLabel pinnumber=new JLabel("xxxx");
       pinnumber.setBounds(300,350,200,30);
        add(pinnumber);
        JLabel services=new JLabel("Service required");
        services.setBounds(100,420,200,30);
        add(services);
        c1=new JCheckBox("ATM CARD");
        c1.setBounds(100,450,200,30);
        add(c1);
        c2=new JCheckBox("Mobile Banking");
        c2.setBounds(100,480,200,30);
        add(c2);
        c3=new JCheckBox("Email & SMS Alerts");
        c3.setBounds(350,450,200,30);
        add(c3);
        c4=new JCheckBox("Cheque Book");
        c4.setBounds(350,480,200,30);
        add(c4);
        c5=new JCheckBox("I hereby declare that above enter details are correct to the best of my knowledge");
        c5.setBounds(100,550,600,30);
        add(c5);
submit=new JButton("submit");
submit.setBounds(100,650,100,30);
add(submit);
submit.addActionListener(this);
        cancel=new JButton("Cancel");
       cancel.setBounds(250,650,100,30);
        add(cancel);
        cancel.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accounttype = null;
            if (r1.isSelected()) {
                accounttype = "Saving Account";
            } else if (r2.isSelected()) {
                accounttype = "Fixed Deposit Account";

            } else if (r3.isSelected()) {
                accounttype = "Cuurent Account";

            } else if (r4.isSelected()) {
                accounttype = "Recurring Deposit Account";
            }
            Random random = new Random();
           String cardnumber =""+ Math.abs((random.nextLong() % 90000000L)+5040900000000000L);
            String pinnumber =""+ Math.abs((random.nextLong() % 9000L)+1000L);
            String facility="";
            if(c1.isSelected()){
                facility=facility+"ATM CARD";
            }else if(c2.isSelected()){
                facility=facility+"Mobile Banking";
            }
            else if(c3.isSelected()){
                facility=facility+"Email & SMS alerts";
            }
            else if(c4.isSelected()){
                facility=facility+"Cheque Book";
            }
            try{
                if(accounttype.equals("")){
                    JOptionPane.showMessageDialog(null,"Accoun type is required");
                } else {
                    con c=new con();

                    String query1="insert into signupthree values('"+formno+"','"+accounttype+"','"+cardnumber+"','"+pinnumber+"','"+facility+"')";
                    String query2 = "insert into login values('" + formno + "','" + cardnumber + "','" + pinnumber + "')";

                 //   String query2="insert into login values('"+formno+"','"+cardnumber+"','"+pinnumber+")";
                   c.st.executeUpdate(query1);
                    c.st.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null,"Card Number"+cardnumber+"\n Pin:"+pinnumber);
                    setVisible(false);
                    new Deposit(pinnumber).setVisible(true);

                }
            }catch(Exception e){
                System.out.println(e);
            }

        }
            else if (ae.getSource() == cancel) {
setVisible(false);
new Login().setVisible(true);
        }
    }
        public static void main(String[] args) {

        new signupThree("formno").setVisible(true);
    }
}
