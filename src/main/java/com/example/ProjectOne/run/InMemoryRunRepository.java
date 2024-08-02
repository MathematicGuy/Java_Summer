package com.example.ProjectOne.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRunRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);
    private final List<Run> runs = new ArrayList<>();

    public List<Run> findAll() {
        return runs;
    }

    public Run findById(Integer id){
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void create(Run run){
        Run newRun = new Run(run.id(),
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.kilometers(),
                run.location(), null);
        runs.add(newRun);
    }

    public void update(Run run, Integer id){
        Run updatedRun = new Run(id,
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.kilometers(),
                run.location(), null);
        runs.set(id, updatedRun);
    }

    public void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    public int count(){
        return runs.size();
    }
}
