/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.UserRepository;
import com.bh08.movieproject.models.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
@Getter
@Setter
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.saveAndFlush(user);
    }
    
    public List<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public List<User> findByCinemaAdmin(boolean cinemaAdmin) {
        return repository.findByCinemaAdmin(cinemaAdmin);
    }
}
