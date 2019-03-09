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
public class Screening implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    //@Column(nullable = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Movie movie;
    
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Room room;
    
    @Column(nullable = false)
    private LocalDateTime time;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "screening", fetch = FetchType.EAGER)
    private List<Ticket> ticketList;
    
    private boolean occupied;
    
    @Enumerated(EnumType.STRING)
    private Language language;

    @Override
    public String toString() {
        return "Screening{" + "movie=" + movie + ", room=" + room + ", time=" + time + ", language=" + language + '}';
    }
    
    
    
    
}
