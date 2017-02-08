/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarydb.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Dvd {

    private int dvdId;
    @NotEmpty(message = "You must supply a title for the Dvd")
    @Length(max = 50, message = "The title can not be more than 50 characters")
    private String title;
    @NotEmpty(message = "You must supply a date for the Dvd")
    @Length(max = 50, message = "The date can not be more than 50 characters")
    private String date;
    @NotEmpty(message = "You must supply a director for the Dvd")
    @Length(max = 50, message = "The director's name can not be more than 50 characters")
    private String director;
    @NotEmpty(message = "You must supply a studio for the Dvd")
    @Length(max = 50, message = "The studio name can not be more than 50 characters")
    private String studio;
    @NotEmpty(message = "You must supply the MPAA rating for the Dvd")
    @Length(max = 50, message = "The rating can not be more than 50 characters")
    private String rating;
    @Length(max = 150, message = "Your notes can not exceed 150 characters")
    private String notes;

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
