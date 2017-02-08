package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.BeerDao;
import com.sg.glbccapstone.dao.BreweryDao;
import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.Brewery;
import com.sg.glbccapstone.model.State;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class BreweryController {

    private BeerDao beerDao;
    private BreweryDao breweryDao;

    @Inject
    public BreweryController(BreweryDao breweryDao, BeerDao beerDao) {
        this.breweryDao = breweryDao;
        this.beerDao = beerDao;
    }

    /*
	 Methods to display the add/edit/remove pages
     */
    @RequestMapping(value = "/admin/addBrewery", method = RequestMethod.GET)
    public String displayAddBrewery(Model model) {
        Brewery brewery = new Brewery();
        model.addAttribute("brewery", brewery);
        List<State> states = Arrays.asList(State.values());
        model.addAttribute("states", states);

        return "admin/addBrewery";
    }

    @RequestMapping(value = "/admin/editBrewery", method = RequestMethod.GET)
    public String displayEditBrewery(HttpServletRequest req, Model model) {
        int breweryId = Integer.parseInt(req.getParameter("breweryId"));

        Brewery brewery = breweryDao.getBreweryById(breweryId);
        model.addAttribute("brewery", brewery);
        List<State> states = Arrays.asList(State.values());
        model.addAttribute("states", states);

        return "admin/editBrewery";
    }

    /*
	 Brewery Dao CRUD
     */
    //Create
    @RequestMapping(value = "admin/addBrewery", method = RequestMethod.POST)
    public String createBrewery(@Valid @ModelAttribute("brewery") Brewery brewery, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/addBrewery";
        }

        breweryDao.createBrewery(brewery);

        return "redirect:/admin/breweryList";
    }

    //Retrieve
    @RequestMapping(value = "/viewBrewery", method = RequestMethod.GET)
    public String viewSingleBrewery(HttpServletRequest req, Model model) {

        int breweryId = Integer.parseInt(req.getParameter("breweryId"));
        Brewery brewery = breweryDao.getBreweryById(breweryId);
        List<Beer> beers = beerDao.getAllBeerByBreweryId(breweryId);

        model.addAttribute("brewery", brewery);
        model.addAttribute("beers", beers);

        return "viewBrewery";
    }
    
    @RequestMapping(value = "/brewery/{breweryId}", method = RequestMethod.GET)
    @ResponseBody
    public Brewery getBreweryById(@PathVariable("breweryId") int breweryId) {
        return breweryDao.getBreweryById(breweryId);
    }

    @RequestMapping(value = "/breweryList", method = RequestMethod.GET)
    public String displayBreweryList(Model model) {

        List<Brewery> breweryList = breweryDao.getAllBreweriesByApproval(true);
        model.addAttribute("breweryList", breweryList);

        return "breweryList";
    }
    
    @RequestMapping(value = "/admin/breweryList", method = RequestMethod.GET)
    public String displayAdminBreweryList(Model model) {

        List<Brewery> breweryList = breweryDao.getAllBreweries();
        model.addAttribute("breweryList", breweryList);
        
        return "admin/breweryList";
    }

    //Update
    @RequestMapping(value = "/admin/editBrewery", method = RequestMethod.POST)
    public String editBrewery(@Valid @ModelAttribute("brewery") Brewery brewery, BindingResult result) {

		if(result.hasErrors()) {
			return "/admin/editBrewery";
		}
		
        breweryDao.updateBrewery(brewery);

        return "redirect:/admin/breweryList";
    }
    
    @RequestMapping(value = "/admin/removeBrewery", method = RequestMethod.GET)
    public String displayRemoveBrewery(HttpServletRequest req, Model model) {
        int breweryId = Integer.parseInt(req.getParameter("breweryId"));

        Brewery brewery = breweryDao.getBreweryById(breweryId);
        model.addAttribute("brewery", brewery);
        
        List<Beer> beerList = beerDao.getAllBeerByBreweryId(breweryId);
        model.addAttribute("beerList", beerList);

        return "admin/removeBrewery";
    }

    //Remove
    @RequestMapping(value = "/admin/removeBrewery", method = RequestMethod.POST)
    public String removeBrewery(HttpServletRequest req) {

        int breweryId = Integer.parseInt(req.getParameter("breweryId"));
        breweryDao.removeBrewery(breweryId);

        return "redirect:/admin/breweryList";
    }
}
