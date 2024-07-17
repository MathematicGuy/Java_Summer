package com.example.ProjectOne.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();
    List<Run> findAll(){
        return runs;
    }

    // `Optional` represent optional values that can either be present or absent
    // providing a way to handle situations where a value might be null without using explicit null checks.
    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    void create(Run run){
        runs.add(run);
    }

    void update(Run run, Integer id){
        Optional<Run> existingRun = findById(id);
        if (existingRun.isEmpty()){
            throw new IllegalArgumentException("Run not found");
        }

        runs.remove(existingRun.get());
        runs.add(run);
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1, "First Run", LocalDateTime.of(2024, 1, 1, 10, 0), null, 13, Location.OUTDOOR));
        runs.add(new Run(2, "Second Run", LocalDateTime.of(2024, 1, 2, 11, 0), null, 15, Location.INDOOR));
        runs.add(new Run(3, "Third Run", LocalDateTime.of(2024, 1, 3, 12, 0), LocalDateTime.of(2024, 1, 3, 13, 0), 10, Location.OUTDOOR));
    }
}