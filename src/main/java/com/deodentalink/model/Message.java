package com.deodentalink.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author mir
 */
@Entity
@Table(name = "MESSAGES")
public class Message extends PanacheEntity{

  //public Long id;
  @Column(name = "TEXT", nullable = false)
  @NotBlank
  public String text;
  
  @Column(name = "MESSAGE_TYPE", nullable = false)
  @NotNull
  public MESSAGE_TYPE messageType;

  @Column(name = "DATE_TIME", nullable = false)
  @NotNull
  public LocalDateTime dateTime;
  
  @Column(name = "PHONE_NUMBER_FROM", nullable = false)
  @NotBlank
  public String phoneNumberFrom;

  @Column(name = "PHONE_NUMBER_TO", nullable = false)
  @NotBlank
  public String phoneNumberTo;
  
  /*Long visitId*/

  @ManyToOne
  @JoinColumn(name = "VISIT_ID", nullable = false)
  @NotNull
  public Visit visit;

  public Message() {

  }

  // Required arguments constructor
  public Message(String text, MESSAGE_TYPE messageType, LocalDateTime dateTime, String phoneNumberFrom, String phoneNumberTo){
    this.text = text;
    this.messageType = messageType;
    this.dateTime = dateTime;
    this.phoneNumberFrom = phoneNumberFrom;
    this.phoneNumberTo = phoneNumberTo;
  }

}
