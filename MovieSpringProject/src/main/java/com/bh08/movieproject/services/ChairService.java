package com.bh08.movieproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh08.movieproject.daos.ChairRepository;
import com.bh08.movieproject.daos.UserRepository;
import com.bh08.movieproject.models.Chair;
import com.bh08.movieproject.models.User;

@Service
public class ChairService {
	
	@Autowired
	private ChairRepository repository;
	

	
	public Chair saveChair(Chair chair) {
		return repository.saveAndFlush(chair);
		
	}

}
