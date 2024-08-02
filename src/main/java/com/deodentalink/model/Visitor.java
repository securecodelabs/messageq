package com.deodentalink.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public class Visitor {

  public Long id;

  @NotBlank
  public String name;

  @NotBlank
  public String surname;

  @NotBlank
  public String phoneNumber;
  
  public String email;

  // Required arguments constructor
  public Visitor(String name, String surname, String phoneNumber){
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
  }

}
