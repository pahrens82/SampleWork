/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.dao;

import com.sg.dvdlibrarydb.models.Dvd;
import java.util.List;
import java.util.Map;



/**
 *
 * @author apprentice
 */
public interface DvdLibraryDao {
    public Dvd addDvd(Dvd dvd);
    
    public void removeDvd(int dvdId);
    
    public void updateDvd(Dvd dvd);
    
    public List<Dvd> getAllDvds();
    
    public Dvd getDvdById(int dvdId);
    
    public List<Dvd> searchDvds(Map<SearchTerms, String> criteria);
    
}
