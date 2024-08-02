package com.example.ProjectOne.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRunRepositoryTest {

    InMemoryRunRepository repository;
    @BeforeEach
    void SetUp(){
        repository = new InMemoryRunRepository();
        repository.create(
                new Run(1,
                "Morning Run",
                LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR,
                null));

        repository.create(
                new Run(2,
                "Evening Run",
                LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR,
                null));
    }

    @Test
    void shouldFindAllRuns(){
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(), "This return all runs");
    }

    @Test
    void shouldFindRunById() {
        Run run = repository.findById(1);
    }

    @Test
    void shouldCreateRun() {
        Run run = new Run(3,
                "Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR,
                null);
        repository.create(run);
        assertEquals(3, repository.findAll().size(), "This should create a new run");
    }

    @Test
    void shouldUpdateRun() {
        Run run = new Run(3,
                "Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR,
                null);
        repository.update(run, 1);
        assertEquals("Morning Run", repository.findById(1).title(), "This should update the run");
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        assertEquals(1, repository.findAll().size(), "This should delete the run");
    }

    @Test
    void shouldCountRuns(){
        assertEquals(2, repository.count(), "This should count the number of runs");
    }
}