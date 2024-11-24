package com.ContactNexus.Contollers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user/contacts")
public class ContactController {

    @RequestMapping("/contact")
    public String contactPage(){
        return "user/contact";
    }
    @RequestMapping("/add_contact")
    public String addContactPage(){
        return "user/add_contact";
    }
}
