/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Norbi
 */

@Getter
@Setter
@ToString
public class MoviePageFormData {
    
    private long id;
    private String title;
    private int movielength;
    private String actorList;
    private String director;
    private String description;
    private String moviecategory;
    private double rating;
}
