/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hammer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Mohit
 */
public class CreateDataBase {

    CreateDataBase() {
        try
    {
        Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql:///","root","qwerty");
        
        Statement s = con.createStatement();
        s.executeUpdate("create database Hammer_User_Data");
          con = DriverManager.getConnection("jdbc:mysql:///Hammer_User_Data","root","qwerty"); 
           s = con.createStatement();
    s.executeUpdate("create table user_info( User_No int , Name text , age int , gender text )");  
        s.executeUpdate("create table user_status( User_No int , Games_Played int , status text)");
        s.executeUpdate("create table Admin_info( User_No int , Name text , password text )");
        s.executeUpdate("insert into Admin_info(User_No , Name , password) values ('"+00001+"' , '"+"Admin"+"' , '"+"Password"+"') ");      
       new Home_Page().setVisible(true);
    }catch(Exception e)
    {
        System.out.println("Errror because of: "+e);
    }   
       
    }
}