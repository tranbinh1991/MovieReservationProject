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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Zoli
 */
@Entity
@Getter
@Setter
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private BigDecimal price;
    //@Column(nullable = false)
    @JoinColumn(name = "screening_id", referencedColumnName = "ID")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Screening screening;
    
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Customer user;
    
    @OneToOne (cascade = {CascadeType.ALL})
    private Chair chair;
    
    private boolean student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public BigDecimal calculatePrice() {
        return null;
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
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bh08.movieproject.models.Ticket[ id=" + id + " ]";
    }
    
}
