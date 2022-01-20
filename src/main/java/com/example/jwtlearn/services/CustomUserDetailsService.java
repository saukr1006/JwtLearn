package com.example.jwtlearn.services;

import com.example.jwtlearn.model.CustomUserDetails;
import com.example.jwtlearn.model.User;
import com.example.jwtlearn.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    String username="foo";
    String password="foo";

    public void ChangeUsernamePassword(String u1,String p1){
        username=u1;
        password=p1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User Not found");

        return new CustomUserDetails(user);
    }
}
