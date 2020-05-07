package com.webupps.custom.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.UsersRepository;

@RestController
@RequestMapping("/v1/users")
public class UsersResource {
	
	@Autowired
    UsersRepository usersRepository;
	
	@GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
	
    @PostMapping
    public ResponseEntity<Users> addUsers(Users users) {
       // Users updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<Users>(users, new HttpHeaders(), HttpStatus.OK);
    }
}
