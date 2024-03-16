package com.ldtech.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomUserDetailsService();
//    }
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
}



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configures CSRF to be available for JavaScript to read from cookies
                .csrf(csrf -> csrf.disable())
                // Configures authorization rules
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/**").permitAll() // Combines matchers for permitAll
                        .anyRequest().authenticated()
                )
                // Configures HTTP Basic authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // For encoding password
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
