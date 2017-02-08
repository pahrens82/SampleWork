/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.ArticleDao;
import com.sg.glbccapstone.dao.BeerDao;
import com.sg.glbccapstone.dao.BreweryDao;
import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.ApprovalForm;
import com.sg.glbccapstone.model.Brewery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class ApprovalController {

    private BeerDao beerDao;
    private BreweryDao breweryDao;
    private ArticleDao articleDao;

    @Inject
    public ApprovalController(BeerDao beerDao, BreweryDao breweryDao, ArticleDao articleDao) {
        this.beerDao = beerDao;
        this.breweryDao = breweryDao;
        this.articleDao = articleDao;
    }

    //TODO - google and ask Sarah
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/admin/approveContent", method = RequestMethod.GET)
    public String displayApproveContent(Model model, HttpServletRequest req) {
        ApprovalForm approvalForm = new ApprovalForm();

        ArrayList<Beer> beerList = (ArrayList<Beer>) beerDao.getAllBeerByApproval(false);
        approvalForm.setBeerList(beerList);
        ArrayList<Brewery> breweryList = (ArrayList<Brewery>) breweryDao.getAllBreweriesByApproval(false);
        approvalForm.setBreweryList(breweryList);
        ArrayList<Article> articleList = (ArrayList<Article>) articleDao.getAllArticlesByApproval(false);
        approvalForm.setArticleList(articleList);

        //List of ALL brewerys for display perposes only
        ArrayList<Brewery> allBreweryList = (ArrayList<Brewery>) breweryDao.getAllBreweries();

        req.setAttribute("approvalForm", approvalForm);
        req.setAttribute("allBreweryList", allBreweryList);

        return "admin/approveContent";
    }

    @RequestMapping(value = "/admin/approveContent", method = RequestMethod.POST)
    public String approveContent(Model model, HttpServletRequest req,
            @ModelAttribute("approvalForm") ApprovalForm approvalForm) {

        ArrayList<Beer> beerList = approvalForm.getBeerList();
        ArrayList<Brewery> breweryList = approvalForm.getBreweryList();
        ArrayList<Article> articleList = approvalForm.getArticleList();

        //Update approvals on Beers
        for (Beer beer : beerList) {
            if (beer.getApproval() == true) {
                beerDao.updateBeer(beer);
            }
        }

        //Update approvals for Brewerys
        for (Brewery brewery : breweryList) {
            if (brewery.getApproval() == true) {
                breweryDao.updateBrewery(brewery);
            }
        }

        //Update approvals for Article
        for (Article article : articleList) {
            if (article.getApproval() == true) {
				
				if(article.getPublishDate() == null) {
					article.setPublishDate(new Date());
				}
				
                article.setApproveDate(new Date());

                articleDao.updateArticle(article);
            }
        }

        return "redirect:/admin/approveContent";
    }

}
