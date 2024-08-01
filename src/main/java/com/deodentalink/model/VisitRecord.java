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
public record VisitRecord(@NotBlank Long id, @NotBlank LocalDate visitDate, @NotBlank LocalTime visitTime,
                          @NotBlank VisitorRecord visitorRecord, @NotBlank SpecialistRecord specialistRecord,
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
