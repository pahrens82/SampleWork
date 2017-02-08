package com.sg.glbccapstone.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.glbccapstone.dao.BeerDao;
import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 *
 * @author apprentice
 */
public class BeerDaoTests {
    
    public BeerDaoTests() {
    }
    
    private BeerDao dao;
    
    @Before
    public void setUp(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (BeerDao) ctx.getBean("beerDaoDbImpl");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        JdbcTestUtils.executeSqlScript(cleaner, new ClassPathResource("/GlbcData/glbcBeerTest.sql"), true);
    }
    
    @Test
    public void testGetAllBeers(){
        //arrange
        List<Beer> beers;
        //act
        beers = dao.getAllBeer();
        //assert
        assertTrue("The default collection does not have 3 items.", beers.size()==3);
        
    }
    
    @Test
    public void testCreateGetEditRemoveBeer(){
        //arrange
        Beer test1 = new Beer();
        test1.setAbv(0);
        test1.setApproval(true);
        test1.setBeerName("Test1");
        test1.setBreweryId(1);
        test1.setHop("N/A");
        test1.setIbu(0);
        test1.setStyle("N/A");
        //act - create/get
        dao.createBeer(test1);
        Beer test2 = dao.getBeerById(test1.getBeerId());
        //assert
        assertTrue("The added beer does not have the expected ABV", test2.getAbv()==test1.getAbv());
        assertTrue("The added beer does not have the expected Approval value", test2.getApproval()==test1.getApproval());
        assertTrue("The added beer does not have the expected BeerName",test2.getBeerName().equals(test1.getBeerName()));
        assertTrue("The added beer does not have the expected Brewery ID",test2.getBreweryId()==test1.getBreweryId());
        assertTrue("The added beer does not have the expected Hop",test2.getHop().equals(test1.getHop()));
        assertTrue("The added beer does not have the expected Ibu",test2.getIbu()==(test1.getIbu()));
        assertTrue("The added beer does not have the expected Style",test2.getStyle().equals(test1.getStyle()));
        //arrange - update
        test1.setAbv(1);
        test1.setApproval(true);
        test1.setBeerName("Test2");
        test1.setBreweryId(1);
        test1.setHop("N/A - again");
        test1.setIbu(0);
        test1.setStyle("N/A - again");
        //act
        dao.updateBeer(test1);        
        Beer test3 = dao.getBeerById(test1.getBeerId());
        //assert
        assertTrue("The updated beer does not have the expected ABV", test3.getAbv()==test1.getAbv());
        assertTrue("The updated beer does not have the expected Approval value", test3.getApproval()==test1.getApproval());
        assertTrue("The updated beer does not have the expected BeerName",test3.getBeerName().equals(test1.getBeerName()));
        assertTrue("The updated beer does not have the expected Brewery ID",test3.getBreweryId()==test1.getBreweryId());
        assertTrue("The updated beer does not have the expected Hop",test3.getHop().equals(test1.getHop()));
        assertTrue("The updated beer does not have the expected Ibu",test3.getIbu()==(test1.getIbu()));
        assertTrue("The updated beer does not have the expected Style",test3.getStyle().equals(test1.getStyle()));
        //act - remove
        dao.removeBeer(test1.getBeerId());
        //assert
        Beer test4 = dao.getBeerById(test1.getBeerId());
        assertNull("The beer removed remains in the database.", test4);
    }
    
    @Test
	public void testSearchBeerByName() {
		//no search results, matching case
		Map<SearchTerm, String> emptySearchCriteria = new HashMap<>();
		emptySearchCriteria.put(SearchTerm.BEER, "Redd's");
		
		List<Beer> emptyResults = dao.searchBeer(emptySearchCriteria);
		
		assertTrue("The search results list should be empty", emptyResults.isEmpty());
		
		//one search result, matching case
		Map<SearchTerm, String> oneSearchCriteria = new HashMap<>();
		oneSearchCriteria.put(SearchTerm.BEER, "Epiphany");
		
		List<Beer> oneResultMatchCase = dao.searchBeer(oneSearchCriteria);
		
		assertTrue("The search results list should contain one entry", oneResultMatchCase.size() == 1);
		
		//one search result, non-matching case
		Map<SearchTerm, String> oneSearchCriteriaMismatchCase = new HashMap<>();
		oneSearchCriteriaMismatchCase.put(SearchTerm.BEER, "EpIpHaNy");
		
		List<Beer> oneResultNonMatchCase = dao.searchBeer(oneSearchCriteriaMismatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultNonMatchCase.size() == 1);
		
		//one search result, partial criteria, matching case
		Map<SearchTerm, String> partialCriteriaMatchCase = new HashMap<>();
		partialCriteriaMatchCase.put(SearchTerm.BEER, "pip");
		
		List<Beer> oneResultPartialCriteriaMatchCase = dao.searchBeer(partialCriteriaMatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultPartialCriteriaMatchCase.size() == 1);
		
		//one search result, partial criteria, non-matching case
		Map<SearchTerm, String> partialCriteriaNonMatchCase = new HashMap<>();
		partialCriteriaNonMatchCase.put(SearchTerm.BEER, "PiP");
		
		List<Beer> oneResultPartialCriteriaNonMatchCase = dao.searchBeer(partialCriteriaNonMatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultPartialCriteriaNonMatchCase.size() == 1);
	}
}
