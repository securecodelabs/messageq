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
@Table(name = "SPECIALISTS")
public class Specialist extends PanacheEntity {

  //public Long id;

  @Column(name = "NAME", nullable = false)
  @NotBlank
  public String name;

  @Column(name = "SURNAME", nullable = false)
  @NotBlank
  public String surname;

  @Column(name = "EMAIL")
  public String email;
  
  @Column(name = "PHONE_NUMBER")
  public String phoneNumber;

  @Column(name = "TITLE")
  public String title;

  @OneToMany(mappedBy = "specialist")
  public List<Visit> visits;

  // Required arguments constructor
  public Specialist(String name, String surname){
    this.name = name;
    this.surname = surname;
  }

  public Specialist() {

  }
}
