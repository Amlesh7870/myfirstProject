package in.spring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.spring.springboot.dao.UserDAORepository;
import in.spring.springboot.entity.Users;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserDAORepository userDAORepository;
	
	@GetMapping("/{id}")
	public Users findStudentById(@PathVariable int id) {
		System.out.println("working fine");
		return userDAORepository.findById(id).orElse(new Users());

	}

	@GetMapping("/findAll")
	public List<Users> findAllUser(){
	List<Users> allUser=	userDAORepository.findAll();
	return allUser;
	}
	
	@PostMapping("/save")
	public String save(@RequestBody Users users) {
		
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		userDAORepository.save(users);
		return "Success";
	}
}
