package com.ContactNexus.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Security {


    @Bean
    public UserDetailsService userDetailsService() {

       UserDetails user1 = User
               .withDefaultPasswordEncoder()
               .username("vishal")
               .password("vishal").
               build();

            var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
        return inMemoryUserDetailsManager;
    }
}
