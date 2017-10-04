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
?>