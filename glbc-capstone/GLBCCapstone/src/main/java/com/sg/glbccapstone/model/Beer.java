package com.sg.glbccapstone.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Beer {

    private int beerId;
    @NotEmpty(message="Style required.")
    @Length(max = 30, message = "Style must be under 30 chars")
    private String style;
    @Length(max = 30, message = "Hop must be under 30 chars")
    private String hop;
    @Range(min = 0, max = 30, message = "ABV must be between 0-30")
    private float abv;
    @Min(value=0, message= "IBU must be at least 0.")
    private int ibu;
    @NotNull(message = "Please specify a brewery.")
    @Min(value = 1, message = "Invalid brewery choice.")
    private int breweryId;
    @NotEmpty(message = "Please specify a Beer name.")
    @Length(max=50, message ="Name must be under 50 chars")
    private String beerName;
    private boolean approval;
    private String textBody;
    @Length(max=180, message = "Summary must be under 180 chars")
    private String summary;

    

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getHop() {
        return hop;
    }

    public void setHop(String hop) {
        this.hop = hop;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    /**
     * @return the beerName
     */
    public String getBeerName() {
        return beerName;
    }

    /**
     * @param beerName the beerName to set
     */
    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    /**
     * @return the abv
     */
    public float getAbv() {
        return abv;
    }

    /**
     * @param abv the abv to set
     */
    public void setAbv(float abv) {
        this.abv = abv;
    }

    /**
     * @return the ibu
     */
    public int getIbu() {
        return ibu;
    }

    /**
     * @param ibu the ibu to set
     */
    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    /**
     * @return the approval
     */
    public boolean getApproval() {
        return approval;
    }

    /**
     * @param approval the approval to set
     */
    public void setApproval(boolean approval) {
        this.approval = approval;
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

    
}
