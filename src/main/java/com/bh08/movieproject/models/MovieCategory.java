/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Zoli
 */
@Entity
public class MovieCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id    
    @GeneratedValue(generator = "mySequenceGenerator")
    @SequenceGenerator(name = "mySequenceGenerator", sequenceName="seq01", initialValue = 1)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @JoinTable(name = "movieCategory_movie",
        joinColumns = @JoinColumn(name = "movieCategory_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    

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
        if (!(object instanceof MovieCategory)) {
            return false;
        }
        MovieCategory other = (MovieCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bh08.movieproject.models.MovieCategory[ id=" + id + " ]";
    }
    
}
