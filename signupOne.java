import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import java.util.*;
import java.awt.*;
public class signupOne extends JFrame implements ActionListener{
    long random;
    JTextField name1, name2, citi, countr, mail, state1, pin, date;
JButton next;
JRadioButton male,female;

   signupOne() {

        setLayout(null);
        setSize(1000, 1000);

        setLocation(150, 10);
        Random ran = new Random();//it is class  used to generate a randon number and present in util package
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);//it will generate a random 4 digit number
        JLabel formno = new JLabel("APPLICATION FORM NO:" + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 20));
        formno.setBounds(140, 20, 600, 40);
        add(formno);
        JLabel personaldetails = new JLabel("Page 1:Personal details");
        personaldetails.setBounds(200, 35, 300, 100);
        personaldetails.setFont(new Font("aerial", Font.BOLD, 10));
        add(personaldetails);
        JLabel name = new JLabel("Name:");
        name.setBounds(100, 100, 70, 20);
        add(name);
     //   JLabel fname = new JLabel("Father's name:");
     //   fname.setBounds(100, 140, 100, 20);
    //    add(fname);
        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(100, 170, 200, 20);
        add(dob);
        JLabel gender = new JLabel("Gender:");
        gender.setBounds(100, 220, 100, 20);
        add(gender);
        JLabel email = new JLabel("Email Add.:");
        email.setBounds(100, 270, 70, 20);
        add(email);
        JLabel city = new JLabel("City");
        city.setBounds(100, 320, 50, 20);
        add(city);
        JLabel state = new JLabel("State");
        state.setBounds(100, 360, 50, 20);
        add(state);
        JLabel country = new JLabel("Country");
        country.setBounds(100, 400, 50, 20);
        add(country);
        JLabel pincode = new JLabel("Pincode");
        pincode.setBounds(100, 440, 50, 20);
        add(pincode);
         name1 = new JTextField();
        name1.setBounds(180, 100, 80, 20);
        add(name1);

       //  name2 = new JTextField();
      //  name2.setBounds(180, 140, 80, 20);
      //  add(name2);
         mail = new JTextField();
        mail.setBounds(180, 270, 200, 20);
        add(mail);
       citi = new JTextField();
        citi.setBounds(160, 320, 80, 20);
        add(citi);
       state1 = new JTextField();
        state1.setBounds(160, 360, 80, 20);
        add(state1);
        countr = new JTextField();
        countr.setBounds(160, 400, 80, 20);
        add(countr);
         pin = new JTextField();
        pin.setBounds(160, 440, 80, 20);
        add(pin);
         date = new JTextField("enter date in format:01 March 2023");
        date.setBounds(200, 170, 100, 20);
        add(date);
         male = new JRadioButton("Male");
        male.setBounds(220, 220, 70, 20);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(310, 220, 70, 20);
        add(female);
        ButtonGroup gendergroup = new ButtonGroup();//to ensure only one button can be chosen
        gendergroup.add(male);
        gendergroup.add(female);
       next = new JButton("next");
        next.setBounds(120, 560, 80, 30);
        add(next);
        next.addActionListener(this);

    }
    public void actionPerformed(ActionEvent ae){
       String formno=""+random;//""+ use kra string m convert krne ke liye
        String name=name1.getText();//getText() used to extract value from the form
      //  String fname=name2.getText();

       //date bala baad m krta hu you tube se
        String dob=date.getText();
        String gender=null;
        if(male.isSelected()){
            gender="Male";
        }
        else if(female.isSelected()){
            gender="Female";

        }
        String email=mail.getText();
        String city=citi.getText();
        String country=countr.getText();
        String state=state1.getText();
        String pincode=""+pin.getText();
        try {

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is required");
            }//we can put all the validation by else if condition similar to this
            else{
                con c=new con();
               String query="insert into personaldetails values('"+formno+"','"+name+"','"+dob+"','"+gender+"','"+email+"','"+city+"','"+state+"','"+country+"','"+pincode+"')";
            // String query="insert into personaldetails(formno,name,fname,dob,gender,email,city,state,country,pincode)values(?,?,?,?,?,?,?,?,?,?)";

             c.st.executeUpdate(query);
             setVisible(false);
                new signupTwo(formno).setVisible(true);

            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public static void main(String[] args) {

        new signupOne().setVisible(true);
    }
}
