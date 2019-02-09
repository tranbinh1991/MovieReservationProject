/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.daos;

import com.bh08.movieproject.models.Ticket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Zoli
 */
public class TicketDAO implements DAO<Ticket> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviePU");
    
    @Override
    public Ticket save(Ticket obj) {
        EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	tx.begin();
		
	em.persist(obj);
		
	tx.commit();
		
	em.close();
	return obj;
    }
    
}
