/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

/**
 *
 * @author Franky Naidu
 */
public class ExotelDAO {
    
     private Connection con;
     //Insert Query for incoming sms table
     private final static String INSERT_EXOTEL = "INSERT INTO incomingsms(`smsSid`, `sender`, `receiver`, `date`, `body`) VALUES(?,?,?,?,?) ";
     // Constructor
     public ExotelDAO(){
        //set the connection Object
         con = DataBase.getConnection();
     }

    //incoming Sms Handling
     public String insertRecord(Map data){         
                String msg="";
         try {
               //Prepare a statement
                PreparedStatement ps = con.prepareStatement(INSERT_EXOTEL);
                //set the Value of the prepared Statements
                ps.setString(1,(String)data.get("SmsSid"));
                ps.setString(2,(String)data.get("sender"));
                ps.setString(3,(String)data.get("receiver"));
                ps.setString(4,(String)data.get("date"));
                ps.setString(5,(String)data.get("body"));
                 
            } 
            catch (Exception e) {
            }
        return msg;
     }
    
}
