/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Category;
import com.sg.glbccapstone.model.SearchTerm;
import com.sg.glbccapstone.model.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface ArticleDao {
	
	//Create
	public Article createArticle(Article article);
	
	//Retrieve
	public List<Article> getAllArticles();
	public List<Article> getAllArticlesByUserName(String username);
	public List<Article> getAllArticlesByTagName(String tagName);
	public List<Article> getAllArticlesByCategoryId(int categoryId);
	public List<Article> getAllArticlesByApproval(boolean approved);
	public Article getArticleById(int articleId);
	public List<Article> getMostRecentApprovedArticle(boolean approved, int limit);
	//Alter
	public void updateArticle(Article article);
	
	//Purge
	public void removeArticle(int articleId);
	
	public List<Article> searchArticles(Map<SearchTerm, String> criteria);

	public void addTagsToArticle(List<Tag> tagList, Article article);
}
