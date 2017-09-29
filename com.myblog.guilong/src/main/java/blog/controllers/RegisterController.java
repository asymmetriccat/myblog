package blog.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.dao.RoleDao;
import blog.domain.User;
import blog.domain.security.UserRole;

import blog.services.UserService;

@Controller
public class RegisterController {
	@Autowired
    private UserService userService;

   
   @Autowired
   private RoleDao roleDao; 
   

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
    	User user=new User();
 	   model.addAttribute("user", user);
        
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user,  Model model) {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "register";
        } else {
        	 Set<UserRole> userRoles = new HashSet<>();
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}
    
    
