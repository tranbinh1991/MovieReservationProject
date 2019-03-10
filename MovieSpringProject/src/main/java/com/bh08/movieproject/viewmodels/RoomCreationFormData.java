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
public class RoomCreationFormData {
    
    @NotBlank
    private String roomNumber;
    @NotBlank
    private String rowCount;
    @NotBlank
    private String columnCount;
}
