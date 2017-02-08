/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.model;

/**
 *
 * @author apprentice
 */
public enum Permission {
    
    ADMIN(1,"ROLE_ADMIN","Site Admin: Approve content changes and manage users."),
    SUPER_USER(2,"ROLE_SUPER","Allowed to Add/Edit/Remove content and submit for Approval."),
    USER(3,"ROLE_USER","Allowed to comment."),
    GUEST(4,"ROLS_GUEST","Allowed to veiw."),
    RESTRICTED(5,"ROLE_RESTRICTED","No access."),
    ;
    private final String role;
    private final String description;
    private final int id;
    
    
    private Permission(int id, String role, String description){
        this.role = role;
        this.description = description;
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public static Permission getPermissionById(int id){
        switch(id){
            case 1:
                return ADMIN;
            case 2:
                return SUPER_USER;
            case 3:
                return USER;
            case 4:
                return GUEST;
            default:
                return RESTRICTED;
        }
    }
    
    public String getDescription(){
        return this.description;
    }
}
