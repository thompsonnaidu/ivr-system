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

     // Constructor
     public ExotelDAO(){
        //set the connection Object
         con = DataBase.getConnection();
     }

    //incoming Sms Handling
     public String insertRecord(Map data){         
                String msg="";
         try {
                
                 
            } 
            catch (Exception e) {
            }
        return msg;
     }
    
}
