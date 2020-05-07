package com.webupps.custom.app.resource;

import java.util.Collections;
import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.UsersRepository;
import com.webupps.custom.app.service.EmailValidatorService;
import com.webupps.custom.app.service.PhoneValidatorService;

@RestController
@RequestMapping("/v1/users")
public class UsersResource {
	
	@Autowired
    UsersRepository usersRepository;
	
	@Autowired
	private PhoneValidatorService phoneValidatorService;
	

	
	@GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
	
    @PostMapping("/add")
    public ResponseEntity<Users> addUsers(@RequestBody Users users) {
       // Users updated = service.createOrUpdateEmployee(employee);
    	if(phoneValidatorService.validatePhoneNumber(users.getPhone())) {
    		 // return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
		} else  {  
			//return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
		}
    	
    	if(EmailValidatorService.isValidEmailAddress(users.getUsername())){
    		return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
    	} else { 
    		return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	// users.setPassword(new BCryptPasswordEncoder(users.getPassword()););
    	//usersRepository.save(users);
        //return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
    }
    /*
    @PostMapping("/add")
	public @ResponseBody Map<String,Boolean> addNewPhone(@Valid @RequestBody Phone obj) {			
		if(phoneValidatorService.validatePhoneNumber(obj.getNumber())) {
			phoneService.addPhone(obj);
		}
		return Collections.singletonMap("succeed", phoneValidatorService.validatePhoneNumber(obj.getNumber()));
	} */
}
