/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hammer;

import java.sql.*;

/**
 *
 * @author Mohit
 */
public class Database_Connection {
    
static Connection con = null;
static int a=0;

Database_Connection()
{
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql:///Hammer_User_Data","root","Qwerty1234");  
//        Old Password was 'qwerty', new password is: 'Qwerty1234'
    }
    catch(Exception e)
    {
        System.out.println("Errror because of: "+e);
    }
}

public static void main(String args[])
{
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql:///Hammer_User_Data","root","qwerty1234");
//        Old Password was 'qwerty', new password is: 'Qwerty1234'
        
        Statement s = con.createStatement();
        ResultSet rs=s.executeQuery("select * from Admin_info");
        while(rs.next())
        {
           a=1; 
        }
        if(a!=0)
        {
            new Home_Page().setVisible(true);
        }
        
    }
    catch(Exception e)
    {
        new CreateDataBase();
        System.out.println("Errror because of: "+e);
    }    
}
}
