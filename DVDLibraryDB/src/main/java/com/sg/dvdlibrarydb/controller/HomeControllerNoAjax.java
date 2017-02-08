/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.controller;

import com.sg.dvdlibrarydb.dao.DvdLibraryDao;
import com.sg.dvdlibrarydb.models.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
public class HomeControllerNoAjax {

    private DvdLibraryDao dao;

    @Inject
    public HomeControllerNoAjax(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdLibraryNoAjax", method = RequestMethod.GET)
    public String displayDvdLibraryNoAjax(Model model) {
        List<Dvd> dvdList = dao.getAllDvds();
        model.addAttribute("dvdList", dvdList);
        return "displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "displayNewDvdFormNoAjax", method = RequestMethod.GET)
    public String displayNewDvdFormNoAjax() {
        return "newDvdFormNoAjax";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String addnewDvdNoAjax(HttpServletRequest req) {
        String title = req.getParameter("title");
        String date = req.getParameter("date");
        String director = req.getParameter("director");
        String studio = req.getParameter("studio");
        String rating = req.getParameter("rating");
        String notes = req.getParameter("notes");

        Dvd dvd = new Dvd();
        dvd.setTitle(title);
        dvd.setDate(date);
        dvd.setDirector(director);
        dvd.setStudio(studio);
        dvd.setRating(rating);
        dvd.setNotes(notes);
        dao.addDvd(dvd);
        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest req) {
        int dvdId = Integer.parseInt(req.getParameter("dvdId"));
        dao.removeDvd(dvdId);
        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/displayEditDvdFormNoAjax", method = RequestMethod.GET)
    public String displayEditDvdFormNoAjax(HttpServletRequest req, Model model) {
        int dvdid = Integer.parseInt(req.getParameter("dvdId"));
        Dvd dvd = dao.getDvdById(dvdid);
        model.addAttribute("dvd", dvd);
        return "editDvdFormNoAjax";
    }

    @RequestMapping(value = "/editDvdNoAjax", method = RequestMethod.POST)
    public String editDvdNoAjax(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "editDvdFormNoAjax";
        }
        dao.updateDvd(dvd);
        return "redirect:displayDvdLibraryNoAjax";
    }
}
