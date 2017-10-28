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
		$exotel_sid = "xxxxxxx"; // Your Exotel SID say 97946
		$exotel_token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // Your exotel token say aksdsa5fsa66saf

		//Sample Request url  looks like https://97946:aksdsa5fsa66saf@twilix.exotel.in/v1/Accounts/97946/Sms/send
		$url = "https://".$exotel_sid.":".$exotel_token."@twilix.exotel.in/v1/Accounts/".$exotel_sid."/Sms/send";

		//set the curl request
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_VERBOSE, 1);
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_FAILONERROR, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
		curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($post_data));

		//Execute the curl request
		$http_result = curl_exec($ch);

		//Get the details of request		
		$error = curl_error($ch);
		$http_code = curl_getinfo($ch ,CURLINFO_HTTP_CODE);

 		//Close the curl connection request (destroy it)
		curl_close($ch);		 

		//Print the response or use conditional operater to perfrom certain task when we need only
		print "Response = ".print_r($http_result);

		//end of the function
	}
	
	//usage:- To send SMS to a single receiver
	// sendSms("1234567890","7132456780","Hey Your delivery is been sent to user");
	//usage:- To send  Sums to more than one receiver (Bulk SMS)
	// sendSms("1234567890", array('7468120613', '7614839520'),"Open house on Monday please be there");

	sendSms("",array("9511763048","9762417643"),"Hi Franky, your number 951764523 is now turned on.");
?>
