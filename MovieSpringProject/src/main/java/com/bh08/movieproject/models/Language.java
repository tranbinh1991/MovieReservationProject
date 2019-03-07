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
public enum Language {
    HUNGARIAN("magyar"),
    ENGLISH("angol"),
    FRENCH("francia");
    
    private String hungarianName;
    
    Language (String hungarianName) {
        this.hungarianName = hungarianName;
    }

    public String getHungarianName() {
        return hungarianName;
    }
}
