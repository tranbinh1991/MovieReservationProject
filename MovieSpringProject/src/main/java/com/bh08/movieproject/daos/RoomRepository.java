/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.daos;

import com.bh08.movieproject.models.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Zoli
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long>  {
    
    List<Room> findByRoomNumber(int roomNumber);
}
