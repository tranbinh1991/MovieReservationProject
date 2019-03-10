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
import org.springframework.security.crypto.bcrypt.BCrypt;

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

    public User saveUserWithPasswordEncryption(User user) {
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encryptedPassword);
        return repository.saveAndFlush(user);
    }
    
    public User saveUserWithoutPasswordEncryption(User user) {        
        return repository.saveAndFlush(user);
    }
    
    public List<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public List<User> findByCinemaAdmin(boolean cinemaAdmin) {
        return repository.findByCinemaAdmin(cinemaAdmin);
    }
    
    public User findById(Long id) {
        return repository.findById(id).get();
    }
}
