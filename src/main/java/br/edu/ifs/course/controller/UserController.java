package br.edu.ifs.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifs.course.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@GetMapping()
	public ResponseEntity<User> findAll() {
		User user = new User(1L, "Joao", "joao@gmail.com", "999875651", "1234");
		
		return ResponseEntity.ok().body(user);
		
	}

}
