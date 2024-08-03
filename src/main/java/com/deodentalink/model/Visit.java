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
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate visitDate;

    @NotNull
    private LocalTime visitTime;

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

    public LocalDate getVisitDate() {
        //return visitDate != null ? visitDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitDateString() {
        return visitDate != null ? visitDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
    }

    public LocalTime getVisitTime() {
        //return visitTime != null ? visitTime.format(DateTimeFormatter.ofPattern("HH:mm")) : null;
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeString() {
        return visitTime != null ? visitTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null;
        //return "14:00";
    }

	public LocalDateTime getMessageSendDateTime(){
		return messageSendDateTime;
	}

    public void setMessageSendDateTime(int hoursBefore){
		messageSendDateTime = LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore);
	}

    
    //Calculate messageSendDateTime
    //LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore));
}
