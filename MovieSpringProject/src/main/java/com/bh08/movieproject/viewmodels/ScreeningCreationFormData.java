/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Zoli
 */
@Getter
@Setter
public class ScreeningCreationFormData {
    
    private String movie;
    private String room;
    @NotEmpty(message = "Ez a mező nem lehet üres!")
    private String year;
    @NotEmpty(message = "Ez a mező nem lehet üres!")
    private String month;
    @NotEmpty(message = "Ez a mező nem lehet üres!")
    private String day;
    @NotEmpty(message = "Ez a mező nem lehet üres!")
    private String hour;
    @NotEmpty(message = "Ez a mező nem lehet üres!")
    private String minute;
    private String language;
}
