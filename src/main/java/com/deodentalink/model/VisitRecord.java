package com.deodentalink.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author mir
 */
public record VisitRecord(Long id, LocalDate visitDate, LocalTime visitTime,
                          VisitorRecord visitorRecord, SpecialistRecord specialistRecord,
                          List<MessageRecord> messageRecords) {

}
