package com.deodentalink.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author mir
 */
public record SpecialistRecord(@NotBlank Long id, @NotBlank String name, @NotBlank String surname,
                              String email, String phoneNumber, String title) {

}
