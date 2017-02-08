/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String displayAdminHome() {
        return "adminHome";
    }

    @RequestMapping(value = "/manageContent", method = RequestMethod.GET)
    public String displayManageContent() {
        return "manageContent";
    }
}
