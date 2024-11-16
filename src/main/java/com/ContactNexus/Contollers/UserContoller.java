package com.ContactNexus.Contollers;


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
    public String userProfile() {
        return "user/profile";
    }
}
