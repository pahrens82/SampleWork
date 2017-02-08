/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.daotests;

import com.sg.dvdlibrarydb.dao.DvdLibraryDao;
import com.sg.dvdlibrarydb.dao.SearchTerms;
import com.sg.dvdlibrarydb.models.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoTests {

    public DvdLibraryDaoTests() {
    }
    private DvdLibraryDao dao;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (DvdLibraryDao) ctx.getBean("DvdLibraryDao");
        JdbcTemplate Cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
       // cleaner.execute("delete from dvds");
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addOneDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie Title");
        dvd.setDate("2006");
        dvd.setDirector("Movie Director");
        dvd.setStudio("Production Studio");
        dvd.setRating("MPAA Rating");
        dvd.setNotes("Here are a series of notes about this film.");
        int expectedQuantity = 1;
        int expectedId = 0;

        dao.addDvd(dvd);
        assertEquals(expectedQuantity, dao.getAllDvds().size());
        assertEquals(expectedId, dao.getAllDvds().indexOf(dvd));
    }

    @Test
    public void removeOneDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie Title");
        dvd.setDate("2006");
        dvd.setDirector("Movie Director");
        dvd.setStudio("Production Studio");
        dvd.setRating("MPAA Rating");
        dvd.setNotes("Here are a series of notes about this film.");
        dao.addDvd(dvd);

        int expectedSize = 1;
        int expectedId = 0;
        assertEquals(expectedSize, dao.getAllDvds().size());
        assertEquals(expectedId, dao.getAllDvds().indexOf(dvd));

        int sizeAfterRemoval = 0;
        dao.removeDvd(1);
        assertEquals(sizeAfterRemoval, dao.getAllDvds().size());
        assertNull(dao.getDvdById(dvd.getDvdId()));
    }

    @Test
    public void updateDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie Title");
        dvd.setDate("2006");
        dvd.setDirector("Movie Director");
        dvd.setStudio("Production Studio");
        dvd.setRating("MPAA Rating");
        dvd.setNotes("Here are a series of notes about this film.");
        dao.addDvd(dvd);

        dvd.setTitle("Updated Title");
        dao.updateDvd(dvd);
        Dvd fromMem = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromMem, dvd);
    }

    @Test
    public void getAllDvds() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie Title");
        dvd.setDate("2006");
        dvd.setDirector("Movie Director");
        dvd.setStudio("Production Studio");
        dvd.setRating("MPAA Rating");
        dvd.setNotes("Here are a series of notes about this film.");
        dao.addDvd(dvd);

        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Another Title");
        dvd2.setDate("2100");
        dvd2.setDirector("Another Director");
        dvd2.setStudio("Another Studio");
        dvd2.setRating("Another Rating");
        dvd2.setNotes("Another series of notes.");
        dao.addDvd(dvd2);

        List<Dvd> dvdList = dao.getAllDvds();
        assertEquals(dvdList.size(), 2);
    }

    /*  @Test
    public void searchDvds() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("Movie1");
        dvd1.setDate("Date1");
        dvd1.setDirector("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setRating("MPAA Rating1");
        dvd1.setNotes("Notes1");
        dao.addDvd(dvd1);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Movie2");
        dvd2.setDate("Date2");
        dvd2.setDirector("Director2");
        dvd2.setStudio("Studio2");
        dvd2.setRating("MPAA Rating2");
        dvd2.setNotes("Notes2.");
        dao.addDvd(dvd2);
        
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("Movie3");
        dvd3.setDate("Dates3");
        dvd3.setDirector("Director3");
        dvd3.setStudio("Studio3");
        dvd3.setRating("MPAA Rating3");
        dvd3.setNotes("Notes3.");
        dao.addDvd(dvd3);
        
        Map<SearchTerms, String> criteria = new HashMap<>();
        criteria.put(SearchTerms.DATE, "Date1");
        List<Dvd> dvdList = dao.searchDvds(criteria);
        assertEquals(1, dvdList.size());
        assertEquals(dvd2, dvdList.get(1));
        
        criteria.put(SearchTerms.TITLE, "Movie3");
        dvdList = dao.searchDvds(criteria);
        assertEquals(1, dvdList);
     */
}
