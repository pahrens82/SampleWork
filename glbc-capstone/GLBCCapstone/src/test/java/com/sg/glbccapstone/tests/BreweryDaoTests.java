/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.tests;

import com.sg.glbccapstone.dao.BreweryDao;
import com.sg.glbccapstone.model.Brewery;
import com.sg.glbccapstone.model.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNull;
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
public class BreweryDaoTests {
    
    public BreweryDaoTests() {
    }
    
    private BreweryDao dao;
    
    @Before
    public void setUp(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (BreweryDao) ctx.getBean("breweryDaoDbImpl");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        JdbcTestUtils.executeSqlScript(cleaner, new ClassPathResource("/GlbcData/glbcBreweryTest.sql"), true);
    }
    
    @Test
    public void testGetAllBreweries(){
        //arrange
        List<Brewery> breweries;
        //act
        breweries = dao.getAllBreweries();
        //assert
        assertTrue("The default collection does not have 3 items.", breweries.size()==3);
    }
    
    @Test
    public void testCreateGetEditRemoveBrewery(){
        //arrange
        Brewery test1 = new Brewery();
        test1.setApproval(true);
        test1.setBreweryId(0);
        test1.setBreweryName("Test1");
        test1.setBrewmaster("1");
        test1.setCity("Akron");
        test1.setState("Ohio");
        test1.setSummary("2");
        test1.setTextBody("3");
        //act - create/get
        dao.createBrewery(test1);
        Brewery test2 = dao.getBreweryById(test1.getBreweryId());
        //assert
        assertTrue("The added Brewery does not have the expected Approval",test2.getApproval()==test1.getApproval());
        assertTrue("The added Brewery does not have the expected Name ",test2.getBreweryName().equals(test1.getBreweryName()));
        assertTrue("The added Brewery does not have the expected Brewmaster",test2.getBrewmaster().equals(test1.getBrewmaster()));
        assertTrue("The added Brewery does not have the expected City",test2.getCity().equals(test1.getCity()));
        assertTrue("The added Brewery does not have the expected State",test2.getState().equals(test1.getState()));
        assertTrue("The added Brewery does not have the expected Summary",test2.getSummary().equals(test1.getSummary()));
        assertTrue("The added Brewery does not have the expected Text Body",test2.getTextBody().equals(test1.getTextBody()));
        //arrange - update
        test1.setApproval(true);
        test1.setBreweryName("Test1");
        test1.setBrewmaster("John");
        test1.setCity("Akron");
        test1.setState("Ohio");
        test1.setSummary("N/A");
        test1.setTextBody("N/A");
        //act
        dao.updateBrewery(test1);
        Brewery test3 = dao.getBreweryById(test1.getBreweryId());
        //assert
        assertTrue("The updated Brewery does not have the expected Approval",test3.getApproval()==test1.getApproval());
        assertTrue("The updated Brewery does not have the expected Name ",test3.getBreweryName().equals(test1.getBreweryName()));
        assertTrue("The updated Brewery does not have the expected Brewmaster",test3.getBrewmaster().equals(test1.getBrewmaster()));
        assertTrue("The updated Brewery does not have the expected City",test3.getCity().equals(test1.getCity()));
        assertTrue("The updated Brewery does not have the expected State",test3.getState().equals(test1.getState()));
        assertTrue("The updated Brewery does not have the expected Summary",test3.getSummary().equals(test1.getSummary()));
        assertTrue("The updated Brewery does not have the expected Text Body",test3.getTextBody().equals(test1.getTextBody()));
        //act - remove
        dao.removeBrewery(test1.getBreweryId());
        //assert
        assertNull("The brewery removed remains in the database.", dao.getBreweryById(test1.getBreweryId()));
    }
    
	@Test
	public void testSearchBreweryByName() {
		
		//no search results, matching case
		Map<SearchTerm, String> emptySearchCriteria = new HashMap<>();
		emptySearchCriteria.put(SearchTerm.BREWERY, "Snafu");
		
		List<Brewery> emptyResults = dao.searchBrewery(emptySearchCriteria);
		
		assertTrue("The search results should be empty", emptyResults.isEmpty());
		
		//one search result, matching case
		Map<SearchTerm, String> oneSearchCriteria = new HashMap<>();
		oneSearchCriteria.put(SearchTerm.BREWERY, "Bissell Brothers Brewing");
		
		List<Brewery> oneResultMatchCase = dao.searchBrewery(oneSearchCriteria);
		
		assertTrue("The search results list should contain one entry", oneResultMatchCase.size() == 1);
		
		//one search result, non-matching case
		Map<SearchTerm, String> oneSearchCriteriaMismatchCase = new HashMap<>();
		oneSearchCriteriaMismatchCase.put(SearchTerm.BREWERY, "BaRrElEd SoUlS bReWiNg CoMpAnY");
		
		List<Brewery> oneResultNonMatchCase = dao.searchBrewery(oneSearchCriteriaMismatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultNonMatchCase.size() == 1);
		
		//one search result, partial criteria, matching case
		Map<SearchTerm, String> partialCriteriaMatchCase = new HashMap<>();
		partialCriteriaMatchCase.put(SearchTerm.BREWERY, "Foundation");
		
		List<Brewery> oneResultPartialCriteriaMatchCase = dao.searchBrewery(partialCriteriaMatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultPartialCriteriaMatchCase.size() == 1);
		
		//one search result, partial criteria, non-matching case
		Map<SearchTerm, String> partialCriteriaNonMatchCase = new HashMap<>();
		partialCriteriaNonMatchCase.put(SearchTerm.BREWERY, "FoUnDaTiOn");
		
		List<Brewery> oneResultPartialCriteriaNonMatchCase = dao.searchBrewery(partialCriteriaNonMatchCase);
		
		assertTrue("The search results list should contain one entry", oneResultPartialCriteriaNonMatchCase.size() == 1);
	}
}
