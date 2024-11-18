package com.ContactNexus.Contollers;

import com.ContactNexus.Entities.User;
import com.ContactNexus.Helper.Helper;
import com.ContactNexus.Services.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootContoller {

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication == null){
          return;
        }

        String name = Helper.getEmailOfLoggedInUser(authentication);

        User user =userService.getUserByEmail(name);
        model.addAttribute("loggedinuser",user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
    }
}
