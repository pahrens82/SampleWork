/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.dao;

import com.sg.dvdlibrarydb.models.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoInMem implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();

    private static int dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> dvd = dvdMap.values();
        return new ArrayList(dvd);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerms, String> criteria) {
        String titleCriteria = criteria.get(SearchTerms.TITLE);
        String dateCriteria = criteria.get(SearchTerms.DATE);
        String directorCriteria = criteria.get(SearchTerms.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerms.STUDIO);
        String ratingCriteria = criteria.get(SearchTerms.RATING);

        Predicate<Dvd> titleMatches;
        Predicate<Dvd> dateMatches;
        Predicate<Dvd> directorMatches;
        Predicate<Dvd> studioMatches;
        Predicate<Dvd> ratingMatches;

        Predicate<Dvd> truePredicate = (dvd) -> {
            return true;
        };

        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (dvd) -> dvd.getTitle().equalsIgnoreCase(titleCriteria);

        dateMatches = (dateCriteria == null || dateCriteria.isEmpty())
                ? truePredicate
                : (dvd) -> dvd.getDate().equalsIgnoreCase(dateCriteria);

        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (dvd) -> dvd.getDirector().equalsIgnoreCase(directorCriteria);

        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (dvd) -> dvd.getStudio().equalsIgnoreCase(studioCriteria);

        ratingMatches = (ratingCriteria == null || ratingCriteria.isEmpty())
                ? truePredicate
                : (dvd) -> dvd.getRating().equalsIgnoreCase(ratingCriteria);

        return dvdMap.values().stream()
                .filter(titleMatches.and(dateMatches).and(directorMatches).and(studioMatches).and(ratingMatches)).collect(Collectors.toList());
    }

}
