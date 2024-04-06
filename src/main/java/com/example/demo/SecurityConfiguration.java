package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.PreAuthorizeFilter;
import com.example.demo.service.CustomUserDtlSvc;

@EnableWebSecurity
@EnableMethodSecurity
//securedEnabled = true for @Secured , jsr250Enabled = true for @RolesAllowed, prePostEnabled = true for @Pre/PostAuthorize, @Pre/PostFilter
@Configuration
//@EnableAsync
public class SecurityConfiguration {

    @Autowired
    PreAuthorizeFilter preAuthorizeFilter;

//    @Autowired
//    CustomUserDtlSvc svc;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home/greet/**", "/home/generateToken", "/home/addUser").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN").requestMatchers("/jwt/**")
                        .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('USER')"))
                        .requestMatchers("/dba/**")
                        .access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"),
                                AuthorityAuthorizationManager.hasAnyAuthority("DBA")))
                        .anyRequest().denyAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(preAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
//    @Async
    UserDetailsService detailsService() {
        return new CustomUserDtlSvc();

    }


    @Bean
    GrantedAuthorityDefaults authorityDefaults() {
        return new GrantedAuthorityDefaults(""); // provide impl if want to remove ROLE_ prefix
    }
}

//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
//    return http.csrf().disable() 
//            .authorizeHttpRequests() 
//            .requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll() 
//            .and() 
//            .authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated() 
//            .and() 
//            .authorizeHttpRequests().requestMatchers("/auth/admin/**").authenticated() 
//            .and() 
//            .sessionManagement() 
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
//            .and() 
//            .authenticationProvider(authenticationProvider()) 
//            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) 
//            .build(); 
//} 