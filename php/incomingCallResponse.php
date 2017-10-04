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
//Request url:- http://localhost:8000/incomingCallResponse?CallSid=9874562&From=78945620
$CallSid = $_GET["CallSid"];
$From = $_GET["From"];

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO `incomingCall`(`callid`, `caller`) VALUES ('".$CallSid."','".$From."')";

if ($conn->query($sql) === TRUE) {
	header("HTTP/1.1 200 OK");
    echo "Thank You for Registering";
} else {
	header("HTTP/1.1 501 NOTOK");
    echo "Some error occured while registering"; 
}

$conn->close();
?>