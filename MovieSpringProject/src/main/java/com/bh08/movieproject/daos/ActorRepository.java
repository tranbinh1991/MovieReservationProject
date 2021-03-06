package com.bh08.movieproject.daos;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bh08.movieproject.models.Actor;



@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByName(String name);
}
