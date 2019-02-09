/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.daos;

/**
 *
 * @author Zoli
 */
public interface DAO <T> {
    T save(T obj);    
}
