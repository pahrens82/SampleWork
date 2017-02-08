/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Brewery;
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
public class BreweryDaoDBImpl implements BreweryDao {

    private enum SQL {
        SELECT_ALL("select * from Brewery"),
        SELECT_BY_ID("select * from Brewery where Brewery_ID = ?"),
        INSERT("insert into Brewery(BreweryName, City, State, Brewmaster, Approval, Summary, TextBody) values(?, ?, ?, ?, ?, ?, ?)"),
        DELETE_BEER_BY_BREWERY_ID("delete from Beer where Brewery_ID = ?"),
        DELETE_BREWEY_BY_BREWERY_ID("delete from Brewery where Brewery_ID = ?"),
        UPDATE("update Brewery set BreweryName = ?, City = ?, State = ?, Brewmaster = ?, Approval = ?, Summary = ?, TextBody = ? where Brewery_ID = ?;"),
        SELECT_BEER_BY_BREWERY_ID("select * from Beer join Brewery where Brewery.Brewery_ID = ?"),
		SELECT_BY_APPROVAL("select * from Brewery where Approval = ?");

        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }

        public String getStatement() {
            return this.statement;
        }
    }

    private JdbcTemplate jdbcTemplate;

	
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //create
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Brewery createBrewery(Brewery brewery) {

        jdbcTemplate.update(SQL.INSERT.getStatement(),
            brewery.getBreweryName(),
			brewery.getCity(),
			brewery.getState(),
			brewery.getBrewmaster(),
			brewery.getApproval(),
			brewery.getSummary(),
			brewery.getTextBody()
        );

        brewery.setBreweryId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));

        return brewery;
    }

    //retrieve
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Brewery> getAllBreweries() {
            return jdbcTemplate.query(SQL.SELECT_ALL.getStatement(), new BreweryMapper());
    }
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Brewery> getAllBreweriesByApproval(boolean approval) {
		return jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new BreweryMapper(), approval);
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Brewery getBreweryById(int breweryId) {
        try{
            return jdbcTemplate.queryForObject(SQL.SELECT_BY_ID.getStatement(),
                new BreweryMapper(), breweryId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //update
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBrewery(Brewery brewery) {

        jdbcTemplate.update(SQL.UPDATE.getStatement(),
			brewery.getBreweryName(),
			brewery.getCity(),
			brewery.getState(),
			brewery.getBrewmaster(),
			brewery.getApproval(),
			brewery.getSummary(),
			brewery.getTextBody(),
			brewery.getBreweryId()
        );
    }

    //remove
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeBrewery(int id) {
        jdbcTemplate.update(SQL.DELETE_BEER_BY_BREWERY_ID.getStatement(), id);
        jdbcTemplate.update(SQL.DELETE_BREWEY_BY_BREWERY_ID.getStatement(), id);
    }
	
	@Override
	public List<Brewery> searchBrewery(Map<SearchTerm, String> criteria) {
		List<Brewery> allBreweries = jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new BreweryMapper(), true);
		
		String searchCriteria = criteria.get(SearchTerm.BREWERY).toLowerCase();
		
		Predicate<Brewery> nameMatches;
		Predicate<Brewery> cityMatches;
		Predicate<Brewery> stateMatches;
		Predicate<Brewery> brewmasterMatches;
		
		Predicate<Brewery> truePredicate = (brewery) -> { return true; };
		
		nameMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (brewery) -> brewery.getBreweryName().toLowerCase().contains(searchCriteria);
		cityMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (brewery) -> brewery.getCity().toLowerCase().contains(searchCriteria);
		stateMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (brewery) -> brewery.getState().toLowerCase().contains(searchCriteria);
		brewmasterMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (brewery) -> brewery.getBrewmaster().toLowerCase().contains(searchCriteria);

		return allBreweries.stream().filter(nameMatches.or(cityMatches).or(stateMatches).or(brewmasterMatches)).collect(Collectors.toList());
	}

    private static final class BreweryMapper implements RowMapper<Brewery> {

        @Override
        public Brewery mapRow(ResultSet rs, int i) throws SQLException {
            Brewery brewery = new Brewery();

            brewery.setBreweryId(rs.getInt("Brewery_ID"));
            brewery.setBreweryName(rs.getString("BreweryName"));
            brewery.setCity(rs.getString("City"));
            brewery.setState(rs.getString("State"));
            brewery.setBrewmaster(rs.getString("Brewmaster"));
            brewery.setApproval(rs.getBoolean("Approval"));
            brewery.setSummary(rs.getString("Summary"));
            brewery.setTextBody(rs.getString("TextBody"));

            return brewery;
        }
    }
}
