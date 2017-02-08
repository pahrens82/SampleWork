/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Brewery;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface BreweryDao {

    //C
    public Brewery createBrewery(Brewery brewery);

	//R
    public List<Brewery> getAllBreweries();
	public List<Brewery> getAllBreweriesByApproval(boolean approval);
    public Brewery getBreweryById(int breweryId);

	//U
	public void updateBrewery(Brewery brewery);

	//D
    public void removeBrewery(int id);
	
	public List<Brewery> searchBrewery(Map<SearchTerm, String> criteria);
}
