<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "exotel";
	//content type must be set to text/plain
	header('Content-Type: text/plain');
	//exotel sends a HEAD request to verify the headers
	if ($_SERVER['REQUEST_METHOD'] == 'HEAD') {
		exit();
	}	

	
	//http://localhost:8080/incomingSms.php?SmsSid=132456&From=9052161119&To=56070&Date=12/2/2017&Body=Hello+World
	//Extratct the GEt parameters from url 
	$SmsSid = $_GET["SmsSid"];
	$From = $_GET["From"];
	$To = $_GET["To"];
	$Date = $_GET["Date"];
	$Body = $_GET["Body"];
?>