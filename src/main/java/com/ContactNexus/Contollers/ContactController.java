package com.ContactNexus.Contollers;


import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import com.ContactNexus.Forms.ContactForm;
import com.ContactNexus.Forms.ContactSearchForm;
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
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    public String contactPage(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "3") int size,
            @RequestParam(value="sortBy",defaultValue = "name") String sortBy,
            @RequestParam(value="direction",defaultValue = "asc") String direction,
            Model model,Authentication authentication){
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user,page,size,sortBy,direction);

//        logger.info("contacts" ,pageContact);
        model.addAttribute("pageContact",pageContact);
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
            return "user/add_contact";
        }


        String username = Helper.getEmailOfLoggedInUser(authentication);
        String filename= UUID.randomUUID().toString();
        String fileUrl = imageService.uploadImage(contactForm.getContactImage(),filename);

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
        contact.setCloudinaryImagePublicId(filename);
        contact.setDescription(contactForm.getDescription());

    contactService.save(contact);
        return "redirect:/user/contacts/add_contact";
    }

    @RequestMapping("/search")
    public String searchHandler(

            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue =  "4") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());

        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }

        logger.info("pageContact {}", pageContact);

        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", "4");

        return "user/search";
    }

    // detete contact
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(
            @PathVariable("contactId") String contactId,
            HttpSession session) {
        contactService.delete(contactId);
        logger.info("contactId {} deleted", contactId);

        session.setAttribute("message",
                Alert.builder()
                        .content("Contact is Deleted successfully !! ")
                        .type(AlertType.green)
                        .build()

        );

        return "redirect:/user/contacts";
    }

    // update contact form view
    @GetMapping("/view/{contactId}")
    public String updateContactFormView(
            @PathVariable("contactId") String contactId,
            Model model) {

        var contact = contactService.getById(contactId);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
//        contactForm.setContactImage(MultipartFile(contact.getPicture().toString()));

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);

        return "user/update_contact_view";
    }

}
