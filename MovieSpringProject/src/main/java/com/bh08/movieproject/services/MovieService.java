package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.MovieRepository;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.MovieCategory;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }
    
    public List<Movie> findAll() {
        return movieRepository.findAllByOrderByTitle();
    }
    

    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Movie findById(Long movieId){
        return movieRepository.findFirstById(movieId);
    }
    
    public List<Movie> findByTitleIgnoreCaseContains(String title) {
        return movieRepository.findByTitleIgnoreCaseContainsOrderByTitle(title);
    }
    
//    public List<Movie> findByMovieCategory(MovieCategory movieCategory) {
//        return movieRepository.findByMovieCategory(movieCategory);
//    }

}
