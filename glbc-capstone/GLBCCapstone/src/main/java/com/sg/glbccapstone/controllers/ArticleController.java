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
import com.sg.glbccapstone.model.Tag;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class ArticleController {

    private ArticleDao articleDao;
    private TagDao tagDao;
    private CategoryDao categoryDao;

    @Inject
    public ArticleController(ArticleDao articleDao, TagDao tagDao, CategoryDao categoryDao) {
        this.articleDao = articleDao;
        this.tagDao = tagDao;
        this.categoryDao = categoryDao;
    }

    /*
	 Methods to display the add/edit/remove pages
     */
    @RequestMapping(value = "/admin/addArticle", method = RequestMethod.GET)
    public String displayAddArticle(Model model) {

        Article article = new Article();
        model.addAttribute("article", article);
        List<Category> categoryList = categoryDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        return "admin/addArticle";
    }

    @RequestMapping(value = "/admin/editArticle", method = RequestMethod.GET)
    public String displayEditArticle(HttpServletRequest req, Model model) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));

        Article article = articleDao.getArticleById(articleId);
        model.addAttribute("article", article);

        List<Category> categoryList = categoryDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        return "admin/editArticle";
    }

    @RequestMapping(value = "/admin/removeArticle", method = RequestMethod.GET)
    public String displayRemoveArticle(HttpServletRequest req, Model model) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));

        Article article = articleDao.getArticleById(articleId);
        model.addAttribute("article", article);

        return "admin/removeArticle";
    }

    /*
	 Article Dao CRUD
     */
    //Create
    @RequestMapping(value = "/admin/addArticle", method = RequestMethod.POST)
    public String createArticle(@Valid @ModelAttribute("article") Article article,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<Category> categoryList = categoryDao.getAllCategories();
            model.addAttribute("categoryList", categoryList);

            return "admin/addArticle";
        }

        article.setCreateDate(new Date());
        articleDao.createArticle(article);

        ////Handle Tags
        //Create any new tags and create list of all tags in article
        List<Tag> tagList = tagDao.manageTags(article.getTextBody());
        //Assosiate tags to Aricle
        articleDao.addTagsToArticle(tagList, article);

        //You NEED the begging '/' here
        return "redirect:/admin/articleList";
    }

    //Retrieve
    @RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
    @ResponseBody
    public String viewArticleByArticleId(HttpServletRequest req, Model model) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));

        Article article = articleDao.getArticleById(articleId);
        model.addAttribute("article", article);

        return "viewArticle";
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticleById(@PathVariable("articleId") int articleId) {
        Article potato = articleDao.getArticleById(articleId);
        return potato;
    }

    @RequestMapping(value = {"/articleArchive"}, method = RequestMethod.GET)
    public String displayArticleArchive() {
        return "articleArchive";
    }

    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> displayArticleList() {
        List<Article> newList = articleDao.getAllArticles();
        Collections.reverse(newList);

        return newList;
    }

    @RequestMapping(value = "/mostRecentArticle", method = RequestMethod.GET)
    @ResponseBody
        public List<Article> displayMostRecentApprovedArticle() {
        int limit = 1;
        return articleDao.getMostRecentApprovedArticle(true, limit);

    }

    @RequestMapping(value = "/articlesByUser", method = RequestMethod.POST)
    public String viewArticleByUserId(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");

        List<Article> articlesByUserName = articleDao.getAllArticlesByUserName(username);
        model.addAttribute("articlesByUserName", articlesByUserName);

        return "articleList";
    }

    @RequestMapping(value = "/articles/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getArticleByCategoryId(@PathVariable("categoryId") int categoryId) {
        List<Article> returnArticle = articleDao.getAllArticlesByCategoryId(categoryId);
        return returnArticle;
    }

    @RequestMapping(value = "/articlesByApproval", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> viewApprovedArticles() {

        return articleDao.getAllArticlesByApproval(true);

    }

    //TODO - google and ask Sarah
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    //Update
    @RequestMapping(value = "/admin/editArticle", method = RequestMethod.POST)
    public String editArticle(@Valid @ModelAttribute("article") Article article,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<Category> categoryList = categoryDao.getAllCategories();
            model.addAttribute("categoryList", categoryList);
            return "admin/editArticle";
        }

        ////Handle Tags
        //Get old article
        Article oldArticle = articleDao.getArticleById(article.getArticleId());
        //Create any new tags and create list of all tags in article
        //Sending old and new so only net new are added.
        List<Tag> tagList = tagDao.manageTags(article.getTextBody(), oldArticle.getTextBody());
        //Assosiate tags to Aricle
        if (tagList.size() != 0) {
            articleDao.addTagsToArticle(tagList, article);
        }

        article.setEditDate(new Date());
        articleDao.updateArticle(article);
        //You NEED the begging '/' here
        return "redirect:/admin/articleList";
    }

    //Delete
    @RequestMapping(value = "/admin/removeArticle", method = RequestMethod.POST)
    public String removeArticle(HttpServletRequest req) {

        int articleId = Integer.parseInt(req.getParameter("articleId"));
        articleDao.removeArticle(articleId);

        //You NEED the begging '/' here
        return "redirect:/admin/articleList";
    }

    //List
    @RequestMapping(value = "/admin/articleList", method = RequestMethod.GET)
    public String displayAdminArticleList(Model model) {

        List<Article> articleList = articleDao.getAllArticles();
        model.addAttribute("articleList", articleList);
        List<Category> categoryList = categoryDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        return "admin/articleList";
    }
}
