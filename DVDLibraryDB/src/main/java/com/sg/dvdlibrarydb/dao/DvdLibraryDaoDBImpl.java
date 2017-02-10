/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.dao;

import com.sg.dvdlibrarydb.models.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoDBImpl implements DvdLibraryDao {

    private static final String ADD_DVD
            = "insert into Dvd (title, date, director, studio, rating, notes) values (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_DVD
            = "delete from Dvd where dvdId = ?";
    private static final String SELECT_DVD
            = "select * from Dvd where dvdId = ?";
    private static final String UPDATE_DVD
            = "update Dvd set title = ?, date = ?, director = ?, studio = ?, rating = ?, notes = ? where dvdId = ?";
    private static final String SELECT_ALL_DVDS
            = "select * from Dvd";
    private static final String SELECT_DVDS_BY_DIRECTOR
            = "select * from Dvd where director = ?";
    private static final String SELECT_DVDS_BY_STUDIO
            = "select * from Dvd where studio = ?";
    private static final String SELECT_DVDS_BY_RATING
            = "select * from Dvd where rating = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(ADD_DVD,
                dvd.getTitle(),
                dvd.getDate(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getRating(),
                dvd.getNotes()
        );
        dvd.setDvdId(jdbcTemplate.queryForObject("select Last_INSERT_ID()", Integer.class));
        return dvd;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(DELETE_DVD, dvdId);
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(UPDATE_DVD,
                dvd.getTitle(),
                dvd.getDate(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getRating(),
                dvd.getNotes()
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_DVD, new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Dvd> searchDvds(Map<SearchTerms, String> criteria) {
        return jdbcTemplate.query(SELECT_DVD, new DvdMapper());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Dvd> searchDvdsByDirector(Map<SearchTerms, String> criteria) {
        return jdbcTemplate.query(SELECT_DVDS_BY_DIRECTOR, new DvdMapper());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Dvd> searchDvdsByStudio(Map<SearchTerms, String> criteria) {
        return jdbcTemplate.query(SELECT_DVDS_BY_STUDIO, new DvdMapper());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Dvd> searchDvdsByRating(Map<SearchTerms, String> criteria) {
        return jdbcTemplate.query(SELECT_DVDS_BY_RATING, new DvdMapper());
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setDate(rs.getString("date"));
            dvd.setDirector(rs.getString("director"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }

}
