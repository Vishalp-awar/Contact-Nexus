package com.ContactNexus.Configuration;


import com.ContactNexus.Services.Implementation.SecurityCustomDetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//       UserDetails user1 = User
//               .withDefaultPasswordEncoder()
//               .username("vishal")
//               .password("vishal").
//               build();
//
//            var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
//        return inMemoryUserDetailsManager;
//    }

    @Autowired
    private SecurityCustomDetailUserService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorize ->{
////   allow access as public page         authorize.requestMatchers("/Register","/home").permitAll();
//        authorize.requestMatchers("/user/**").authenticated();
//        authorize.anyRequest().permitAll();
//        });
//
//        httpSecurity.formLogin(Customizer.withDefaults());
//
//        return httpSecurity.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .authorizeHttpRequests(authorize -> authorize
                    // Allow access to public pages
//                    .requestMatchers("/Register", "/home").permitAll()
                    // Require authentication for `/user/**` routes
                    .requestMatchers("/user/**").authenticated()
                    // Allow all other requests
                    .anyRequest().permitAll()
            )
            // Enable form login
            .formLogin(Customizer.withDefaults()) // Enable default login form
            .logout(Customizer.withDefaults());  // Optionally enable logout functionality


    return httpSecurity.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
