/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Actor;
import com.bh08.movieproject.models.Category;
import com.bh08.movieproject.models.Director;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.services.ActorService;
import com.bh08.movieproject.services.DirectorService;
import com.bh08.movieproject.services.MovieCategoryService;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.viewmodels.MovieCreationFormData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zoli
 */
@Controller
public class MovieAdderController {
    
    @Autowired
    private MovieService movieService;    
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ActorService actorService;
    
    @RequestMapping(value = "movieadder", method = RequestMethod.GET)
    public String showMovieAdderPage(Model model) {
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        model.addAttribute("movieCategories", movieCategories);
        model.addAttribute("movieCreationFormData", new MovieCreationFormData());
        return "movieadder.html";
    }
    
    @RequestMapping(value = "createMovie", method = RequestMethod.POST)
    public String createMovie(Model model,  
            @Valid @ModelAttribute("movieCreationFormData") MovieCreationFormData movieCreationFormData) {
        //if (!bindingResult.hasErrors()) {
            Movie movie = new Movie();
            Director director;
            if (directorService.findByName(movieCreationFormData.getDirector()).isEmpty()) {
                director = new Director();
                director.setName(movieCreationFormData.getDirector());
            } else {
                director = directorService.findByName(movieCreationFormData.getDirector()).get(0);
            }
            String title = movieCreationFormData.getTitle();
            int movieLength = Integer.parseInt(movieCreationFormData.getMovieLength());
            String description = movieCreationFormData.getDescription();
            double rating = Double.parseDouble(movieCreationFormData.getRating());
            List<MovieCategory> categories = new ArrayList<>();
            MovieCategory movieCategory1 = movieCategoryService.
                    findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory1())).get(0);
            Optional<MovieCategory> movieCategory2 = Optional.of(movieCategoryService.
                    findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory2())).get(0));
            categories.add(movieCategory1);
            if (movieCategory2.isPresent()) {
                categories.add(movieCategory2.get());
            }
            Optional<MovieCategory> movieCategory3 = Optional.of(movieCategoryService.
                    findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory3())).get(0));
            if (movieCategory3.isPresent()) {
                categories.add(movieCategory3.get());
            }
            List<Actor> actorList = new ArrayList<>();
            String[] actorData = {movieCreationFormData.getActor1(), movieCreationFormData.getActor2(),
            movieCreationFormData.getActor3(), movieCreationFormData.getActor4(),
            movieCreationFormData.getActor5(), movieCreationFormData.getActor6()};
            for (int i = 0; i < actorData.length; i++) {
                if (!actorData[i].isEmpty()) {
                    if (actorService.isActorPresentInDatabase(actorData[i])) {
                        Actor actor = actorService.findByName(actorData[i]).get(0);
                        
                        actorList.add(actor);
                    } else {
                        Actor actor = new Actor();
                        actor.setName(actorData[i]);
                        actorList.add(actor);
                        actorService.saveActor(actor);
                    }
                }
            }
            movie.setActorList(actorList);
            movie.setDescription(description);
            movie.setDirector(director);
            movie.setMovieCategoryList(categories);
            movie.setMovieLength(movieLength);
            movie.setRating(rating);
            movie.setTitle(title);
            movieService.saveMovie(movie);
        //}
        return "movieadder.html";
    }
}
