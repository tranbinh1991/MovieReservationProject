/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.User;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zoli
 */
@RestController
@RequestMapping(value = "movie")
public class RoomController {

//    @Autowired
//    private RoomService roomService;
//
//    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
//    public @ResponseBody
//    Room createRoom(@RequestBody Room room) {
//        return roomService.saveRoom(room);
//    }
//
    @Autowired
    private UserService userService;

    @RequestMapping(value = "createUser", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);

    }
}
