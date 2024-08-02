package com.deodentalink.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author mir
 */
public class Message {

  public Long id;
  
  @NotBlank
  public String text;
  
  @NotNull
  public MESSAGE_TYPE messageType;

  @NotNull
  public LocalDateTime dateTime;
  
  @NotBlank
  public String phoneNumberFrom;

  @NotBlank
  public String phoneNumberTo;
  
  /*Long visitId*/

  // Required arguments constructor
  public Message(String text, MESSAGE_TYPE messageType, LocalDateTime dateTime, String phoneNumberFrom, String phoneNumberTo){
    this.text = text;
    this.messageType = messageType;
    this.dateTime = dateTime;
    this.phoneNumberFrom = phoneNumberFrom;
    this.phoneNumberTo = phoneNumberTo;
  }

}
