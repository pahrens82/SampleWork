package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CategoryDaoDBImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private enum SQL {
        SELECT_ALL("select * from Category"),
        SELECT_BY_ID("select * from Category where Category_ID = ?"),
        INSERT("insert into Category (CategoryName) values (?)"),
        DELETE("delete from Category where Category_ID = ?"),
        UPDATE("update Category set CategoryName = ? where Category_ID = ?");

        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }

        public String getStatement() {
            return this.statement;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL.INSERT.getStatement(),
                category.getCategoryName()
        );

        category.setCategoryId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));

        return category;
    }

    @Override
    public void removeCategoryById(int categoryId) {
        jdbcTemplate.update(SQL.DELETE.getStatement(), categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL.UPDATE.getStatement(),
                category.getCategoryName(),
                category.getCategoryId()
        );
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL.SELECT_ALL.getStatement(), new CategoryMapper());
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL.SELECT_BY_ID.getStatement(), new CategoryMapper(), categoryId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();

            category.setCategoryId(rs.getInt("Category_ID"));
            category.setCategoryName(rs.getString("CategoryName"));

            return category;
        }

    }

}
