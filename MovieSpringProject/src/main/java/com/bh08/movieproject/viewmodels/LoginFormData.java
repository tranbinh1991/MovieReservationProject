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
