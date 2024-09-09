package com.example.active.business.controller;

import com.example.active.business.domain.paramobject.FacilityParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FacilityControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    int facilityId, typeId;

    @BeforeEach
    void setUp() {
        facilityId = 2153;
        typeId = 2141;
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/toronto/facilities?q=community")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void findAllNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities?q=qwerjyfkyfkhkhfkhk")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(0)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}", facilityId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(facilityId)));
    }

    @Test
    void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findByType() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}/facilities", typeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void findByTypeNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}