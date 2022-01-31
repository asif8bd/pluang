package com.ashid.pluang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashid.pluang.model.UsersHelper;
import com.ashid.pluang.model.UsersPojo;
import com.ashid.pluang.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {
	
	 @Autowired
	 UsersRepository usersRepo;

	   @Override
	   public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
	      UsersPojo usersPojo = null;
	      try {
	    	  usersPojo = usersRepo.getUserDetails(username);
	    	  UsersHelper userDetail = new UsersHelper(usersPojo);
	         return userDetail;
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new UsernameNotFoundException("User " + username + " was not found in the database");
	      }
	   }

}
