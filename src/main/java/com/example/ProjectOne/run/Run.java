package com.example.ProjectOne.run;
import java.time.LocalDateTime;

public record Run(
        // record class is the same as a class of attribute
        // with getters, setters, equals, hashcode, and toString
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer kilometers,
        Location location
) {}



