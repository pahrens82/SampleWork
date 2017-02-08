/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.controllers;

import com.sg.glbccapstone.dao.ArticleDao;
import com.sg.glbccapstone.dao.CategoryDao;
import com.sg.glbccapstone.dao.TagDao;
import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Category;
import java.util.List;
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
public class TagController {
    
    private ArticleDao articleDao;
    private CategoryDao categoryDao;
    private TagDao tagDao;

    @Inject
    public TagController(ArticleDao articleDao, CategoryDao categoryDao, TagDao tagDao) {
        this.articleDao = articleDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
    }
    
    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getArticleByTagName(@PathVariable("tagName") String tagName) {
        return articleDao.getAllArticlesByTagName(tagName);
    }
}
