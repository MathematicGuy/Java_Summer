package com.example.ProjectOne.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record Run(
        // record class is the same as a class of attribute
        // with getters, setters, equals, hashcode, and toString
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @PositiveOrZero
        Integer kilometers,
        Location location
) {
    public Run {
        if (completedOn != null && !completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed date must be after started date");
        }
    }
}



