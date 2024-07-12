package in.spring.springboot.springsecurity1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.spring.springboot.entity.Users;
import in.spring.springboot.service.UserService;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users userData = userService.findUserByUserName(username);
		if(userData==null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(userData);
		
	}

}
