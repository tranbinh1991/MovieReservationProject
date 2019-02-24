/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.MovieSpringProject;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Zoli
 */
@Configuration
@ComponentScan(basePackages = "com.bh08.movieproject")
@EntityScan(basePackages = "com.bh08.movieproject")
public class MovieSpringProjectConfiguration {
    
    
}
