/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Franky Naidu
 */
public class DataBase {

    /**
     * @param args the command line arguments
     */
    final public static String HOST_URL ="jdbc:mysql://localhost:3306/exotel";
    final public static String USERNAME="root";;
    final public static String PASSWORD="";
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(HOST_URL,USERNAME,PASSWORD);
            
            return con;
        } catch (Exception e) {
            return con;
            
        }
    }
    
    
    
}
