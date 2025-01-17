package in.spring.springboot.springsecurity1;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.spring.springboot.entity.Users;

public class CustomUserDetails implements UserDetails {

	private Users users;
	CustomUserDetails (Users users){
		this.users=users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> data= new HashSet();
		data.add(new SimpleGrantedAuthority(this.users.getRole()));
		return data;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.users.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
