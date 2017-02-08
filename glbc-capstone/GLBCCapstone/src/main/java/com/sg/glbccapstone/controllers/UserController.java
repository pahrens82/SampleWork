/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.UserDao;
import com.sg.glbccapstone.model.Permission;
import com.sg.glbccapstone.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "admin/user")
public class UserController {

    private UserDao userDao;

    // #1 - PasswordEncoder interface
    private BCryptPasswordEncoder encoder;
    
    @Inject
    public UserController(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    //This endpoint just displays the Add User form
    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/addUser";
    }

    //This endpoint processes the form data and creates a new User
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addUser";
        }

        String userPassword =user.getPassword();
        String hashUserPassword = encoder.encode(userPassword);
        user.setPassword(hashUserPassword);
        
        //All users that get added will be at least a guest and a user, only the ADMIN can add SUPER_USER via a checkbox.
        user.addPermission(Permission.GUEST);
        user.addPermission(Permission.USER);
        if (user.getPermissionId() == 2) {
            user.addPermission(Permission.SUPER_USER);
        }
        try{
        userDao.addUser(user);
        }catch(DuplicateKeyException dke){
            result.rejectValue("userName", "userName.notvalid","Username Taken!");
            return "admin/addUser";
        }
        return "redirect:manageUsers";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userDao.updateUser(user);
        return "redirect:manageUsers";
    }

    @RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
    public String displayUserAccountList(HttpServletRequest req, Model model) {
        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            for (int j = 1; j < 5; j++) {
                if (j >= user.getPermissionId()) {
                    user.addPermission(Permission.getPermissionById(j));
                }
            }
        }
        model.addAttribute("userList", userList);
        return "admin/manageUsers";
    }
    
    @RequestMapping(value="/changeUserRole", method=RequestMethod.POST)
    public String changeUserRole(HttpServletRequest req, Model model){
        String userName = req.getParameter("userName");
//        int newPermissionId = Integer.parseInt(req.getParameter("newPermissionId"));
        User user = userDao.getUserByUserName(userName);
        user.addPermission(Permission.USER);
        user.addPermission(Permission.GUEST);
        switch(user.getPermissionId()){
            case 2:
                userDao.updateUser(removeRoleSuper(user));
                break;
            case 3:
                userDao.updateUser(addRoleSuper(user));
                break;
            default:
                user.setPermissionId(4);
                user.setPermissions(new ArrayList<> (Arrays.asList(Permission.GUEST)));
                userDao.updateUser(user);
        }
        return "redirect:manageUsers";
    }
    
    @RequestMapping(value="/changeUserStatus", method=RequestMethod.POST)
    public String changeUserEnabled(HttpServletRequest req, Model model){
        String userName = req.getParameter("userName");
        User user = userDao.getUserByUserName(userName);
        if (user.getEnabled()){
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
        for(Permission p:Permission.values()){
            if(p.getId()>=user.getPermissionId() && p.getId()<5){
                user.addPermission(p);
            }
        }
        userDao.updateUser(user);
        return "redirect:manageUsers";
    }

    private User removeRoleSuper(User user) {
        user.setPermissionId(3);
        user.getPermissions().remove(Permission.SUPER_USER);
        return user;
    }

    private User addRoleSuper(User user) {
        user.setPermissionId(2);
        user.addPermission(Permission.SUPER_USER);
        return user;
    }

}
