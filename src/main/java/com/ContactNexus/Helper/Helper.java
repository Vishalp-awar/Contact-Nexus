//package com.ContactNexus.Helper;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//
//import java.security.Principal;
//
//public class Helper {
//
//    public static String getEmailOfLoggedUser(Authentication authentication) {
//
//        Principal principal = (Principal) authentication.getPrincipal();
//
//        if (principal instanceof OAuth2AuthenticatedPrincipal) {
//            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
//            String clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
//
//            if (clientId.equalsIgnoreCase("google")) {
//                System.out.println("User logged in from Google");
//                return ((OAuth2AuthenticatedPrincipal) principal).getAttribute("email");
//            }
//        } else {
//            System.out.println("User logged in from email");
//            return principal.getName();
//        }
//
//        return null;
//    }
//}
package com.ContactNexus.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class Helper {

//    @Value("${server.baseUrl}")
//    private String baseUrl;

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // agar email is password se login kiya hai to : email kaise nikalenge
        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {

                // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();

            }

            // sign with facebook
            return username;

        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

    }


}