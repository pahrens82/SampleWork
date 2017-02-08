/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.ArticleDao;
import com.sg.glbccapstone.dao.CategoryDao;
import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Category;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class CategoryController {

    private ArticleDao articleDao;
    private CategoryDao categoryDao;

    @Inject
    public CategoryController(ArticleDao articleDao, CategoryDao categoryDao) {
        this.articleDao = articleDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryById(@PathVariable("categoryId") int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }
}
