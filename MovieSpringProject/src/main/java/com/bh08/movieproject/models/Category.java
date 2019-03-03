/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.models;

/**
 *
 * @author Zoli
 */
public enum Category {
    ACTION("Akció"),
    ADVENTURE("Kaland"),
    ANIMATION("Animációs"),
    BIOGRAPHY("Életrajzi"),
    COMEDY("Vígjáték"),
    CRIME("Bűnügyi"),
    DRAMA("Dráma"),
    FAMILY("Családi"),
    FANTASY("Fantasy"),
    FILMNOIR("Film-noir"),
    HISTORY("Történelmi"),
    HORROR("Horror"),
    MUSIC("Zenés"),    
    MYSTERY("Mystery"),
    ROMANCE("Romantikus"),
    SCIFI("Sci-fi"),
    SPORT("Sport"),
    THRILLER("Thriller"),
    WAR("Háborús"),
    WESTERN("Western");
    
    private String hungarianName;
    
    Category (String hungarianName) {
        this.hungarianName = hungarianName;
    }

    public String getHungarianName() {
        return hungarianName;
    }
    
    
}
