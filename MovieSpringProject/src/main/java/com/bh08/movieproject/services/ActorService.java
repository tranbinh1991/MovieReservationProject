package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.ActorRepository;
import com.bh08.movieproject.models.Actor;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
        return actorRepository.saveAndFlush(actor);
    }

    public List<Actor> findByName(String name) {
        return actorRepository.findByName(name);
    }
    
    public boolean isActorPresentInDatabase(String name) {        
        return !findByName(name).isEmpty();
    }

}
