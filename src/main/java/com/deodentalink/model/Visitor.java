package com.deodentalink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
//@Entity
@Embeddable
//@Table(name = "VISITORS")
//public class Visitor extends PanacheEntity{
public class Visitor{

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

  /*@OneToMany(mappedBy = "visitor")
  public List<Visit> visits;*/

  // Required arguments constructor
  public Visitor(String name, String surname, String phoneNumber){
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
  }

  public Visitor() {

  }
}
