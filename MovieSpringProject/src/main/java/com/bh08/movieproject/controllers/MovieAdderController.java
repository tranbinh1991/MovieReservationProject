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

    @RequestMapping(value = "movieadder", method = RequestMethod.POST)
    public String createMovie(Model model,
            @Valid @ModelAttribute("movieCreationFormData") MovieCreationFormData movieCreationFormData, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Movie movie = new Movie();
            Director director;
            if (directorService.findByName(movieCreationFormData.getDirector()).isEmpty()) {
                director = new Director();
                director.setName(movieCreationFormData.getDirector());
            } else {
                director = directorService.findByName(movieCreationFormData.getDirector()).get(0);
            }

            String title = movieCreationFormData.getTitle();
            movie.setTitle(title);

            int movieLength = Integer.parseInt(movieCreationFormData.getMovieLength());
            movie.setMovieLength(movieLength);

            String description = movieCreationFormData.getDescription();
            movie.setDescription(description);

            double rating = Double.parseDouble(movieCreationFormData.getRating());
            movie.setRating(rating);

            List<MovieCategory> categories = processCategories(movieCreationFormData, movie);

            List<Actor> actorList = processActors(movieCreationFormData, movie);
            
            movie.setActorList(actorList);
            movie.setDirector(director);
            movie.setMovieCategoryList(categories);
            movieService.saveMovie(movie);
            model.addAttribute("successMessage", "Sikeres mentés!");
        } else {
//            if ("".equals(movieCreationFormData.getTitle())) {
//                bindingResult.rejectValue("title", "", "Ez egy kötelező mező!");
//            }} else {
//            if ("".equals(movieCreationFormData.getTitle())) {
//                
//            if ("".equals(movieCreationFormData.getRating())) {
//                bindingResult.rejectValue("rating", "", "Ez egy kötelező mező!");
//            }
//            if ("".equals(movieCreationFormData.getDescription())) {
//                bindingResult.rejectValue("description", "", "Ez egy kötelező mező!");
//            }
        }

        return showMovieAdderPage(model); //milyen model kell ide?
    }

    private List<Actor> processActors(MovieCreationFormData movieCreationFormData, Movie movie) {
        List<Actor> actorList = new ArrayList<>();
        String[] actorData = {movieCreationFormData.getActor1(), movieCreationFormData.getActor2(),
            movieCreationFormData.getActor3(), movieCreationFormData.getActor4(),
            movieCreationFormData.getActor5(), movieCreationFormData.getActor6()};
        for (int i = 0; i < actorData.length; i++) {
            if (!actorData[i].isEmpty()) {
                if (actorService.isActorPresentInDatabase(actorData[i])) {
                    Actor actor = actorService.findByName(actorData[i]).get(0);
                    List<Movie> movieList = actor.getMovieList();
                    movieList.add(movie);
                    actor.setMovieList(movieList);
                    actorList.add(actor);
                } else {
                    Actor actor = new Actor();
                    actor.setName(actorData[i]);
                    actorList.add(actor);
                    List<Movie> movieList = new ArrayList<>();
                    movieList.add(movie);
                    actor.setMovieList(movieList);
                    actorService.saveActor(actor);
                }
            }
        }
        return actorList;
    }

    private List<MovieCategory> processCategories(MovieCreationFormData movieCreationFormData, Movie movie) {
        
        List<MovieCategory> categories = new ArrayList<>();
        MovieCategory movieCategory1 = movieCategoryService.
                findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory1())).get(0);
        List<Movie> movieListOfTheFirstCategory = movieCategory1.getMovieList();
        categories.add(movieCategory1);
        movieListOfTheFirstCategory.add(movie);
        
        if (!("".equals(movieCreationFormData.getMovieCategory2()))) {
            MovieCategory movieCategory2 = movieCategoryService.
                    findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory2())).get(0);
            List<Movie> movieList = movieCategory2.getMovieList();
            movieList.add(movie);
            movieCategory2.setMovieList(movieList);
            categories.add(movieCategory2);
        }
        
        if (!("".equals(movieCreationFormData.getMovieCategory3()))) {
            MovieCategory movieCategory3 = movieCategoryService.
                    findByCategory(Category.valueOf(movieCreationFormData.getMovieCategory2())).get(0);
            List<Movie> movieList = movieCategory3.getMovieList();
            movieList.add(movie);
            movieCategory3.setMovieList(movieList);
            categories.add(movieCategory3);
        }
        
        return categories;
    }
}
