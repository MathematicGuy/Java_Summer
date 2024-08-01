package com.example.ProjectOne.run;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

@EnableJdbcRepositories
public interface RunRepository extends ListCrudRepository<Run, Integer> {
    @Query("SELECT * FROM Run WHERE location = :location")
    List<Run> findAllByLocation(String location);


}
