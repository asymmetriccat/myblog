package blog.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import blog.domain.User;
import blog.services.AccountService;
import blog.services.UserService;

@RestController

public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
	AccountService AccountService;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User input) {
        User user = userService.findByUsername(input.getUsername());
        user.setUsername(input.getUsername());
        user.setFullName(input.getFullName());
        user.setAccount(AccountService.createAccount());
        user.setEmail(input.getEmail());
        user.setPhoneNumber(input.getPhoneNumber());
        userService.saveUser(user);
        return user;
    }

}
