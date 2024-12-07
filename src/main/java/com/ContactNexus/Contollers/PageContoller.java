package com.ContactNexus.Contollers;

import com.ContactNexus.Entities.User;
import com.ContactNexus.Forms.UserForm;
import com.ContactNexus.Helper.Alert;
import com.ContactNexus.Helper.AlertType;
import com.ContactNexus.Services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.HashMap;
import java.util.Map;
@Controller
public class PageContoller {

    @Autowired
    UserService userService;

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
    @RequestMapping("/")
    public String defaultPage(Model model) {
        model.addAttribute("script", "yourScriptFragment"); // Replace "yourScriptFragment" with the actual fragment name

        return "redirect:/home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }
    @RequestMapping("/services")
    public String servicePage(){
        return "services";
    }

    @RequestMapping("/login")
    public String LoginPage(){
        return "login";
    }

    @RequestMapping("/Register")
    public String SignupPage(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "Register";
    }
    @RequestMapping("/contactus")
    public String ContactusPage(){
        return "contactus";
    }

    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){

//        fetching data from userForm and storing into user object which help to create user.
//        User user = User.builder()
//                .name(userForm.getName())
//                .password(userForm.getPassword())
//                .email(userForm.getEmail())
//                .phoneNumber(String.valueOf(userForm.getPhoneNumber()))
//                .profilePic("https://images.app.goo.gl/5Wv62339zAfVB4rs7")
//                .build();
//        User saveduser = userService.saveUser(user);
//        System.out.println(saveduser);

        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
            return "Register";
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://images.app.goo.gl/5Wv62339zAfVB4rs7");

        User savedUser = userService.saveUser(user);


        Alert alert = Alert.builder().content("Registration Successful").type(AlertType.blue).build();
        session.setAttribute("message",alert);

        System.out.println("user saved");
    return "redirect:/Register";
    }
}
