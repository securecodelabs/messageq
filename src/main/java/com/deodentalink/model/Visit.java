package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public class Visit {
    
    @NotBlank
    public Long id;

    @NotBlank
    public LocalDate visitDate;

    @NotBlank
    public LocalTime visitTime;

    @NotBlank
    public Visitor visitor;

    @NotBlank
    public Specialist specialist;

    public List<Message> messages;

    private LocalDateTime messageSendDateTime;

    public void setMessageSendDateTime(int hoursBefore){
		messageSendDateTime = LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore);
	}

	public LocalDateTime getMessageSendDateTime(){
		return messageSendDateTime;
	}
    
    //Calculate messageSendDateTime
    //LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore));
}
