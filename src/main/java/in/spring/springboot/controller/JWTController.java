package in.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.spring.springboot.helper.JWTHelper;
import in.spring.springboot.model.JWTRequest;
import in.spring.springboot.model.JWTResponse;
import in.spring.springboot.springsecurity1.CustomUserDetailsService;

@RestController
@RequestMapping("/token")
public class JWTController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private JWTHelper jwtHelper;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/t")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Bad credentials", e);
        }
        
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtHelper.generateToken(userDetails);
        System.out.println(token);
        
        return ResponseEntity.ok(new JWTResponse(token));
    }
}
