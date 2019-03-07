package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.ChairRepository;
import com.bh08.movieproject.daos.UserRepository;
import com.bh08.movieproject.models.Chair;
import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.User;

@Service
public class ChairService {

    @Autowired
    private ChairRepository repository;

    public Chair saveChair(Chair chair) {
        return repository.saveAndFlush(chair);
    }

    public void saveChairsToRoom(Room room) {
        for (int i = 1; i <= room.getColumnCount(); i++) {
            for (int j = 1; j <= room.getRowCount(); j++) {
                Chair chair = new Chair();
                chair.setRoom(room);
                chair.setColumnOfChair(i);
                chair.setRowOfChair((char)(64+j));
                saveChair(chair);
            }
        }
    }

}
