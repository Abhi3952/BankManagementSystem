import java.sql.*;
public class con {
    Connection conn;
    Statement st;
    public  con(){

        try{

            Class.forName("oracle.jdbc.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            String user="ujjawal";
            String pass="******";
            conn=DriverManager.getConnection(url,user,pass);
            st=conn.createStatement();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
