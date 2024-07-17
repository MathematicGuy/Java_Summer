package com.example.ProjectOne.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

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


    @PostConstruct
    private void init(){
        runs.add(new Run(1, "First Run", null, null, 13, Location.OUTDOOR));
        runs.add(new Run(2, "Second Run", null, null, 15, Location.INDOOR));
    }
}