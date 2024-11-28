package com.ContactNexus.Contollers;


import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import com.ContactNexus.Forms.ContactForm;
import com.ContactNexus.Helper.Alert;
import com.ContactNexus.Helper.AlertType;
import com.ContactNexus.Helper.Helper;
import com.ContactNexus.Services.ContactService;
import com.ContactNexus.Services.ImageService;
import com.ContactNexus.Services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private  ImageService  imageService;
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/contact")
    public String contactPage(){
        return "user/contact";
    }
    @RequestMapping("/add_contact")
    public String addContactPage(Model model){
        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="add_contact" ,method = RequestMethod.POST)
    public String SaveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session){


//        if(result.hasErrors()){
//           return "redirect:/user/contacts/add_contact";
//        }
        if(result.hasErrors()){


            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Alert.builder()
                    .content("Please Fill All details correct and valid")
                    .type(AlertType.red)
                    .build());
            return "redirect:/user/contacts/add_contact";
        }


        String username = Helper.getEmailOfLoggedInUser(authentication);
        String fileUrl = imageService.uploadImage(contactForm.getContactImage());

logger.info("file Info",contactForm.getContactImage().getOriginalFilename());
        User user = userService.getUserByEmail(username);
//        contactService.save(contactForm);
//        to do above we need to convert that form into contact
        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(true);
        contact.setAddress(contactForm.getAddress());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setUser(user);
        contact.setPicture(fileUrl);
        contact.setDescription(contactForm.getDescription());

    contactService.save(contact);
        return "redirect:/user/contacts/add_contact";
    }

}
