/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Beer;
import com.sg.glbccapstone.model.SearchTerm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class BeerDaoDBImpl implements BeerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private enum SQL {
		//SELECT_BY_OMNI_SEARCH("select * from Beer where BeerName like '%?%' or Style like '%?%' or Hop like '%?%' or ABV like '%?%' or IBU like '%?%'"),
        SELECT_ALL("select * from Beer"),
        SELECT_BY_ID("select * from Beer where Beer_ID = ?"),
        INSERT("insert into Beer(BeerName, Style, Hop, ABV, IBU, Approval, Summary, TextBody, Brewery_ID) values(?, ?, ?, ?, ?, ?, ?, ?, ?)"),
        DELETE("delete from Beer where Beer_ID = ?"),
        UPDATE("update Beer set BeerName = ?, Style = ?, Hop = ?, ABV = ?, IBU = ?, Approval = ?, Summary = ?, TextBody = ?, Brewery_ID = ? where Beer_ID = ?"),
        SELECT_BY_BREWERY_ID("select * from Beer where Brewery_ID = ?"),
		SELECT_BY_APPROVAL("select * from Beer where Approval = ?");

        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }

        public String getStatement() {
            return this.statement;
        }
    }

	//create
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Beer createBeer(Beer beer) {

        jdbcTemplate.update(SQL.INSERT.getStatement(),
                beer.getBeerName(),
                beer.getStyle(),
                beer.getHop(),
                beer.getAbv(),
                beer.getIbu(),
				beer.getApproval(),
				beer.getSummary(),
				beer.getTextBody(),
				beer.getBreweryId()
        );
		
        beer.setBeerId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));
		
        return beer;
    }
	
	//retrieve
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Beer> getAllBeer() {
        return jdbcTemplate.query(SQL.SELECT_ALL.getStatement(), new BeerMapper());
    }
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Beer> getAllBeerByApproval(boolean approval) {
		return jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new BeerMapper(), approval);
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Beer getBeerById(int beerId) {
        try {
            Beer beer = jdbcTemplate.queryForObject(SQL.SELECT_BY_ID.getStatement(), new BeerMapper(), beerId);
            return beer;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Beer> getAllBeerByBreweryId(int breweryId) {
		return jdbcTemplate.query(SQL.SELECT_BY_BREWERY_ID.getStatement(), new BeerMapper(), breweryId);
	}
	
	//update
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBeer(Beer beer) {

        jdbcTemplate.update(SQL.UPDATE.getStatement(),
			beer.getBeerName(),
			beer.getStyle(),
			beer.getHop(),
			beer.getAbv(),
			beer.getIbu(),
			beer.getApproval(),
			beer.getSummary(),
			beer.getTextBody(),
			beer.getBreweryId(),
			beer.getBeerId()
        );
    }
	
	//remove
    @Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeBeer(int id) {

        jdbcTemplate.update(SQL.DELETE.getStatement(), id);
    }

    //Usage to be determined at a later date.
    //this may be something that belings in the search or is accessed by search
/**    
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//    public List<Beer> getAllBeerByBreweryId(int breweryId) {
//        try {
//            return jdbcTemplate.query(SQL.SELECT_BY_BREWERY_ID.statement, new BeerMapper(), breweryId);
//        }catch(EmptyResultDataAccessException ex){
//            return null;
//        }
//    }
* */
	@Override
	public List<Beer> searchBeer(Map<SearchTerm, String> criteria) {
		List<Beer> allBeer = jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new BeerMapper(), true);
		
		String searchCriteria = criteria.get(SearchTerm.BEER).toLowerCase();
		
		Predicate<Beer> nameMatches;
		Predicate<Beer> styleMatches;
		Predicate<Beer> hopMatches;
		Predicate<Beer> abvMatches;
		Predicate<Beer> ibuMatches;
		
		Predicate<Beer> truePredicate = (beer) -> { return true; };
		
		nameMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (beer) -> beer.getBeerName().toLowerCase().contains(searchCriteria);
		styleMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (beer) -> beer.getStyle().toLowerCase().contains(searchCriteria);
		hopMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (beer) -> beer.getHop().toLowerCase().contains(searchCriteria);
		
//		abvMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (beer) -> beer.getAbv() == Float.parseFloat(searchCriteria);
//		ibuMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (beer) -> beer.getIbu() == Float.parseFloat(searchCriteria);

//		return allBeer.stream().filter(nameMatches.or(styleMatches).or(hopMatches).or(abvMatches).or(ibuMatches)).collect(Collectors.toList());
		return allBeer.stream().filter(nameMatches.or(styleMatches).or(hopMatches)).collect(Collectors.toList());
	}
	
	private static final class BeerMapper implements RowMapper<Beer> {

        @Override
        public Beer mapRow(ResultSet rs, int i) throws SQLException {
            Beer beer = new Beer();
			
			beer.setBeerId(rs.getInt("Beer_ID"));
            beer.setBeerName(rs.getString("BeerName"));
            beer.setStyle(rs.getString("Style"));
            beer.setHop(rs.getString("Hop"));
            beer.setStyle(rs.getString("Style"));
            beer.setAbv(rs.getFloat("ABV"));
            beer.setIbu(rs.getInt("IBU"));
			beer.setApproval(rs.getBoolean("Approval"));
			beer.setSummary(rs.getString("Summary"));
			beer.setTextBody(rs.getString("TextBody"));
			beer.setBreweryId(rs.getInt("Brewery_ID"));

            return beer;
        }
    }
}
