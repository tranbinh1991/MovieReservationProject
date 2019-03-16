package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.MovieRepository;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.models.Screening;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        List<Movie> completeMovieList = movieRepository.findByTitleIgnoreCaseContainsOrderByTitle(title);
        return filterByAvailableScreenings(completeMovieList);
    }
    
    public List<Movie> findMoviesWithFutureScreenings() {
        List<Movie> completeMovieList = movieRepository.findAllByOrderByTitle();
        return filterByAvailableScreenings(completeMovieList);
    }

    private List<Movie> filterByAvailableScreenings(List<Movie> completeMovieList) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie movie : completeMovieList) {
            if (doesMovieHaveFutureScreenings(movie)) {
                filteredMovieList.add(movie);
            }
        }
        return filteredMovieList;
    }
    
    private boolean doesMovieHaveFutureScreenings(Movie movie) {
        List<Screening> screeningList = movie.getScreenings();
        if (screeningList.isEmpty()) {
            return false;
        }
        for (Screening screening : screeningList) {
            if (screening.getTime().isAfter(LocalDateTime.now()) && !screening.isOccupied()) {
                return true;
            }
        }
        return false;
    }

}
