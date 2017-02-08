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
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private DvdLibraryDao dao;

    @Inject
    public HomeController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    @RequestMapping(value = "/getDvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") int id) {
        return dao.getDvdById(id);
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd) {
        dao.addDvd(dvd);
        return dvd;
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id) {
        dao.removeDvd(id);
    }

    @RequestMapping(value = "/updateDvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putDvd(@PathVariable("id") int id, @RequestBody Dvd dvd) {
        dvd.setDvdId(id);
        dao.updateDvd(dvd);
    }

    @RequestMapping(value = "/loadDvds", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {
        List<Dvd> dvd = dao.getAllDvds();
        return dvd;
    }
}
