package com.ContactNexus.Contollers;


import com.ContactNexus.Entities.User;
import com.ContactNexus.Helper.Helper;
import com.ContactNexus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    private UserService userService;




    @RequestMapping(value ="/dashboard",method = RequestMethod.GET )
    public String dashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value ="/profile",method = RequestMethod.GET )
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }
}
