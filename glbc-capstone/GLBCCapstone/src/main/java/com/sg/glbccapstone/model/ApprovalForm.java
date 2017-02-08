/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.model;

import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public class ApprovalForm {
    
    private ArrayList<Beer> beerList;
    private ArrayList<Brewery> breweryList;
    private ArrayList<Article> articleList;

    public ArrayList<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(ArrayList<Beer> beerList) {
        this.beerList = beerList;
    }

    public ArrayList<Brewery> getBreweryList() {
        return breweryList;
    }

    public void setBreweryList(ArrayList<Brewery> breweryList) {
        this.breweryList = breweryList;
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<Article> articleList) {
        this.articleList = articleList;
    }
    
   
    
}
