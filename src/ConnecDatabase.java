/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pradi
 */
import static java.lang.invoke.VarHandle.AccessMode.SET;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class ConnecDatabase {
    Connection con=null;
    public ConnecDatabase()
    {
        connect();
    }
    void connect()
    {
        try
        {
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");   
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
}
