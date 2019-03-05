package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.ChairRepository;
import com.bh08.movieproject.daos.MovieRepository;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.User;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	

	
	public Movie saveMovie(Movie movie) {
		return  repository.saveAndFlush(movie);
                
              
		
	}
        
        public Movie findByTitle (String title){
            return repository.findByTitle(title);
        }
        
         public Movie findById (long id){
            return repository.findById(id);
        }


        
}
