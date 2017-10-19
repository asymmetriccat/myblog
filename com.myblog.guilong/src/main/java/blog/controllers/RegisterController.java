package blog.controllers;



import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import blog.dao.RoleDao;
import blog.domain.Account;
import blog.domain.User;
import blog.domain.security.UserRole;

import blog.services.UserService;

@Controller
public class RegisterController {
	@Autowired
    private UserService userService;
	
	@Autowired
	private RoleDao roleDao;
	
	
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register(Model model) {
    	User user=new User();
    	model.addAttribute("user", user);
    	return "register";
    	     
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerPost(@ModelAttribute("user") User user, Model model) {
    	   if(userService.checkUserExists(user.getUsername(), user.getEmail())) {
    		   if(userService.checkEmailExists(user.getEmail())) {
    		   model.addAttribute("emailExists", true);
    		   }
    		   if(userService.checkUsernameExists(user.getUsername())) {
    			   model.addAttribute("usernameExists", true);
    		   }
    		   return "register";
    	}else {
    		Set<UserRole> userRoles=new HashSet<>();
    		userRoles.add(new UserRole(user,roleDao.findByName("ROLE_USER")));
    		
    		userService.saveUser(user);
    		return "index";
    		//return "redirect:/";
    	}
    }
    @RequestMapping("/account")
    public String userAccount(Principal principal, Model model) {
    	User user=userService.findByUsername(principal.getName());
    	Account account=user.getAccount();
    	model.addAttribute("account", account);
    	return "index";
    	
    }
}

    
    
