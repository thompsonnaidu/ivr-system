/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author Franky Naidu
 */
public class ExotelDAO {
     private Connection con;
     private final String EXOTEL_SID = "xxxxx"; // Your Exotel SID
     private final String EXOTEL_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // Your exotel token
     private final static String INSERT_EXOTEL = "INSERT INTO incomingsms(`smsSid`, `sender`, `receiver`, `date`, `body`) VALUES(?,?,?,?,?) ";
     private final static String GET_ALL_EXOTEl = "select * from incomingsms";
     private final static String INSERT_INCOMING_CALL = "INSERT INTO `incomingCall`(`callid`, `caller`,`registerphone`) VALUES (?,?,?)";
     private final static String GET_INCOMING_CALL = "Select * from `incomingCall`  where `registerphone` like ?";
     
     public ExotelDAO(){
         con = DataBase.getConnection();
     }
     public String insertRecord(Map data){         
                String msg="";
         try {
                PreparedStatement ps = con.prepareStatement(INSERT_EXOTEL);
                ps.setString(1,(String)data.get("SmsSid"));
                ps.setString(2,(String)data.get("sender"));
                ps.setString(3,(String)data.get("receiver"));
                ps.setString(4,(String)data.get("date"));
                ps.setString(5,(String)data.get("body"));
                System.out.println("Hello"); 
                if (ps.executeUpdate() >0 ){
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
         System.out.println(""+msg);
        return msg;
     }
     public String insertCallRecord(Map data){
                String msg="";
         try {
             
                PreparedStatement ps = con.prepareStatement(GET_INCOMING_CALL);
                ps.setString(1, (String)data.get("digits"));
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                     msg = "{\"select\":\"registered\"}";
                    return msg;
                }
                else{
                        ps = con.prepareStatement(INSERT_INCOMING_CALL);
                        ps.setString(1,(String)data.get("CallSid"));
                        ps.setString(2,(String)data.get("From"));
                        ps.setString(3, (String)data.get("digits"));
                        if (ps.executeUpdate() >0 ){
                            msg = "{\"select\":\"success\"}";
                            return msg;
                        }
                        else{
                            msg = "{\"select\":\"fail\"}";
                            return msg;
                        }
                }
                 
            } 
            catch (Exception e) {
                System.out.println(""+e.getMessage());
            }
         return msg;
     }
     
     public String sendSms(Map data){
         try{
            String from = "From="+(String)data.get("From")+"&";
            String[] toarray = (String [])data.get("To");
            String to="";
             for (String todata : toarray) {
                 to +="To[]="+todata+"&"; 
             }
            String msg = "Body="+data.get("Body");
           String link = "https://"+EXOTEL_SID+":"+EXOTEL_TOKEN+"@twilix.exotel.in/v1/Accounts/"+EXOTEL_SID+"/Sms/send";
            //String link="http://localhost/tawk/test.php";
             System.out.println(""+link);
            URL url = new URL(link);            
            String urlParameters = from+to+msg;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
            conn.setDoOutput( true );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
               wr.write( postData );
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer result = new StringBuffer();
            String line;
            while((line=br.readLine())!= null){
                result.append(line);    
            }
            br.close();
           return ""+result;
         }
         catch(Exception e){
             return "Exception "+e;
         }
     } 
}
