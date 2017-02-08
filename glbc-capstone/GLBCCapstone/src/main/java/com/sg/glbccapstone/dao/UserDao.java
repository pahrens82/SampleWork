/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.User;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

/**
 *
 * @author apprentice
 */
public interface UserDao {
    public User addUser (User newUser) throws DuplicateKeyException;
    public void removeUser(String userName);
    public List<User> getAllUsers();
    public User getUserByUserName(String userName);
    public void updateUser(User user);
}
