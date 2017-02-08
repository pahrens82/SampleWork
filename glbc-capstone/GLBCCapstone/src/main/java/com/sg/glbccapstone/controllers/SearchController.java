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
import com.sg.glbccapstone.model.Brewery;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	private BeerDao beerDao;
	private BreweryDao breweryDao;
	private ArticleDao articleDao;
	
	@Inject
	public SearchController(BeerDao beerDao, BreweryDao breweryDao, ArticleDao articleDao) {
		this.beerDao = beerDao;
		this.breweryDao = breweryDao;
		this.articleDao = articleDao;
	}
	
	//Methods to return the requested search view
	@RequestMapping(value = "/searchBeer", method = RequestMethod.GET)
	public String displaySearchBeer() {
		return "searchBeer";
	}
	
	@RequestMapping(value = "/searchBrewery", method = RequestMethod.GET)
	public String displaySearchPBrewery() {
		return "searchBrewery";
	}
	
	@RequestMapping(value = "/searchArticle", method = RequestMethod.GET)
	public String displaySearchArticle() {
		return "searchArticle";
	}
	
	//Methods for searching for beer, breweries, and articles
	@RequestMapping(value = "/searchBeer", method = RequestMethod.POST)
	@ResponseBody
	public List<Beer> searchForBeer(@RequestBody Map<String, String> criteriaMap) {
		
		Map<SearchTerm, String> beerCriteriaMap = new HashMap<>();
		
		String searchTerm = criteriaMap.get("beerSearchCriteria");
		
		if(!searchTerm.isEmpty()) {
			beerCriteriaMap.put(SearchTerm.BEER, searchTerm);
			return beerDao.searchBeer(beerCriteriaMap);
		}
		
		return beerDao.getAllBeerByApproval(true);
	}
	
	@RequestMapping(value = "/searchBrewery", method = RequestMethod.POST)
	@ResponseBody
	public List<Brewery> searchForBrewery(@RequestBody Map<String, String> criteriaMap) {
		
		Map<SearchTerm, String> breweryCriteriaMap = new HashMap<>();
		
		String searchTerm = criteriaMap.get("brewerySearchCriteria");
		
		if(!searchTerm.isEmpty()) {
			breweryCriteriaMap.put(SearchTerm.BREWERY, searchTerm);
			return breweryDao.searchBrewery(breweryCriteriaMap);
		}
		
		return breweryDao.getAllBreweriesByApproval(true);
	}
	
	@RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
	@ResponseBody
	public List<Article> searchForArticle(@RequestBody Map<String, String> criteriaMap) {
		
		Map<SearchTerm, String> articleCriteriaMap = new HashMap<>();
		
		String searchTerm = criteriaMap.get("articleSearchCriteria");
		
		if(!searchTerm.isEmpty()) {
			articleCriteriaMap.put(SearchTerm.ARTICLE, searchTerm);
			return articleDao.searchArticles(articleCriteriaMap);
		}
		
		return articleDao.getAllArticlesByApproval(true);
	}
}