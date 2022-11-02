package br.edu.ifs.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifs.course.entities.Product;
import br.edu.ifs.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	/*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
	public ResponseEntity<List<Product>> findAll() {
		List<Product> userList = productService.findAll();
		return ResponseEntity.ok().body(userList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product user = productService.findById(id);
		return ResponseEntity.ok().body(user);
	}

}
