package com.deodentalink.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public class Specialist {

  @NotBlank
  public Long id;

  @NotBlank
  public String name;

  @NotBlank
  public String surname;

  public String email;

  public String phoneNumber;

  public String title;

}
