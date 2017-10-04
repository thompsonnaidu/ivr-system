/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

/**
 *
 * @author Franky Naidu
 */
public class ExotelDAO {
    
     private Connection con;
     //Insert Query for incoming sms table
     private final static String INSERT_EXOTEL = "INSERT INTO incomingsms(`smsSid`, `sender`, `receiver`, `date`, `body`) VALUES(?,?,?,?,?) ";
     private final static String GET_ALL_EXOTEl = "select * from incomingsms";
     private final static String INSERT_INCOMING_CALL = "INSERT INTO `incomingCall`(`callid`, `caller`) VALUES (?,?)";
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
                //Exceute the query 
                if (ps.executeUpdate() >0 ){
                    //Query Fired Successfully
                    msg = "Hi "+(String)data.get("sender")+",Thank you for registering; Your account has been activated";
                }
                else{
                    msg = "Sorry "+(String)data.get("sender")+",Your request is not accepted";
                
                }  

                 
            } 
            catch (Exception e) {
                msg = "Sorry "+(String)data.get("sender")+",Your request is not accepted";
                System.out.println(""+e.getMessage());
            }
            System.out.println(msg);
        return msg;
     }


     //incoming Call Handling
     public boolean insertCallRecord(Map data){
         try {

                PreparedStatement ps = con.prepareStatement(INSERT_INCOMING_CALL);
                ps.setString(1,(String)data.get("CallSid"));
                ps.setString(2,(String)data.get("From"));
                if (ps.executeUpdate() >0 ){
                    return true;
                }
                else{
                    return false;
                } 
            } 
            catch (Exception e) {                
                System.out.println(""+e.getMessage());
            }
         return false;
     }

       public String sendSms(Map data){
        try{
            //Prepare the post request parameters
            String from = "From="+(String)data.get("From")+"&";
            String[] toarray = (String [])data.get("To");
            String to="";
             for (String todata : toarray) {
                 to +="To[]="+todata+"&"; 
             }
            String msg = "Body="+data.get("Body");
           
           String link = "https://"+EXOTEL_SID+":"+EXOTEL_TOKEN+"@twilix.exotel.in/v1/Accounts/"+EXOTEL_SID+"/Sms/send";
           //Set the Url
            URL url = new URL(link);            
            //Set the parameters
            String urlParameters = from+to+msg;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            //Open a connection to the url
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
            conn.setDoOutput( true );
            //Set POST request
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            
           return "";
         }
         catch(Exception e){
             return "Exception "+e;
         }
     } 
    
}
