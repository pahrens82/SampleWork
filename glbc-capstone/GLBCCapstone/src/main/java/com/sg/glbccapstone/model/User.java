/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.model;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class User {
    
    private int userId;
    //@Range(min=1, max=5, message="Invalid User Permission Type")
    @NotNull(message="Required to make an account.")
    private int permissionId;
    @NotEmpty(message="User Name Required.")
    @Length(max=50, message="Max characters allowed: 50")
    private String userName;
    @NotEmpty(message="Password Required.")
    @Length(max=50, message="Max characters allowed: 50")
    private String password;
    @Length(max=50, message="Max characters allowed: 50")
    private String firstName;
    @Length(max=50, message="Max characters allowed: 50")
    private String lastName;
    @Length(max=80, message="Max characters allowed: 80")
    private String email;
    @NotNull(message="Required to make an account.")
    private boolean enabled;
    private ArrayList<Permission> permissions = new ArrayList<>();
    
    
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the permissionId
     */
    public int getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId the permissionId to set
     */
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.email = Email;
    }

    /**
     * @return the authorities
     */
    public ArrayList<Permission> getPermissions() {
        return permissions;
    }
    
    public void addPermission(Permission permission){
        permissions.add(permission);
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the enabled
     */
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    }
