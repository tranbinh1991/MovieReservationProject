/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@ToString
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private BigDecimal price;
    //@Column(nullable = false)
    @JoinColumn(name = "screening_id", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Screening screening;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private User user;
    
    @OneToOne (cascade = {CascadeType.ALL})
    private Chair chair;
    
    private boolean student;

    
    
}
