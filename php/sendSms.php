<?php

	//Call sendSms when you want to send a message
	// $From :- The sender mobile number.It is automatically replaced by the exotel mobile number
	// $To :- The mobile number of the receiver.
	// $msg :- The sms the sender wants to send.
	function sendSms($From,$To,$msg)
	{
		// To send an sms we make a post request to url specified by exotel

		//Set the Post data
		$post_data = array(	    
		    'From'   => $From,
		    'To'    => $To,
		    'Body'  => $msg,
		);

		//Replace your exotel credentials provided by exotel
		$exotel_sid = "xxxxx"; // Your Exotel SID
		$exotel_token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // Your exotel token
	}
	


?>