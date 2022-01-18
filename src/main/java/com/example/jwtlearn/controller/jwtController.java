package com.example.jwtlearn.controller;

import com.example.jwtlearn.model.jwtReq;
import com.example.jwtlearn.model.jwtRes;
import com.example.jwtlearn.services.CustomUserDetailsService;
import com.example.jwtlearn.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jwtController {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private jwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String getAuth(){
        return "Hello World! Server Check";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody jwtReq authReq) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword())
            );
        } catch (BadCredentialsException e){
            System.out.println("Authorization failed");
            throw new Exception("Incorrect Username or Password",e);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authReq.getUsername());

        final  String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Authorization API Success");

        return ResponseEntity.ok(new jwtRes(jwt));
    }

//    @PostMapping("/addAuth")
//    public String addAuth(@RequestBody jwtReq authReq) throws Exception{
//        userDetailsService.ChangeUsernamePassword(authReq.getUsername(),authReq.getPassword());
//        userDetailsService.loadUserByUsername(authReq.getUsername());
//        return "User Added";
//    }
}
