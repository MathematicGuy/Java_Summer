package com.example.ProjectOne.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    // If table is empty then load data from runs.json
    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) { // change 0 to 1 to automatic load data from runs.json
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Loaded {} runs from JSON file and saving it to the database", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                log.error("Failed to load runs from JSON file", e);
            }
        } else {
            log.info("Not loading Runs from JSON file as there are already {} runs in the database", runRepository.count());
        }
    }
}
