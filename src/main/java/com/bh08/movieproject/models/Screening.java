/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Screening implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    //@Column(nullable = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "ID")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Movie movie;
    
    @JoinColumn(name = "room_id", referencedColumnName = "ID")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Room room;
    
    @Column(nullable = false)
    private LocalDateTime time;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "screening", fetch = FetchType.EAGER)
    private List<Ticket> ticketList;
    
    private boolean occupied;
    
    @Enumerated(EnumType.STRING)
    private Language language;
    
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
        if (!(object instanceof Screening)) {
            return false;
        }
        Screening other = (Screening) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bh08.movieproject.models.Screening[ id=" + id + " ]";
    }
    
}
