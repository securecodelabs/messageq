package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author mir
 */
@Entity
@Table(name = "VISITS")
public class Visit extends PanacheEntity{
    
    //public Long id;

    @Column(name = "VISIT_DATE", nullable = false)
    @Future
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate visitDate;

    @Column(name = "VISIT_TIME", nullable = false)
    @NotNull
    public LocalTime visitTime;

    @ManyToOne
    @JoinColumn(name = "VISITOR_ID", nullable = false)
    @NotNull
    public Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "SPECIALIST_ID", nullable = false)
    @NotNull
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

    public Visit(){

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
