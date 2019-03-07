/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jbacs
 */

@Getter
@Setter
@ToString
public class LoginFormData {

    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String password;
    
}