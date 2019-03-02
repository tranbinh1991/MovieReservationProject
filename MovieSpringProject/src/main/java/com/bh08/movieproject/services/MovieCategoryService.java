/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.MovieCategoryRepository;
import com.bh08.movieproject.daos.UserRepository;
import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
public class MovieCategoryService {
    
    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    public MovieCategory saveMovieCategory(MovieCategory movieCategory) {
        return movieCategoryRepository.saveAndFlush(movieCategory);
    }
}