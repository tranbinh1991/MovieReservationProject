package com.bh08.movieproject.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.MovieCategory;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    
    Movie findFirstById(Long movieId);
    
    List<Movie> findByTitleIgnoreCaseContainsOrderByTitle(String title);    
    
    //@Query(value = "select m from movie m order by m.title asc", nativeQuery = true)
    List<Movie> findAllByOrderByTitle();
}
