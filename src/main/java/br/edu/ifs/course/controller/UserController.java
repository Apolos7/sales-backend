package br.edu.ifs.course.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifs.course.entities.User;
import br.edu.ifs.course.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/users")
@SecurityRequirement(name = "sales")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	/*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
	@Operation(summary = "Find all users.")
	public ResponseEntity<List<User>> findAll() {
		List<User> userList = userService.findAll();
		return ResponseEntity.ok(userList);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Find a user by id.")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping()
	@Operation(summary = "Register a user in the system.")
	public ResponseEntity<User> register(@RequestBody User user) {
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a user from the system.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update user information that's is present in the system.")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		user = userService.update(id, user);
		return ResponseEntity.ok(user);
		
	}
	
}
