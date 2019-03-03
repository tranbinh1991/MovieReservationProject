package com.bh08.movieproject.MovieSpringProject;

import com.bh08.movieproject.models.Category;
import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.services.MovieCategoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bh08.movieproject.daos")
public class MovieSpringProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(new Class<?>[]{MovieSpringProjectApplication.class, MovieSpringProjectConfiguration.class}, args);
        MovieCategoryService mcs = new MovieCategoryService();
        MovieCategory mc = new MovieCategory();
        mc.setCategory(Category.COMEDY);
        mcs.saveMovieCategory(mc);
        System.out.println("Kész");
//        char a = 'A';
//        System.out.println(a-64);
    }

}
