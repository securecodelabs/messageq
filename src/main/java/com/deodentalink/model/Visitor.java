package com.deodentalink.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
@Entity
@Table(name = "VISITORS")
public class Visitor extends PanacheEntity{

  //public Long id;

  @Column(name = "NAME", nullable = false)
  @NotBlank
  public String name;

  @Column(name = "SURNAME", nullable = false)
  @NotBlank
  public String surname;

  @Column(name = "PHONE_NUMBER", nullable = false)
  @NotBlank
  public String phoneNumber;
  
  @Column(name = "EMAIL")
  public String email;

  @OneToMany(mappedBy = "visitor")
  public List<Visit> visits;

  // Required arguments constructor
  public Visitor(String name, String surname, String phoneNumber){
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
  }

  public Visitor() {

  }
}
