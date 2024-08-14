 package com.example.ProjectOne.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

 // Controller: take in request then return a response.
@RestController
@RequestMapping("/api/runs") // map to /api/runs (like os.chdir in python)
@CrossOrigin(origins = "http://127.0.0.1:5500")
//@CrossOrigin(origins = "*")
public class RunController {

    @Autowired
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    // Test Hellow World
    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
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
    @PostMapping("/create")
    public ResponseEntity<Run> createRun(@RequestBody Run run) {
        if (run.id() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be provided for creation.");
        }
        Run savedRun = runRepository.save(run);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRun);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody Run run) {
        if (run.id() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Run ID must be provided.");
        }

        if (runRepository.existsById(run.id())) {
            runRepository.save(run);
            // Log success
            System.out.println("Update Successful: Run ID " + run.id());
            return ResponseEntity.noContent().build();
        } else {
            // Log failure and return an appropriate response
            System.out.println("Update Failed: Run ID " + run.id() + " does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Run with ID " + run.id() + " does not exist.");
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
