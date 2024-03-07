package com.ldtech.security;

import com.ldtech.entities.Role;
import com.ldtech.entities.User;
import com.ldtech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not found with email " + email));

        User user = (User) userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not found with email:" + email));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),true, true, true, true, Collections.emptyList());

        return new org.springframework.security.core.userdetails.User(user.getCompanyEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

//        return (UserDetails) user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}