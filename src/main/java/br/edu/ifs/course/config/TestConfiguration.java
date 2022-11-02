package br.edu.ifs.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifs.course.entities.Category;
import br.edu.ifs.course.entities.Order;
import br.edu.ifs.course.entities.OrderItem;
import br.edu.ifs.course.entities.Payment;
import br.edu.ifs.course.entities.Product;
import br.edu.ifs.course.entities.Role;
import br.edu.ifs.course.entities.User;
import br.edu.ifs.course.entities.enums.OrderStatus;
import br.edu.ifs.course.entities.enums.RoleName;
import br.edu.ifs.course.repositories.CategoryRepository;
import br.edu.ifs.course.repositories.OrderItemRepository;
import br.edu.ifs.course.repositories.OrderRepository;
import br.edu.ifs.course.repositories.ProductRepository;
import br.edu.ifs.course.repositories.RoleRepository;
import br.edu.ifs.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456","mmBrown", false, false, false, true);
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456", "alexG", false, false, false, true);
		/*
		Desabilitando a encriptação do password
		u1.setPassword(passwordEncoder.encode(u1.getPassword()));
		u2.setPassword(passwordEncoder.encode(u2.getPassword()));*/
		
		Role role1 = new Role(null, RoleName.ADMIN);
		Role role2 = new Role(null, RoleName.USER);
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.DELIVERED);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.SHIPPED);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2);
		OrderItem oi2 = new OrderItem(o1, p3, 1);
		OrderItem oi3 = new OrderItem(o2, p3, 2);
		OrderItem oi4 = new OrderItem(o3, p5, 2);
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		roleRepository.saveAll(Arrays.asList(role1, role2));
		
		u1.getRoles().add(role1);
		u2.getRoles().add(role2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		
		o1.setPayment(pay1);

		orderRepository.save(o1);

	}

}
