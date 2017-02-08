/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Permission;
import com.sg.glbccapstone.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class UserDaoDbImpl implements UserDao {

    

    private enum SQL {
        SELECT_ALL("select * from User"),
        SELECT_BY_USERNAME("select * from User where username = ?"),
        INSERT("insert into User (Permission_ID, username, password, FirstName, LastName, Email, enabled) values(?, ?, ?, ?, ?, ?, ?)"),
        INSERT_USER_PERMISSION("insert into UserPermissions (username, Permission_ID) values (?,?)"),
        DELETE("delete from User where username = ?"),
        DELETE_USER_PERMISSIONS("delete from UserPermissions where username = ?"),
        DELETE_USER_PERMISSION("delete from UserPermissions where username = ? and Permission_ID = ?"),
        UPDATE("update User set enabled = ?, Permission_ID = ? where username = ?")
        ;
        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }
    }
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User newUser) throws DuplicateKeyException {
        // First insert user data into the users table and then insert data into
        // the authorities table (failing to do so will result in foreign key
        // constraint errors)
        
        jdbcTemplate.update(SQL.INSERT.statement,
                newUser.getPermissionId(),
                newUser.getUserName(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getEnabled());
        newUser.setUserId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));
        //now insert user permissions
        ArrayList<Permission> permissions = newUser.getPermissions();
        for(Permission permission: permissions){
            jdbcTemplate.update(SQL.INSERT_USER_PERMISSION.statement,
                    newUser.getUserName(),
                    permission.getId()
                    );
        }
        
        return newUser;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUserByUserName(String userName) {
        User user = jdbcTemplate.queryForObject(SQL.SELECT_BY_USERNAME.statement, new UserMapper(), userName);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {
        jdbcTemplate.update(SQL.UPDATE.statement, user.getEnabled(), user.getPermissionId(), user.getUserName());
        jdbcTemplate.update(SQL.DELETE_USER_PERMISSIONS.statement, user.getUserName());
        for(Permission permission: user.getPermissions()){
            jdbcTemplate.update(SQL.INSERT_USER_PERMISSION.statement,
                    user.getUserName(),
                    permission.getId()
                    );
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeUser(String userName) {
        //need to remove al lthe entries in the userpermissions table first.
        jdbcTemplate.update(SQL.DELETE_USER_PERMISSIONS.statement, userName);
        //need to delete all the user second
        jdbcTemplate.update(SQL.DELETE.statement, userName);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL.SELECT_ALL.statement, new UserMapper());
    }
    
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("User_ID"));
            user.setUserName(rs.getString("username"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setPermissionId(Integer.parseInt(rs.getString("Permission_ID")));
            user.setEmail(rs.getString("Email"));
            user.setEnabled("1".equals(rs.getString("enabled")));
            user.setPassword(rs.getString("password"));
            return user;
        }
        
    }
    
}
