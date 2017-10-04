<%-- 
    Document   : Hello
    Created on : Aug 26, 2017, 11:37:25 PM
    Author     : Franky Naidu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DataBase" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <center>EXOTEL TRY Incoming Call</center><hr>
            <form id="exotelform" action="addExotel">
                <label for="SmsSid">Smsid</label><input id="SmsSid" name="SmsSid"><br>
                <label for="From">Sender</label><input id="From" name="From"><br>
                <label for="To">receiver</label><input id="To" name="To"><br>
                <label for="Date">date</label><input id="Date" name="Date"><br>
                <label for="Body">body</label><input id="Body" name="Body"><br>
                <button type="submit" name="submit">Submit  </button>
            </form>
        </div>
    </body>
</html>
