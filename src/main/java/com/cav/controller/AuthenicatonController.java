package com.cav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cav.config.JwtUtil;
import com.cav.dto.AuthenticationRequest;
import com.cav.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthenicatonController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public String authenticate(@RequestBody AuthenticationRequest request) {
		
		 authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
         );
		 final UserDetails user = userRepository.findUserDetail(request.getEmail());
		 if(user !=null) {
			 return jwtUtil.generateToken(user);
		 }
		return "failed Token Generate";
		
	}

}
