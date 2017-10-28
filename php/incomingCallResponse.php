<?php

// Database details
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "exotel";

//Set the content type to text/plain this is must
header('Content-Type: text/plain');
// HEAD request is sent by exotel to verify the headers
if ($_SERVER['REQUEST_METHOD'] == 'HEAD') {
	exit();
}	
//Fetching the GET params
//Request url:- http://localhost:8000/incomingCallResponse.php?CallSid=9874562&From=78945620
$CallSid = $_GET["CallSid"];
$From = $_GET["From"];
$digit = trim($_GET["digits"],'"');

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "select * from `incomingCall` where `registerphone` like '".$digit."' ";
$result = $conn->query($sql);
//If number of rows is greater than zero it indicates user is already registered
if ($result->num_rows > 0) {
    // output data of each row
    header("HTTP/1.1 200 OK");
	echo '{"select":"registered"}';
}
 else {
 	$sql = "INSERT INTO `incomingCall`(`callid`, `caller`,`registerphone`) VALUES ('".$CallSid."','".$From."','".$digit."')";
    if ($conn->query($sql) === TRUE) {
		header("HTTP/1.1 200 OK");
	    echo '{"select":"success"}';
	} else {
		header("HTTP/1.1 501 NOTOK");
	    echo '{"select":"fail"}';
	}
}


$conn->close();
?>
