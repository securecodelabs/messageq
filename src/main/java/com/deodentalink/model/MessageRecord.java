package com.deodentalink.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public record MessageRecord(@NotBlank Long id, @NotBlank String text, @NotBlank MESSAGE_TYPE messageType,
                            @NotBlank LocalDateTime dateTime, @NotBlank String phoneNumberFrom,
                            @NotBlank String phoneNumberTo /*, Long visitId*/) {

}
