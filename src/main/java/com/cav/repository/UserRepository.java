package com.cav.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

	//Replace this with database table of users
		private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
				new User("admin@hotmail.com",
						"password",
						Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
				new User("client@hotmail.com",
						"password",
						Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT"))),
				new User("support@hotmail.com",
						"password",
						Collections.singleton(new SimpleGrantedAuthority("ROLE_SUPPORT"))));
		
		
		public UserDetails findUserDetail(String email) {
			UserDetails userDetails= APPLICATION_USERS.stream()
					.filter(u ->u.getUsername().equals(email))
					.findFirst().get();
			return userDetails;
		}
}
