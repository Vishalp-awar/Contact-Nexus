package com.ContactNexus.Contollers;


import com.ContactNexus.Forms.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/contacts")
public class ContactController {

    @RequestMapping("/contact")
    public String contactPage(){
        return "user/contact";
    }
    @RequestMapping("/add_contact")
    public String addContactPage(Model model){
        ContactForm contactForm=new ContactForm();
        contactForm.setContactImage("this is imahge");
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="add_contact" ,method = RequestMethod.POST)
    public String SaveContact(@ModelAttribute ContactForm contactForm){
        System.out.println(
                contactForm
        );
        return "redirect:/user/contacts/add_contact";
    }

}
