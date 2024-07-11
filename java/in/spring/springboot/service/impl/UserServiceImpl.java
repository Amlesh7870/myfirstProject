package in.spring.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spring.springboot.dao.UserDAORepository;
import in.spring.springboot.entity.Users;
import in.spring.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAORepository userDAORepository;
	
	
	public Users findUserByUserName(String username) {
		return userDAORepository.findUserByUserName(username);
	}
}
