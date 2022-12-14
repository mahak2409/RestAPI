package com.example.demoRest;

/*
 * This Rest API add,update,delete and get user details i.e. userId and name from PostgreSQL
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> findAllUser(){
		
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<User> findUserById(@PathVariable(value="id") long id){
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public User saveUser(@Validated @RequestBody User user) {
		// System.out.println();
		
		return userRepository.save(user);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteById(@PathVariable(value="id") long id){
		 userRepository.deleteById(id);
		
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void updateUser(@Validated @RequestBody User user) {
		
		
		Optional<User> userDbObj = userRepository.findById(user.getId());
		User obj = userDbObj.get();
		
		System.out.println("DBObj "+userDbObj+" objID "+obj.getId()+" name "+obj.getName());

		obj.setName(user.getName());
		userRepository.save(obj);
		
		System.out.println(" objID "+obj.getId()+" name "+obj.getName());

		
	}

}
