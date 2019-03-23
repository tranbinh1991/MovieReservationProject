package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.DirectorRepository;
import com.bh08.movieproject.models.Director;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public Director saveDirector(Director director) {
        return directorRepository.saveAndFlush(director);
    }
    
    public List<Director> findByName(String name) {
        return directorRepository.findByName(name);
    }

}
