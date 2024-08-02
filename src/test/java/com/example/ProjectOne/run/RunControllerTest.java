package com.example.ProjectOne.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RunRepository repository;

    private final List<Run> runs = new ArrayList<>();

    @BeforeEach
    void SetUp(){
        runs.add(new Run(1,
                "Morning Run",
                null,
                null,
                10,
                Location.OUTDOOR,
                null));

        runs.add(new Run(2,
                "Evening Run",
                null,
                null,
                10,
                Location.INDOOR,
                null));
    }

//    @Test
//    void shouldFindAllRuns() throws Exception {
//        when(repository.findAll()).thenReturn(runs);
//        mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", is(runs.size())));
//    }

}