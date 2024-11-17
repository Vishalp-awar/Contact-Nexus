package com.ContactNexus.Contollers;


import com.ContactNexus.Helper.Helper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserContoller {

    @RequestMapping(value ="/dashboard",method = RequestMethod.GET )
    public String dashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value ="/profile",method = RequestMethod.GET )
    public String userProfile(Authentication authentication) {
        String name = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println(name);
        return "user/profile";
    }
}
