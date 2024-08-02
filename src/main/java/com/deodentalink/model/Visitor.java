package com.deodentalink.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public class Visitor {

  @NotBlank
  public Long id;

  @NotBlank
  public String name;

  @NotBlank
  public String surname;

  @NotBlank
  public String phoneNumber;
  
  public String email;
  
}
