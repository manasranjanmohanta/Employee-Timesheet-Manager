package com.ldtech.Authentication;

import com.ldtech.dtos.LoginDto;
import com.ldtech.dtos.SignUpDto;
import com.ldtech.entities.Role;
import com.ldtech.entities.User;
import com.ldtech.repositories.RoleRepository;
import com.ldtech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // API for login
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return  new ResponseEntity<>("User Signed in successfully!!", HttpStatus.OK);
    }

    // API for signup
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // Checking email exists in the database or not
        if(userRepository.existsByCompanyEmail(signUpDto.getUserEmail())){
            System.out.println(passwordEncoder.encode(signUpDto.getPassword()));
            return new ResponseEntity<>("Email is already taken!!!", HttpStatus.BAD_REQUEST);
        }

        // Creating new user
        User user = new User();
        user.setCompanyEmail(signUpDto.getUserEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        Role roles = null;
        try {
            roles = roleRepository.findByRoleName("ROLE_ADMIN").get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!!!", HttpStatus.OK);

    }
}
