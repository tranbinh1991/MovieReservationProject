/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Category;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.services.MovieCategoryService;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.services.SessionService;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.LoginFormData;
import com.bh08.movieproject.viewmodels.MovieCreationFormData;
import com.bh08.movieproject.viewmodels.SearchByMovieCategoryFormData;
import com.bh08.movieproject.viewmodels.SearchFormData;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Binh
 */
@Controller
public class MainPageController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    @GetMapping("/mainpage")
    public String showMainPage(@ModelAttribute("searchFormData") SearchFormData searchFormData,
            @ModelAttribute("searchByMovieCategoryFormData") SearchByMovieCategoryFormData searchByMovieCategoryFormData, Model model) {
        sessionService.getSeatReservationDtos().clear();
        
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        model.addAttribute("movieCategories", movieCategories);

        model.addAttribute("loginFormData", new LoginFormData());
        model.addAttribute("userId", sessionService.getUserId());
        if (sessionService.getUserId() != null) {
            boolean currentUserAdminRight = userService.findById(sessionService.getUserId()).isCinemaAdmin();
            model.addAttribute("currentUserAdminRight", currentUserAdminRight);
            model.addAttribute("currentUserEmail", userService.findById(sessionService.getUserId()).getEmail());
        }

        if (searchFormData.getSearchValue() == null
                && (searchByMovieCategoryFormData.getMovCategory() == null
                || "".equals(searchByMovieCategoryFormData.getMovCategory()))) {
            List<Movie> movieList = movieService.findMoviesWithFutureScreenings();
            model.addAttribute("movielist", movieList);
        }
        if (searchFormData.getSearchValue() != null) {
            List<Movie> filteredMovieList
                    = movieService.findByTitleIgnoreCaseContains(searchFormData.getSearchValue());
            model.addAttribute("movielist", filteredMovieList);
        }
        if (searchByMovieCategoryFormData.getMovCategory() != null && !("".equals(searchByMovieCategoryFormData.getMovCategory()))) {
            MovieCategory movieCategory = movieCategoryService.
                    findByCategory(Category.valueOf(searchByMovieCategoryFormData.getMovCategory())).get(0);
            List<Movie> movieList = movieService.findMoviesWithFutureScreenings();
            List<Movie> filteredMovieList = new ArrayList<>();
            for (Movie movie : movieList) {
                if (movie.getMovieCategoryList().contains(movieCategory)) {
                    filteredMovieList.add(movie);
                }
            }

            model.addAttribute("movielist", filteredMovieList);

        }
        return "mainpage.html";
    }
}
