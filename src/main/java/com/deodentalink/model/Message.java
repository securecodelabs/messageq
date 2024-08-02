package com.deodentalink.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public class Message {

  @NotBlank
  public Long id;
  
  @NotBlank
  public String text;
  
  @NotBlank
  public MESSAGE_TYPE messageType;

  @NotBlank
  public LocalDateTime dateTime;
  
  @NotBlank
  public String phoneNumberFrom;

  @NotBlank
  public String phoneNumberTo;
  
  /*Long visitId*/

}
