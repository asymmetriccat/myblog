package blog.controllers;



import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import blog.dao.RoleDao;
import blog.domain.Account;
import blog.domain.User;
import blog.domain.security.UserRole;

import blog.services.UserService;

@RestController
public class RegisterController {
	@Autowired
    private UserService userService;
	
	@Autowired
	private RoleDao roleDao;
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerPost(@RequestBody User user) {
    	   if(userService.checkUserExists(user.getUsername(), user.getEmail())) {
    		   if(userService.checkEmailExists(user.getEmail()) ||userService.checkUsernameExists(user.getUsername())) {
    			   return "usernameExists!";
    		   }
    		   
    	}else {
    		Set<UserRole> userRoles=new HashSet<>();
    		userRoles.add(new UserRole(user,roleDao.findByName("ROLE_USER")));
    		
    		userService.saveUser(user);
    		
    		
    	}
    	   return user.toString();
    } 
    
}

    
    
