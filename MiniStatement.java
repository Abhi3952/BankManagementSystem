import javax.swing.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {
    MiniStatement(String pinnumber){
        setTitle("Mini Statement");
        setSize(400,1000);
        setLayout(null);
        setLocation(20,20);
        JLabel mini=new JLabel();
        add(mini);
        mini.setBounds(20,200,400,200);
        JLabel bank=new JLabel("Indian Bank");
        bank.setBounds(150,20,100,20);
        add(bank);
        JLabel card=new JLabel();
        card.setBounds(20,80,300,200);
        add(card);
        JLabel balance=new JLabel();
        balance.setBounds(20,900,300,20);
        add(balance);
        try{
            con c=new con();

         ResultSet rs= c.st.executeQuery("select * from login where pin='"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number:"+" "+rs.getString("cardnumber").substring(0,4)+"xxxxxxxx"+rs.getString("cardnumber").substring(12));

            }

        }catch (Exception e){
            System.out.println(e);
        }
        try{
            con c=new con();
            long bal=0;
            ResultSet rs=c.st.executeQuery("select * from bank where pin='"+pinnumber+"'");
            while(rs.next()){
                mini.setText(mini.getText()+"<html>"+rs.getString("transc_date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    bal+=Long.parseLong(rs.getString("amount"));
                }else {
                    bal-=Long.parseLong(rs.getString("amount"));
                }
            }
            balance.setText("Your Current Account Balance is Rs "+bal);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
new MiniStatement("").setVisible(true);
    }
}

