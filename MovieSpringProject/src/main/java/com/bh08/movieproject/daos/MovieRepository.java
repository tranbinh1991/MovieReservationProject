package com.bh08.movieproject.daos;

import com.bh08.movieproject.models.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    
    Movie findFirstById(Long movieId);
    
    List<Movie> findByTitleIgnoreCaseContainsOrderByTitle(String title);    
    
    List<Movie> findAllByOrderByTitle();
}
