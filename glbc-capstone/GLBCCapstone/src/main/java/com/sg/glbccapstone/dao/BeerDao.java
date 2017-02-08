/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface BeerDao {
	
    //C
    public Beer createBeer(Beer beer);
	
    //R
    public List<Beer> getAllBeer();
    public List<Beer> getAllBeerByBreweryId(int breweryId);
	public List<Beer> getAllBeerByApproval(boolean approval);
    public Beer getBeerById(int beerId);
	
    //U
    public void updateBeer(Beer beer);
	
    //D
    public void removeBeer(int id);
	
	public List<Beer> searchBeer(Map<SearchTerm, String> criteria);
}
