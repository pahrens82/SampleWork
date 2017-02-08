package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.BeerDao;
import com.sg.glbccapstone.dao.BreweryDao;
import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.Brewery;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class BeerController {

    private BeerDao beerDao;
    private BreweryDao breweryDao;

    @Inject
    public BeerController(BeerDao beerDao, BreweryDao breweryDao) {
        this.beerDao = beerDao;
        this.breweryDao = breweryDao;
    }

    /*
	 Methods to display the add/edit/remove pages
     */
    @RequestMapping(value = "/admin/addBeer", method = RequestMethod.GET)
    public String displayAddBeer(Model model) {
        Beer beer = new Beer();
        model.addAttribute("beer", beer);

        List<Brewery> breweryList = breweryDao.getAllBreweries();
        model.addAttribute("breweryList", breweryList);
        

        return "admin/addBeer";
    }

    @RequestMapping(value = "/admin/editBeer", method = RequestMethod.GET)
    public String displayEditBeer(HttpServletRequest req, Model model) {
        int beerId = Integer.parseInt(req.getParameter("beerId"));

        Beer beer = beerDao.getBeerById(beerId);
        model.addAttribute("beer", beer);

        List<Brewery> breweryList = breweryDao.getAllBreweries();
        model.addAttribute("breweryList", breweryList);

        return "admin/editBeer";

    }


    /*
	 Beer Dao CRUD
     */
    //Create
    @RequestMapping(value = "/admin/addBeer", method = RequestMethod.POST)
    public String createBeer(@Valid @ModelAttribute("beer") Beer beer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<Brewery> breweryList = breweryDao.getAllBreweries();
            model.addAttribute("breweryList", breweryList);
            return "admin/addBeer";
        }

        beerDao.createBeer(beer);

        return "redirect:/admin/beerList";
    }

    //Retrieve
    @RequestMapping(value = "/viewBeer", method = RequestMethod.GET)
    public String displayBeerDetails(HttpServletRequest req, Model model) {
        int beerId = Integer.parseInt(req.getParameter("beerId"));

        Beer beer = beerDao.getBeerById(beerId);
        model.addAttribute("beer", beer);

        return "viewBeer";
    }

    @RequestMapping(value = "/beer/{beerId}", method = RequestMethod.GET)
    @ResponseBody
    public Beer getBeerById(@PathVariable("beerId") int beerId) {
        return beerDao.getBeerById(beerId);
    }

    @RequestMapping(value = "/beerList", method = RequestMethod.GET)
    public String displayBeerList(Model model) {

        List<Beer> beerList = beerDao.getAllBeerByApproval(true);
        model.addAttribute("beerList", beerList);
        
        List<Brewery> breweryList = breweryDao.getAllBreweries();
        model.addAttribute("breweryList", breweryList);

        return "beerList";
    }

    @RequestMapping(value = "/admin/beerList", method = RequestMethod.GET)
    public String displayAdminBeerList(Model model) {
        List<Beer> beerList = beerDao.getAllBeer();
        model.addAttribute("beerList", beerList);

        List<Brewery> breweryList = breweryDao.getAllBreweries();
        model.addAttribute("breweryList", breweryList);

        return "/admin/beerList";
    }

    /**
     * List beers by brewery id to possibly be implemented later.
     *
     * @RequestMapping(value = "/allBeerListByBrewery", method =
     * RequestMethod.GET) public String
     * getAllBeersByBreweryId(HttpServletRequest req, Model model) { int
     * breweryId = Integer.parseInt(req.getParameter("breweryId"));
     *
     * List<Beer> allBeerByBrewery = beerDao.getAllBeerByBreweryId(breweryId);
     * model.addAttribute("beerList", allBeerByBrewery);
     *
     * return "beerList"; }
     */
    //Update
    @RequestMapping(value = "/admin/editBeer", method = RequestMethod.POST)
    public String editBeer(@Valid @ModelAttribute("beer") Beer beer,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            List<Brewery> breweryList = breweryDao.getAllBreweries();
            model.addAttribute("breweryList", breweryList);

            return "admin/editBeer";
        }

        beerDao.updateBeer(beer);

        return "redirect:/admin/beerList";
    }

    //Remove
    @RequestMapping(value = "/admin/removeBeer", method = RequestMethod.GET)
    public String displayRemoveBeer(HttpServletRequest req, Model model) {

        int beerId = Integer.parseInt(req.getParameter("beerId"));

        Beer beer = beerDao.getBeerById(beerId);
        model.addAttribute("beer", beer);

        Brewery brewery = breweryDao.getBreweryById(beer.getBreweryId());
        model.addAttribute("brewery", brewery);

        return "admin/removeBeer";
    }

    //Remove
    @RequestMapping(value = "/admin/removeBeer", method = RequestMethod.POST)
    public String removeBeer(HttpServletRequest req) {

        int beerId = Integer.parseInt(req.getParameter("beerId"));
        beerDao.removeBeer(beerId);

        return "redirect:/admin/beerList";
    }
}
