/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.RoomRepository;
import com.bh08.movieproject.models.Room;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
public class RoomService {
    
    @Autowired
    private RoomRepository repository;

    public Room saveRoom(Room room) {
        return repository.saveAndFlush(room);
    }
    
    public List<Room> findByRoomNumber(int roomNumber) {
        return repository.findByRoomNumber(roomNumber);
    }
}
