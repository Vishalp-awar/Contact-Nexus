package com.ContactNexus.Configuration;

import com.ContactNexus.Entities.Providers;
import com.ContactNexus.Entities.User;
import com.ContactNexus.Helper.AppConstants;
import com.ContactNexus.Repositories.UserRepositories;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepositories  userRepositories;

    Logger logger= LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("Authentication success");

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        logger.info(user.getName());

        user.getAttributes().forEach((key,value) ->{
            logger.info(key+"   =   "+value);
        });
        String username = user.getAttribute("name");
        String pitcure = user.getAttribute("picture");
        String email = user.getAttribute("email");

//        creating instance of user object to store this data in db
        User user1 = new User();
         user1.setName(username);
         user1.setEmail(email);
         user1.setPassword("password");
         user1.setProfilePic(pitcure);
         user1.setUserId(UUID.randomUUID().toString());
         user1.setProvider(Providers.GOOGLE);
         user1.setEmailVerified(true);
         user1.setProviderUserId(user.getName());
         user1.setRoleList(List.of(AppConstants.ROLE_USER));
         user1.setAbout("this account is created using Google");

        User userdb= userRepositories.findByEmail(email).orElse(null);
            if(userdb==null){
                userRepositories.save(user1);
                logger.info("User saved to db");
            }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
