package com.webupps.custom.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.UsersRepository;
import com.webupps.custom.app.service.EmailValidatorService;
import com.webupps.custom.app.service.PhoneValidatorService;
import com.webupps.custom.app.service.UserAlreadyExistAuthenticationException;

@Service
public class UserRegistrationService {
	
	@Autowired
    UsersRepository usersRepository;
	
	@Autowired
	private PhoneValidatorService phoneValidatorService;
	
	public static void main(String[] args) {
	}
	
	@Transactional
    public ResponseEntity<?> registerNewUserAccount(Users users) 
      throws UserAlreadyExistAuthenticationException {
         
        if (emailExist(users.getUsername())) {  
            throw new UserAlreadyExistAuthenticationException(
              "There is an account with that email address: "
              +  users.getUsername());
        } else { 
        	
           // Users updated = service.createOrUpdateEmployee(employee);
        	if(phoneValidatorService.validatePhoneNumber(users.getPhone()) || EmailValidatorService.isValidEmailAddress(users.getUsername())) {
        		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
            	String encodedPassword = passwordEncoder.encode(users.getPassword());
        		users.setPassword(encodedPassword);
            	usersRepository.save(users);
        		return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
    		} else  {  
    			return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    		}
        	
        	
        }
        
    }
    private boolean emailExist(String username) {
        return usersRepository.findByUsername(username) != null;
    }
}
