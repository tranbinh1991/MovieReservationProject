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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Zoli
 */
@Entity
@Getter
@Setter
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
    @JoinColumn(name = "director_id", referencedColumnName = "ID")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Director director;
    
    @ManyToMany(mappedBy = "movieList")
    private List<Actor> actorList;
    
    @ManyToMany(mappedBy = "movieList")
    private List<MovieCategory> movieCategoryList;
    
    @Column(unique = true, nullable = false)
    private String description;
    
    private double rating;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Screening> screenings;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bh08.movieproject.models.Movie[ id=" + id + " ]";
    }
    
}
