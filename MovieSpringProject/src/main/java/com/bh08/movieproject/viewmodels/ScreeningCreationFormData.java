/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Ez egy kötelező mező!")
    private String year;
    @NotBlank(message = "Ez egy kötelező mező!")
    private String month;
    @NotBlank(message = "Ez egy kötelező mező!")
    private String day;
    @NotBlank(message = "Ez egy kötelező mező!")
    private String hour;
    @NotBlank(message = "Ez egy kötelező mező!")
    private String minute;
    private String language;
}
