package com.ContactNexus.Contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashMap;
import java.util.Map;
@Controller
public class PageContoller {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home");

        // Create a map and add attributes
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "vvishal");

        // Add the attributes to the model
        model.addAllAttributes(attributes);

        // Return the view name
        return "home";
    }


    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }
    @RequestMapping("/services")
    public String servicePage(){
        return "services";
    }

    @RequestMapping("/Login")
    public String LoginPage(){
        return "Login";
    }

    @RequestMapping("/Register")
    public String SignupPage(){
        return "Register";
    }
    @RequestMapping("/contactus")
    public String ContactusPage(){
        return "contactus";
    }
}
