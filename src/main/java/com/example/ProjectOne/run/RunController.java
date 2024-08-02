 package com.example.ProjectOne.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Controller: take in request then return a response.
@RestController
@RequestMapping("/api/runs") // map to /api/runs (like os.chdir in python)
public class RunController {

    @Autowired
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
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
    public ResponseEntity<Run> createRun(@RequestBody Run run){
        runRepository.save(run);
        return ResponseEntity.status(HttpStatus.CREATED).body(run);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update")
    void update(@Valid @RequestBody Run run)
    {
        if (runRepository.existsById(run.id())){
            runRepository.save(run);
            System.out.println("Update Successfully");
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Object is not Exist. You Fool");
        }
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete{id}")
    void delete(@PathVariable Integer id){
       if (runRepository.existsById(id)) {
           runRepository.delete(runRepository.findById(id).get());
       }
       else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Object is not Exist. You Fool");
       }
   }

    @GetMapping("/count") // count number of runs.
    //! If use PutMapping this will return a empty string ""
    public long count(){
        return runRepository.count();
    }

    @GetMapping("location/{location}")
    List<Run> findAllByLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }
}
;