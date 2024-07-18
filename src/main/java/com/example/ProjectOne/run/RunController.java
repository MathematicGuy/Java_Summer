package com.example.ProjectOne.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Controller: take in request then return a response.
@RestController
@RequestMapping("/api/runs") // map to /api/runs (like os.chdir in python)
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository; // create new instance of RunRepository
    }

    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()){
            throw new RunNotFoundException();
        }

        return run.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED) // use to debug. check
    @PostMapping("/create")
    void create(@Valid @RequestBody Run run){
        runRepository.create(run);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run, id);
    }

    // delete
    @DeleteMapping("/delete{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(id);
    }
}
