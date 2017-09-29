package blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.domain.User;
import blog.forms.LoginForm;
import blog.services.NotificationService;
import blog.services.UserService;

@Controller

public class LoginController {
	@Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/login")
    public String login(LoginForm loginForm) {
    	   
        return "login";
    }
    
    

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
             notifyService.addErrorMessage("Please fill the form correctly!");
             return "login";
        }

        if (!userService.authenticate(
             loginForm.getEmail(), loginForm.getPassword())) {
             notifyService.addErrorMessage("Invalid login!");
             return "login";
        }
        
        notifyService.addInfoMessage("Login successful");
        return "redirect:/";
    }
    
}

