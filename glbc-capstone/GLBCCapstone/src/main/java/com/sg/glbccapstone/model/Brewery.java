/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Brewery {

    private int breweryId;
    @NotEmpty(message = "Must provide a brewery name.")
    @Length(max = 50, message = "Name must be under 50 chars.")
    private String breweryName;
    private boolean approval;
    @NotEmpty(message = "Must provide a city.")
    @Length(max = 50, message = "City must be under 50 chars.")
    private String city;
    @NotEmpty(message = "Must provide a state.")
    @Length(max = 50, message = "State must be under 50 chars.")
    private String state;
    @Length(max = 50, message = "Brewmaster must be under 50 chars.")
    private String brewmaster;
    @Length(max = 180, message = "Summary must be under 180 chars.")
    private String summary;
    private String textBody;

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public boolean getApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrewmaster() {
        return brewmaster;
    }

    public void setBrewmaster(String brewmaster) {
        this.brewmaster = brewmaster;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the textBody
     */
    public String getTextBody() {
        return textBody;
    }

    /**
     * @param textBody the textBody to set
     */
    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
}
