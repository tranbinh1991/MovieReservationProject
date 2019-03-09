package com.bh08.movieproject.daos;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bh08.movieproject.models.Chair;
import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.User;


@Repository
public interface ChairRepository extends JpaRepository<Chair, Long> {

    public Chair findByRowOfChairAndColumnOfChairAndRoom(char rowOfChair, int columnOfChair, Room room);
    public Chair findFirstById(Long ChairId);
	
}
