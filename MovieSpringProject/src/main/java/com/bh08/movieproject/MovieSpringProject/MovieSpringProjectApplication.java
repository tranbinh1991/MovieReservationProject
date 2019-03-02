package com.bh08.movieproject.MovieSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bh08.movieproject.daos")
public class MovieSpringProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(new Class<?>[]{MovieSpringProjectApplication.class, MovieSpringProjectConfiguration.class}, args);
        System.out.println("Kész");
//        char a = 'A';
//        System.out.println(a-64);
    }

}
