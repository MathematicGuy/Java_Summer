package com.example.ProjectOne.run;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        // record class is the same as a class of attribute
        // with getters, setters, equals, hashcode, and toString
        @Id
        Integer id,
        @NotEmpty
        String title,

        LocalDateTime startedOn,
        LocalDateTime completedOn,

        @PositiveOrZero
        Integer kilometers,
        Location location,

//        @Version
        Integer version
) {
    public Run {
        if (completedOn != null && !completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed date must be after started date");
        }
        if (title.isBlank()){
            throw new IllegalArgumentException("Title must not be blank");
        }
    }
}



