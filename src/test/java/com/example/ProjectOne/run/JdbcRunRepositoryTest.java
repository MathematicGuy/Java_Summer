package com.example.ProjectOne.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;
import java.time.LocalDateTime;
import java.util.List;

@JdbcTest
@Import(JdbcRunRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcRunRepositoryTest {

    @Autowired
    JdbcRunRepository repository;

    @BeforeEach
    void SetUp(){
        repository.create(
                new Run(11,
                "Morning Run",
                LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR));

        repository.create(
                new Run(12,
                "Evening Run",
                LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(30),
                10,
                Location.OUTDOOR));
    }


    @Test
    void shouldFindAllRuns(){
        List<Run> runs = repository.findAll();
        Assert.notEmpty(runs, "No runs found");
//        System.out.println(runs);
    }

//    public Optional<Run> findById(Integer id) {
//        return jdbcClient.sql("SELECT * FROM Run WHERE id = :id")
//                .param("id", id)
//                .query(Run.class)
//                .optional();
//    }

//    public void create(Run run) {
//        var create = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, kilometers, location) VALUES (?, ?, ?, ?, ?, ?)")
//                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.kilometers(), run.location().toString()))
//                .update();
//
//        Assert.state(create == 1, "Failed to insert run: " + run.title());
//    }

//    public void update(Run run, Integer id) {
//        var updated = jdbcClient.sql("UPDATE Run SET title = ?, started_on = ?, completed_on = ?, kilometers = ?, location = ? WHERE id = ?")
//                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.kilometers(), run.location().toString(), id))
//                .update();
//
//        Assert.state(updated == 1, "Failed to update run: " + run.title());
//    }

//    public void delete(Integer id) {
//        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
//                .param("id", id)
//                .update();
//
//        Assert.state(updated == 1, "Failed to delete run with id: " + id);
//    }

//    public int count() {
//        return jdbcClient.sql("SELECT COUNT(*) FROM Run").query().listOfRows().size();
//    }


//    public void saveAll(List<Run> runs) {
//        runs.forEach(this::create);
//    }

//    public List<Run> findAllByLocation(String location) {
//        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
//                .param("location", location)
//                .query(Run.class)
//                .list();
//    }
}
