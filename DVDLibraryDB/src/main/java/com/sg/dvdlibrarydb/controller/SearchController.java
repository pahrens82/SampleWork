/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.controller;

import com.sg.dvdlibrarydb.dao.DvdLibraryDao;
import com.sg.dvdlibrarydb.dao.SearchTerms;
import com.sg.dvdlibrarydb.models.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class SearchController {

    private DvdLibraryDao dao;

    @Inject
    public SearchController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value = "search/dvd", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) {
        Map<SearchTerms, String> criteriaMap = new HashMap<>();
        String currentTerm = searchMap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerms.TITLE, currentTerm);
        }
        currentTerm = searchMap.get("date");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerms.DATE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerms.DIRECTOR, currentTerm);
        }
        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerms.STUDIO, currentTerm);
        }
        currentTerm = searchMap.get("rating");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerms.RATING, currentTerm);
        }
        return dao.searchDvds(criteriaMap);
    }
}
