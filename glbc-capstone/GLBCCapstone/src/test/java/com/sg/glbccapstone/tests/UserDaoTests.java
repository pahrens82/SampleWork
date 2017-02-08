/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.tests;

import com.sg.glbccapstone.dao.UserDao;
import com.sg.glbccapstone.model.User;
import java.util.List;
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
public class UserDaoTests {
    
    public UserDaoTests() {
    }
    
    private UserDao dao;
    
    @Before
    public void setUp(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (UserDao) ctx.getBean("userDaoDbImpl");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        JdbcTestUtils.executeSqlScript(cleaner, new ClassPathResource("/GlbcData/GLBC_testCreateDB.sql"), true);
    }
    
    @Test
    public void testGetAllUsers(){
        //arrange
        List<User> test;
        //act
        test = dao.getAllUsers();
        //assert
        assertTrue("The default user list does not have the expected number of users", test.size()==3);
    }
    
    @Test
	public void testCreateGetUpdateDeleteUser() {
		User user = new User();
		
		user.setPermissionId(1);
		user.setUserName("TestUser");
		user.setPassword("password");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("test@user.com");
		user.setEnabled(true);
		
		dao.addUser(user);
		
		
		//assert create
		assertTrue("Unexpected number of users after adding a user.", dao.getAllUsers().size() == 4);
		
		//act get
		User createdUser = dao.getUserByUserName(user.getUserName());
		
		//assert get
		assertTrue("The add User does not have the expected UserName.", createdUser.getUserName().equals(user.getUserName()));
		assertTrue("The add User does not have the expected Password.", createdUser.getPassword().equals(user.getPassword()));
		assertTrue("The add User does not have the expected FirstName.", createdUser.getFirstName().equals(user.getFirstName()));
		assertTrue("The add User does not have the expected LastName.", createdUser.getLastName().equals(user.getLastName()));
		assertTrue("The add User does not have the expected Email.", createdUser.getEmail().equals(user.getEmail()));
		assertTrue("The add User does not have the expected Enabled value.", createdUser.getEnabled() == user.getEnabled());
		
		user.setPermissionId(2);
		user.setEnabled(false);
		
		//act update
		dao.updateUser(user);
		User updatedUser = dao.getUserByUserName("TestUser");
		
		//assert update
		assertTrue("The edited User does not have the expected UserName.", updatedUser.getUserName().equals(user.getUserName()));
		assertTrue("The edited User does not have the expected Password.", updatedUser.getPassword().equals(user.getPassword()));
		assertTrue("The edited User does not have the expected FirstName.", updatedUser.getFirstName().equals(user.getFirstName()));
		assertTrue("The edited User does not have the expected LastName.", updatedUser.getLastName().equals(user.getLastName()));
		assertTrue("The edited User does not have the expected Email.", updatedUser.getEmail().equals(user.getEmail()));
		assertTrue("The edited User does not have the expected Enabled value.", updatedUser.getEnabled() == user.getEnabled());
		assertTrue("The edited User does not have the expected Permission ID.", updatedUser.getPermissionId() == user.getPermissionId());
		
		//act remove
		dao.removeUser(updatedUser.getUserName());
		assertTrue("A user wasn't removed as expected", dao.getAllUsers().size() == 3);
	}
    
}
