/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.MovieCategoryRepository;
import com.bh08.movieproject.models.Category;
import com.bh08.movieproject.models.MovieCategory;
import java.util.List;
import javax.annotation.PostConstruct;
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
    
    @PostConstruct
    private void initCategories() {
        // TODO: generate categories on application start
    }

    public MovieCategory saveMovieCategory(MovieCategory movieCategory) {
        return movieCategoryRepository.saveAndFlush(movieCategory);
    }
    
    public List<MovieCategory> findAll() {
        return movieCategoryRepository.findAll();
    }

    public List<MovieCategory> findByCategory(Category category) {        
        return movieCategoryRepository.findByCategory(category);
    }

    public void saveAllCategories() {
        for (int i = 0; i <= Category.WESTERN.ordinal(); i++) {
            MovieCategory movieCategory = new MovieCategory();
            movieCategory.setCategory(Category.values()[i]);
            saveMovieCategory(movieCategory);
        }
    }
}
