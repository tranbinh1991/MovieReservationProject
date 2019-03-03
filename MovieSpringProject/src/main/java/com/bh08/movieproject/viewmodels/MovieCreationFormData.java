/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Zoli
 */
@Getter
@Setter
@ToString
public class MovieCreationFormData {
    
    @NotEmpty
    private String title;
    private String movieLength;
    
    private String director;
    private String actor1;
    private String actor2;
    private String actor3;
    private String actor4;
    private String actor5;
    private String actor6;
    
    private String movieCategory1;
    private String movieCategory2;
    private String movieCategory3;
    
    private String description;
    private String rating;
    
}
