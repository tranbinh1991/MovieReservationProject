/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject;

import com.bh08.movieproject.models.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zoli
 */
public class Main {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviePU");
        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer();
        customer.setEmail("a@gmail.com");
        customer.setPassword("password");
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
