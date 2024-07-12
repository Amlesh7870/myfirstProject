package in.spring.springboot.service;

import in.spring.springboot.entity.Users;

public interface UserService {
	Users findUserByUserName(String username);
}
