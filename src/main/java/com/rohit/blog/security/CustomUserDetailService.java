package com.rohit.blog.security;

import com.rohit.blog.exceptions.ResourceNotFoundException;
import com.rohit.blog.models.BlogUser;
import com.rohit.blog.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load userName from Db
        BlogUser user;
        user = this.userRepo.findByName(username).orElseThrow(() -> new ResourceNotFoundException("User", " Name: " + username, 0));
        return user;
    }
}
