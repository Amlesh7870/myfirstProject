package in.spring.springboot.springsecurity1;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.spring.springboot.helper.JWTHelper;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private JWTHelper jwtHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String userName=null;
		String jwtToken;
		
		if(header!=null && header.startsWith("Bearer ") ) {
		 jwtToken = header.substring(7).trim();
		
		userName = jwtHelper.extractUsername(jwtToken);
		UserDetails userByUsername = customUserDetailsService.loadUserByUsername(userName);
		if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userByUsername,null,userByUsername.getAuthorities());
			authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticated);
		}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
