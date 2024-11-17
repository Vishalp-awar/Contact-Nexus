package com.ContactNexus.Configuration;


import com.ContactNexus.Services.Implementation.SecurityCustomDetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//    this autowire for oauth2 google
    @Autowired
    private OAuthAuthenicationSuccessHandler successHandler;



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
//
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
////    httpSecurity
////            .authorizeHttpRequests(authorize -> authorize
////                    // Allow access to public pages
//////                    .requestMatchers("/Register", "/home").permitAll()
////                    // Require authentication for `/user/**` routes
////                    .requestMatchers("/user/**").authenticated()
////                    // Allow all other requests
////                    .anyRequest().permitAll()
////            )
////            // Enable form login
////            .formLogin(Customizer.withDefaults());
////
////    return httpSecurity.build();
//    httpSecurity.authorizeHttpRequests(authorize -> {
//        // authorize.requestMatchers("/home", "/register", "/services").permitAll();
//        authorize.requestMatchers("/user/**").authenticated();
//        authorize.anyRequest().permitAll();
//    });
//
//    // form default login
//    // agar hame kuch bhi change karna hua to hama yaha ayenge: form login se
//    // related
//    httpSecurity.formLogin(formLogin -> {
//
//        //
//        formLogin.loginPage("/login");
//        formLogin.loginProcessingUrl("/authenticate");
//        formLogin.successForwardUrl("/user/profile");
//        // formLogin.failureForwardUrl("/login?error=true");
//        // formLogin.defaultSuccessUrl("/home");
//        formLogin.usernameParameter("email");
//        formLogin.passwordParameter("password");
//
//        // formLogin.failureHandler(new AuthenticationFailureHandler() {
//
//        // @Override
//        // public void onAuthenticationFailure(HttpServletRequest request,
//        // HttpServletResponse response,
//        // AuthenticationException exception) throws IOException, ServletException {
//        // // TODO Auto-generated method stub
//        // throw new UnsupportedOperationException("Unimplemented method
//        // 'onAuthenticationFailure'");
//        // }
//
//        // });
//
//        // formLogin.successHandler(new AuthenticationSuccessHandler() {
//
//        // @Override
//        // public void onAuthenticationSuccess(HttpServletRequest request,
//        // HttpServletResponse response,
//        // Authentication authentication) throws IOException, ServletException {
//        // // TODO Auto-generated method stub
//        // throw new UnsupportedOperationException("Unimplemented method
//        // 'onAuthenticationSuccess'");
//        // }
//
//        // });
////        formLogin.failureHandler(authFailtureHandler);
//
//    });
//
//
//}
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//last working ****************************************
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorize -> {
//            // Public pages
//            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
//            // Restrict `/user/**` routes to authenticated users
//            authorize.requestMatchers("/user/**").authenticated();
//            // Allow all other requests
//            authorize.anyRequest().permitAll();
//        });
//
//        // Configure form login
//        httpSecurity.formLogin(formLogin -> {
//            formLogin.loginPage("/login")
//                    .loginProcessingUrl("/authenticate")
//                    .successForwardUrl("/home")
//                    .failureForwardUrl("/login?error=true")
//                    .defaultSuccessUrl("/user/profile")
//                    .usernameParameter("email")
//                    .passwordParameter("password");
//
//            // Uncomment and implement custom handlers if needed
//            // formLogin.failureHandler(new AuthenticationFailureHandler() {
//            //     @Override
//            //     public void onAuthenticationFailure(HttpServletRequest request,
//            //                                         HttpServletResponse response,
//            //                                         AuthenticationException exception)
//            //             throws IOException, ServletException {
//            //         // Custom failure handling logic
//            //     }
//            // });
//
//            // formLogin.successHandler(new AuthenticationSuccessHandler() {
//            //     @Override
//            //     public void onAuthenticationSuccess(HttpServletRequest request,
//            //                                         HttpServletResponse response,
//            //                                         Authentication authentication)
//            //             throws IOException, ServletException {
//            //         // Custom success handling logic
//            //     }
//            // });
//            // formLogin.failureHandler(authFailtureHandler);
//        });
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        httpSecurity.logout(logoutForm -> {
//            logoutForm.logoutUrl("/do-logout");
//            logoutForm.logoutSuccessUrl("/login?logout=true");
//        });
//        return httpSecurity.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.authorizeHttpRequests(authorize -> {
        // Public pages
        authorize.requestMatchers("/login", "/register", "/home", "/services","/css/**", "/js/**", "/images/**").permitAll();
        // Secure `/user/**` routes
        authorize.requestMatchers("/user/**").authenticated();
        // Allow all other requests
        authorize.anyRequest().permitAll();
    });

    httpSecurity.formLogin(formLogin -> {
        formLogin.loginPage("/login") // Login page URL
                .loginProcessingUrl("/authenticate") // Form action URL for processing login
                .defaultSuccessUrl("/", true) // Redirect to `/user/profile` on success
                .failureUrl("/login?error=true") // Redirect back to login on failure
                .usernameParameter("email") // Field name for username
                .passwordParameter("password"); // Field name for password
    });

    httpSecurity.csrf(AbstractHttpConfigurer::disable); // Disable CSRF for debugging (enable in production)

    httpSecurity.logout(logoutForm -> {
        logoutForm.logoutUrl("/do-logout") // Logout URL
                .logoutSuccessUrl("/login?logout=true"); // Redirect after logout
    });

    //oauth configuration
    httpSecurity.oauth2Login(oauth ->{
        oauth.loginPage("/login");
        oauth.successHandler(successHandler);
    });

    return httpSecurity.build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
