
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
public class Transfer {
    String from,to,amount,pin0,email;
    
    ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6=null;
    PreparedStatement pst,pst1,pst2,pst3,pst4,pst5,pst6=null;
    
    MyPage m=new MyPage();
   public Transfer(String fromacc,String toacc,String toamt,String pin,String emss) throws SQLException
    {
        from=fromacc;
        to=toacc;
        amount=toamt;
        pin0=pin;
        email=emss;
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
        Tra();
    }
   public void Tra() throws SQLException
   {
       Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
       String sql1="select * from account where Accountno=?";
       String sql2="select * from account where Accountno=?";
       String sql3="select * from forgotpass where username=?";
       String sql4="insert into trans(fromo,too,details,remarks,date,tranamt) values(?,?,?,?,?,?)";
       String sql5="UPDATE account SET balance=? where AccountNo=?";
       String sql6="UPDATE account SET balance=? where Accountno=?";
       pst1=con1.prepareStatement(sql1);
       pst2=con1.prepareStatement(sql2);
       pst3=con1.prepareStatement(sql3);
       pst4=con1.prepareStatement(sql4);
       pst5=con1.prepareStatement(sql5);
       pst6=con1.prepareStatement(sql6);
       pst1.setString(1, from);
       pst2.setString(1,to);
       pst3.setString(1,email);
       pst5.setString(2,from);
       pst6.setString(2,to);
       rs1=pst1.executeQuery();
       rs2=pst2.executeQuery();
       rs3=pst3.executeQuery();
       rs1.next();
       //System.out.println("rs2:"+rs2.next());
       rs3.next();
       String avlbal=rs1.getString("balance");
       int bal1 = Integer.parseInt(avlbal);
       int amount1=Integer.parseInt(amount);
       String pin1=rs3.getString("PIN");
       if(bal1>=amount1 && pin1.equals(pin0) && rs2.next()==true)
       {
               int newbal=bal1-amount1;
               String tobal=rs2.getString("balance");
               int tobal1 = Integer.parseInt(tobal);
               int newtobal=amount1+tobal1;
               String newbal1=Integer.toString(newbal);
               String newtobal1=Integer.toString(newtobal);
               pst4.setString(1,from);
               pst4.setString(2,to);
               pst4.setString(3,"Transfer");
               pst4.setString(4,"Transfer");
               pst4.setString(5,m.Calenda2());
               pst4.setString(6,amount);
               pst4.executeUpdate();
               
               
               pst5.setString(1,newbal1);
               pst6.setString(1,newtobal1);
               pst5.executeUpdate();
               pst6.executeUpdate();
               
               JOptionPane.showMessageDialog(null,"Transfer sUCCESSFUL");
   
           
       }
        else
           {
               JOptionPane.showMessageDialog(null,"PIN Incorrect or Not enough balance");
           }
       
   }
    
}
