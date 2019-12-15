
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Withdraw {
    Connection con=null;
    ResultSet rs,rs1,rs3=null;
    PreparedStatement pst,pst1,pst2,pst3,pst4=null;
    String username,amount,account,pin0;
    MyPage m=new MyPage();
    public Withdraw(String email,String amt,String acc,String pin)
    {
        username=email;
        amount=amt;
        account=acc;
        pin0=pin;
        withd();
    }
    private void withd()
    {
        String sql="Select * from account where Email=?";
        String sql1="insert into trans(fromo,too,details,remarks,date,tranamt) values(?,?,?,?,?,?)";
        String sql2="UPDATE account SET balance=? where Email=?";
        String sql3="Select PIN from forgotpass where username=?";
        try
        {
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
           pst=con.prepareStatement(sql);
           pst.setString(1,username);
           rs=pst.executeQuery();
           System.out.println(rs.next());
           String avlbal=rs.getString("balance");
           int bal1 = Integer.parseInt(avlbal);
           int amount1=Integer.parseInt(amount);
           pst3=con.prepareStatement(sql3);
           pst3.setString(1,username);
           rs3=pst3.executeQuery();
           System.out.println(rs3.next());
           
           String pin1=rs3.getString("PIN");
           if(bal1>=amount1 && pin0.equals(pin1))
           {
               int newbal=bal1-amount1;
               String newbal1=Integer.toString(newbal);
               pst1=con.prepareStatement(sql1);
               pst2=con.prepareStatement(sql2);
               pst1.setString(1,account);
               pst1.setString(2,"Self");
               pst1.setString(3,"Withdraw");
               pst1.setString(4,"Withdraw");
               pst1.setString(5,m.Calenda2());
               pst1.setString(6,amount);
               pst1.executeUpdate();
               pst2.setString(1,newbal1);
               pst2.setString(2,username);
               pst2.executeUpdate();
               JOptionPane.showMessageDialog(null,"withdraw sUCCESSFUL");
   
               
            
            
                
           }
           else
           {
               JOptionPane.showMessageDialog(null,"PIN Incorrect or Not enough balance");
           }
           
                  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MyPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
}
