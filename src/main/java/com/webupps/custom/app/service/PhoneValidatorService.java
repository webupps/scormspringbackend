package com.webupps.custom.app.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneValidatorService {
	
	public static void main(String[] args) {
	}

	public boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\ds{10}")) return true;
		//else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{4}")) return true;
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		else return false;
		
	}
}
