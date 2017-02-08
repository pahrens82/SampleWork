/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.tests;

import com.sg.glbccapstone.dao.ArticleDao;
import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 *
 * @author apprentice
 */
public class ArticleDaoTests {
    
	public ArticleDaoTests() {
    }
    
    private ArticleDao articleDao;
    
    @Before
    public void setUp(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        articleDao = (ArticleDao) ctx.getBean("articleDaoDbImpl");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        JdbcTestUtils.executeSqlScript(cleaner, new ClassPathResource("/GlbcData/GLBC_testCreateDB.sql"), true);
    }
    
    @Test
    public void getAllArticles(){
        //arrange
        List<Article> test;
        //act
        test = articleDao.getAllArticles();
        //assert
        assertTrue("The default collection does not have 3 items.", test.size()==3);
    }
	
	@Test
	public void testCreateGetEditRemoveArticle() {
		
		//arrange - create
		Article article1 = new Article();
		article1.setUserName("admin");
		article1.setCategoryId(1);
		article1.setApproval(true);
		article1.setArticleName("Testing 1...2...3");
		article1.setSummary("Just a test article");
		article1.setTextBody("This is an article used in an integration test to test whether or not the ArticleDaoDbImpl's create, get, edit, and remove methods work properly");
		article1.setCreateDate(new Date());
		
		//act - create
		articleDao.createArticle(article1);
		List<Article> all = articleDao.getAllArticles();
		
		//assert - create
		assertTrue("An error has occurred when creating a new article.", all.size() == 4);
		
		//arrange - get/edit
		article1.setUserName("maj5004");
		article1.setCategoryId(2);
		article1.setApproval(false);
		article1.setArticleName("Updating 1...2...3");
		article1.setSummary("Just an updated article");
		article1.setTextBody("This is an article used in an integration test to test whether or not the ArticleDaoDbImpl's create, get, edit, and remove methods work properly");
		
		//act - get/edit
		articleDao.updateArticle(article1);
		Article updatedArticle = articleDao.getArticleById(article1.getArticleId());
		
		//assert - get/edit
		assertTrue("The UserName doesn't match.", article1.getUserName().equals(updatedArticle.getUserName()));
		assertTrue("The category_ID doesn't match.", article1.getCategoryId()== updatedArticle.getCategoryId());
		assertTrue("The Approval doesn't match.", article1.getApproval()== updatedArticle.getApproval());
		assertTrue("The Articlename doesn't match.", article1.getArticleName().equals(updatedArticle.getArticleName()));
		assertTrue("The Summary doesn't match.", article1.getSummary().equals(updatedArticle.getSummary()));
		assertTrue("The TextBody doesn't match.", article1.getTextBody().equals(updatedArticle.getTextBody()));
		
		//arrange - remove
		int articleId = article1.getArticleId();
		
		//act - remove
		articleDao.removeArticle(articleId);
		
		//assert - remove
		assertTrue("The article wasn't removed as expected.", articleDao.getAllArticles().size() == 3);
	}
	
	@Test
	public void testSearchArticle() {
		//no search results, matching case
		Map<SearchTerm, String> emptySearchCriteria = new HashMap<>();
		emptySearchCriteria.put(SearchTerm.ARTICLE, "Snafu");
		
		List<Article> emptyResults = articleDao.searchArticles(emptySearchCriteria);
		
		assertTrue("The search results should be empty1", emptyResults.isEmpty());
		
		//one search result, matching case
		Map<SearchTerm, String> oneSearchCriteria = new HashMap<>();
		oneSearchCriteria.put(SearchTerm.ARTICLE, "Artical on Epiphany");
		
		List<Article> oneResultMatchCase = articleDao.searchArticles(oneSearchCriteria);
		
		assertTrue("The search results list should contain one entry2", oneResultMatchCase.size() == 1);
		
		//one search result, non-matching case
		Map<SearchTerm, String> oneSearchCriteriaMismatchCase = new HashMap<>();
		oneSearchCriteriaMismatchCase.put(SearchTerm.ARTICLE, "ArTiCaL oN ePiPhAnY");
		
		List<Article> oneResultNonMatchCase = articleDao.searchArticles(oneSearchCriteriaMismatchCase);
		
		assertTrue("The search results list should contain one entry3", oneResultNonMatchCase.size() == 1);
		
		//one search result, partial criteria, matching case
		Map<SearchTerm, String> partialCriteriaMatchCase = new HashMap<>();
		partialCriteriaMatchCase.put(SearchTerm.ARTICLE, "Epiphany");
		
		List<Article> oneResultPartialCriteriaMatchCase = articleDao.searchArticles(partialCriteriaMatchCase);
		
		assertTrue("The search results list should contain one entry4", oneResultPartialCriteriaMatchCase.size() == 1);
		
		//one search result, partial criteria, non-matching case
		Map<SearchTerm, String> partialCriteriaNonMatchCase = new HashMap<>();
		partialCriteriaNonMatchCase.put(SearchTerm.ARTICLE, "EpIpHaNy");
		
		List<Article> oneResultPartialCriteriaNonMatchCase = articleDao.searchArticles(partialCriteriaNonMatchCase);
		
		assertTrue("The search results list should contain one entry5", oneResultPartialCriteriaNonMatchCase.size() == 1);
	}
}
