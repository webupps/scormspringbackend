 package com.webupps.custom.app.resource;


import java.util.List;


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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.CheckUserRepository;
import com.webupps.custom.app.repository.UsersRepository;
import com.webupps.custom.app.service.EmailValidatorService;
import com.webupps.custom.app.service.PhoneValidatorService;
import com.webupps.custom.app.service.UserAlreadyExistAuthenticationException;


@RestController
@RequestMapping("/v1/users")
public class UsersResource {
	
	@Autowired
    UsersRepository usersRepository;
	
	@Autowired
	CheckUserRepository checkUserRepository;
	
	@Autowired
	private PhoneValidatorService phoneValidatorService;
	
	@Autowired

	
	@GetMapping("/test")
    public String test() {
        return ("test");
    }
	
	@GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
	
    @PostMapping("/add")
    public ResponseEntity<Users> addUsers(@RequestBody Users users) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    	String encodedPassword = passwordEncoder.encode(users.getPassword());
    	System.out.print(encodedPassword);
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
    
    @PostMapping("/registration")
    public  ResponseEntity<?> registerUserAccount (@RequestBody Users users) {
    	System.out.println("testing");
           System.out.println(emailExist(users.getUsername()));
           System.out.println("testing");
        if (emailExist(users.getUsername())) {  
            throw new UserAlreadyExistAuthenticationException(
              "There is an account with that email address: "
              +  users.getUsername());
        } else { 
        	
           // Users updated = service.createOrUpdateEmployee(employee);
        	if(phoneValidatorService.validatePhoneNumber(users.getPhone()) || EmailValidatorService.isValidEmailAddress(users.getUsername())) {
        		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
            	String encodedPassword = passwordEncoder.encode(users.getPassword());
            	//users.setRole("ROLE_USER");
        		users.setPassword(encodedPassword);
            	usersRepository.save(users);
        		return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
    		} else  {  
    			return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    		}
        	
        	
        }
   
    }
    	
	private boolean emailExist(String username) {
        return checkUserRepository.findByUsername(username) != null;
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
