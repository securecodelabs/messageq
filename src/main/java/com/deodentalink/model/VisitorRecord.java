package com.deodentalink.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public record VisitorRecord(@NotBlank Long id, @NotBlank String name, @NotBlank String surname,
                            @NotBlank String phoneNumber, String email) {

}
