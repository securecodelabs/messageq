package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author mir
 */
public class Visit {
    
    public Long id;

    @Future
    public LocalDate visitDate;

    @NotNull
    public LocalTime visitTime;

    public Visitor visitor;

    public Specialist specialist;

    public List<Message> messages;

    private LocalDateTime messageSendDateTime;

    // Required arguments constructor
    public Visit(LocalDate visitDate, LocalTime visitTime, Visitor visitor, Specialist specialist){
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visitor = visitor;
        this.specialist = specialist;
    }

    public void setMessageSendDateTime(int hoursBefore){
		messageSendDateTime = LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore);
	}

	public LocalDateTime getMessageSendDateTime(){
		return messageSendDateTime;
	}

    public String getVisitTime() {
        return visitTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    //Calculate messageSendDateTime
    //LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore));
}
