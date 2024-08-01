package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author mir
 */
public record VisitRecord(Long id, LocalDate visitDate, LocalTime visitTime,
                          VisitorRecord visitorRecord, SpecialistRecord specialistRecord,
                          List<MessageRecord> messageRecords,
                          LocalDateTime messageSendDateTime) {

        public VisitRecord(Long id, LocalDate visitDate, LocalTime visitTime,
                       VisitorRecord visitorRecord, SpecialistRecord specialistRecord,
                       List<MessageRecord> messageRecords, int hoursBefore) {
        this(id, visitDate, visitTime, visitorRecord, specialistRecord, messageRecords,
             // Calculate messageSendDateTime
             LocalDateTime.of(visitDate, visitTime).minusHours(hoursBefore));
    }

}
