package com.deodentalink.model;

import java.time.LocalDateTime;

/**
 *
 * @author mir
 */
public record MessageRecord(Long id, String text, MESSAGE_TYPE messageType,
                            LocalDateTime dateTime, String phoneNumberFrom,
                            String phoneNumberTo, Long visitId) {

}
