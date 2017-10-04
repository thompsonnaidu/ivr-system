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
		$exotel_sid = "xxxxx"; // Your Exotel SID say 97946
		$exotel_token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // Your exotel token say aksdsa5fsa66saf

		//Sample Request url  looks like https://97946:aksdsa5fsa66saf@twilix.exotel.in/v1/Accounts/97946/Sms/send
		$url = "https://".$exotel_sid.":".$exotel_token."@twilix.exotel.in/v1/Accounts/".$exotel_sid."/Sms/send";
		
	}
	


?>