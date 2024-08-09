package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate visitDate;

    @Column(name = "VISIT_TIME", nullable = false)
    @NotNull
    public LocalTime visitTime;

    /*@ManyToOne
    @JoinColumn(name = "VISITOR_ID", nullable = false)*/
    @Embedded
    @NotNull
    public Visitor visitor;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonBackReference
    @JoinColumn(name = "SPECIALIST_ID", nullable = false)
    @NotNull
    public Specialist specialist;

    @OneToMany(mappedBy = "visitId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Message> messages = new ArrayList<>();

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "MESSAGE_SEND_DATE")
    public LocalDate messageSendDate;

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

    @JsonIgnore
    public String getVisitDateString() {
        return visitDate != null ? visitDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    public LocalTime getVisitTime() {
        //return visitTime != null ? visitTime.format(DateTimeFormatter.ofPattern("HH:mm")) : null;
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    @JsonIgnore
    public String getVisitTimeString() {
        return visitTime != null ? visitTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null;
    }

	public LocalDate getMessageSendDate(){
		return messageSendDate;
	}

    public void setMessageSendDate(LocalDate messageSenDate){
        this.messageSendDate = messageSenDate;
	}

    public LocalDate calculateMessageSendDate(int daysBefore){
        return visitDate.minusDays(daysBefore);
        //messageSendDateTime = LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore);
    }

    public static List<Visit> findAllOrderByDateAndTime() {
        return Visit.listAll(Sort.descending("visitDate", "visitTime"));
    }

    /*public static Visit findBySpecialistId(long specialistId) {
        return Visit.find("specialist.id", specialistId).firstResult();
    }*/

    public static List<Visit> findAllBySpecialistId(long specialistId) {
        return Visit.find("specialist.id", Sort.descending("visitDate", "visitTime"), specialistId).list();
    }

    public static List<Visit> findByDate(LocalDate date) {
        return Visit.find("visitDate", Sort.descending("visitTime"), date).list();
    }
    
    //Calculate messageSendDateTime
    //LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore));

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", visitTime=" + visitTime +
                ", specialist=" + specialist.name + " " + specialist.surname +
                ", visitor=" + visitor.name  + " " + visitor.surname +
                ", messages=" + (messages != null ? messages.stream().map(Message::toString).collect(Collectors.joining(", ")) : "null") +
                ", messageSendDate=" + messageSendDate +
                '}';
    }
}
