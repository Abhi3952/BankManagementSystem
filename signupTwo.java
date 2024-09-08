
import javax.swing.*;
        import java.awt.event.*;

        import java.util.*;
        import java.awt.*;
public class signupTwo extends JFrame implements ActionListener {

    JTextField  name2, citi,pan, addhar, date,exist;
    JButton next;
    JComboBox reli,catig,incom,quali,occupat;
    JRadioButton yes,no,eyes,eno;
String formno;
    signupTwo(String formno) {
this.formno=formno;
        setLayout(null);
        setSize(1000, 1000);

        setLocation(150, 10);

        setTitle("New Account Application Form 2");

        JLabel additionaldetails = new JLabel("Page 2:Additional details");
        additionaldetails.setBounds(200, 35, 300, 100);
        additionaldetails.setFont(new Font("aerial", Font.BOLD, 10));
        add(additionaldetails);
        JLabel religion = new JLabel("Religion:");
      religion.setBounds(100, 100, 70, 20);
        add(religion);
        JLabel category = new JLabel("Category:");
        category.setBounds(100, 140, 100, 20);
        add(category);

        JLabel income = new JLabel("Income:");
        income.setBounds(100, 170, 200, 20);
        add(income);

        JLabel qualification = new JLabel("Qualifications:");
       qualification.setBounds(100, 270, 100, 20);
        add(qualification);
        JLabel citizenship = new JLabel("Senier citizen");
        citizenship.setBounds(100, 320, 100, 20);
        add(citizenship);
        JLabel adharcard = new JLabel("Aadhar number");
       adharcard.setBounds(100, 360, 100, 20);
        add(adharcard);
        JLabel pancard = new JLabel("Pan number");
        pancard.setBounds(100, 400, 100, 20);
        add(pancard);
        JLabel occupati = new JLabel("Occupation");
        occupati.setBounds(100, 440, 100, 20);
        add(occupati);
        JLabel existing=new JLabel("Existing customer");
        existing.setBounds(100,480,150,20);
        add(existing);
        String valrel[]={"Hindu","Muslim","Sikh","Christian","Others"};
       reli=new JComboBox<>(valrel);
        reli.setBounds(180, 100, 80, 20);
        add(reli);
String cat[]={"General","OBC","SC/ST","EWS"};
       catig = new JComboBox<>(cat);
        catig.setBounds(180, 140, 80, 20);
        add(catig);
        String qual[]={"10th/12th","Graduation","Post Graduation","Docotorate","Other"};
        quali=new JComboBox<>(qual);
        quali.setBounds(210,270,200,20);
        add(quali);
 yes=new JRadioButton("Yes");

        yes.setBounds(210, 320, 80, 20);
        add(yes);
       no=new JRadioButton("No");

        no.setBounds(300, 320, 80, 20);
        add(no);
        ButtonGroup senier=new ButtonGroup();
        senier.add(yes);
        senier.add(no);
        eyes=new JRadioButton("Yes");

        eyes.setBounds(260, 480, 50, 20);
        add(eyes);
        eno=new JRadioButton("No");

        eno.setBounds(330, 480, 50, 20);
        add(eno);
        ButtonGroup excusto=new ButtonGroup();
        excusto.add(eyes);
        excusto.add(eno);

       addhar= new JTextField();
      addhar.setBounds(210, 360, 80, 20);
        add(addhar);
        pan = new JTextField();
        pan.setBounds(210, 400, 80, 20);
        add(pan);
      String occup[]={"Job","Business","Farmer","Student"};
      occupat=new JComboBox<>(occup);
      occupat.setBounds(210, 440, 80, 20);
        add(occupat);
        String inc[]={"null","<=1.5lakh","<=2.5lakh","<=4.5lakh","upto 10lakh"};
      incom=new JComboBox<>(inc);
        incom.setBounds(200, 170, 100, 20);
        add(incom);

        next = new JButton("next");
        next.setBounds(120, 560, 80, 30);
        add(next);
        next.addActionListener(this);

    }
    public void actionPerformed(ActionEvent ae){
        String formno=this.formno;//""+ use kra string m convert krne ke liye
String religion=(String) reli.getSelectedItem();//jcombo m se selected value nikalne ke liye kra ye object return krta isliye type casting kri h
        String category=(String) catig.getSelectedItem();
        String adhar=addhar.getText();
        String income=(String) incom.getSelectedItem();
        String education=(String) quali.getSelectedItem();
        String occupation=(String) occupat.getSelectedItem();
        String seniercitizen=null;
      if(yes.isSelected()){
          seniercitizen ="Yes";
        }
        else if(no.isSelected()){
            seniercitizen="No";

        }
        String existing=null;
        if(eyes.isSelected()){
           existing ="Yes";
        }
        else if(eno.isSelected()){
        existing="No";

        }

      String pancard=pan.getText();
   //  String country=countr.getText();
      //  String state=state1.getText();

        try {
if(reli.equals("")){
    JOptionPane.showMessageDialog(null,"Religion is required");
}else{
    con c=new con();
  String query="insert into signuptwo  values('"+formno+"','"+religion+"','"+category+"','"+income+"','"+education+"','"+occupation+"','"+pancard+"','"+adhar+"','"+seniercitizen+"','"+existing+"')";
            c.st.executeUpdate(query);
    setVisible(false);//ye page band krne ke liye
    new signupThree(formno).setVisible(true);//

}

          //n put all the validation by else if condition similar to this
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public static void main(String[] args) {

        new signupTwo("").setVisible(true);

    }
}
