package com.example.ProjectOne.run;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("run")
public record Run(
        @Id
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
        if (title.isBlank()){
            throw new IllegalArgumentException("Title must not be blank");
        }
    }
}