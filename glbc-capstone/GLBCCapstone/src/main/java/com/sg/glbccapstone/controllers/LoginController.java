/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.UserDao;
import com.sg.glbccapstone.model.Permission;
import com.sg.glbccapstone.model.User;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class LoginController {
    
    private UserDao userDao;
    
    // #1 - PasswordEncoder interface
    private BCryptPasswordEncoder encoder;
    
    @Inject
    public LoginController(UserDao userDao, BCryptPasswordEncoder encoder){
        this.userDao = userDao;
        this.encoder = encoder;
    }
    
    // #1 - respond to all GET requests for /login

    @RequestMapping(value ={"/"}, method = RequestMethod.GET)
    public String showLanding() {
        return "landing";
    }
    
    @RequestMapping(value ={"/login"}, method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    
    //This endpoint just displays the Signup form
    @RequestMapping(value = "/login/signup", method = RequestMethod.GET)
    public String displaySignupForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        
        return "signup";
    }
    
    //This endpoint processes the form data and creates a new User
    @RequestMapping(value = "/login/createUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        
        if(result.hasErrors()){
            return "signup";
        }
        
        String userPassword =user.getPassword();
        String hashUserPassword = encoder.encode(userPassword);
        user.setPassword(hashUserPassword);
        
        user.setFirstName("");
        user.setLastName("");
        user.setEmail("");
        user.setPermissionId(3);
        //user.setEnabled(true);
        user.addPermission(Permission.GUEST);
        user.addPermission(Permission.USER);
        try{
        userDao.addUser(user);
        }catch(DuplicateKeyException dke){
            result.rejectValue("userName", "userName.notvalid","Username Taken!");
            return "signup";
        }
        return "login";
    }
    
//    @ExceptionHandler(value={DuplicateKeyException.class})
//    public ModelAndView exceptionHandler(Exception ex, Local locale){
//        String message = ex.getMessage();
//        return new ModelAndView("duplicate", "message", message);
//    }
    
}
