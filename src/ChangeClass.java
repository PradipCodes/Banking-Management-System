
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pradi
 */
public class ChangeClass {
    String from,to,amount,pin0,email;
    
    ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6=null;
    PreparedStatement pst,pst1,pst2,pst3,pst4,pst5,pst6=null;
    String oldpass,newpass,newpass1,username;
    public ChangeClass(String passString1,String passString2,String passString3,String ema)
    {
        oldpass=passString1;
        newpass=passString2;
        newpass1=passString3;
        username=ema;
        change();
    }
    public void change() throws SQLException
    {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
        String sql1="Select PIN from forgotpass where username=?";
        String sql2="UPDATE forgotpass SET PIN=? where username=?";
        pst1=con.prepareStatement(sql1);
        pst2=con.prepareStatement(sql2);
        pst1.setString(1,username);
        pst2.setString(2,username);
        rs1=pst1.executeQuery();
        rs1.next();
        String pin1=rs1.getString("PIN");
        if(pin1.equals(oldpass) && newpass.equals(newpass1))
        {
            pst2.setString(1,newpass);
            pst2.executeUpdate();
            String too=username;
            String msg="PIN changed";
            String sbj="PIN Change Notification";
            SendEmail snd=new SendEmail(too,msg,sbj);
            JOptionPane.showMessageDialog(null,"PIN Change sUCCESSFUL");
   
           
       }
        else
           {
               JOptionPane.showMessageDialog(null,"Old PIN Incorrect or New PIN do not match");
           }
            
            
        }
        
        
    }
    

