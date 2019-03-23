/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Zoli
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@ToString
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private int movieLength;
    
    //@Column(nullable = false)
    @JoinColumn(name = "director_id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Director director;
    
    @ManyToMany(mappedBy = "movieList")
    private List<Actor> actorList;
    
    @ManyToMany(mappedBy = "movieList")
    private List<MovieCategory> movieCategoryList;
    
    @Column(unique = true, nullable = false)
    private String description;
    
    private double rating;
    

    @Column(nullable = false)
    private String imageLink;
    
    private String youTubeLink;

    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Screening> screenings;

    @Override
    public String toString() {
        return "Jegyfoglalás a(z) " + title + " című filmre.";
    }
    
    
    
}
