package dev.mollyzhang.activeto.business.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    int activityId, typeId, facilityId;

    @BeforeEach
    void setUp() {
        activityId = 169718;
        typeId = 2141;
        facilityId = 2153;
    }


    @Test
    void searchActivities() throws Exception {
        mockMvc.perform(get("/toronto/activities?q=swimming")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));

    }

    @Test
    void searchActivitiesNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/activities?q=asgasdfhaerherber")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(0)));
    }

    @Test
    void getActivityById() throws Exception {
        mockMvc.perform(get("/toronto/activities/{activityId}", activityId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(activityId)));
    }

    @Test
    void getActivityByIdNotFound() throws Exception {

        mockMvc.perform(get("/toronto/activities/{activityId}", 99999999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void searchActivitiesByType() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}/activities", typeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements", greaterThan(0)));
    }

    @Test
    void searchActivitiesByTypeNotFound() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}/activities", 99999999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchActivitiesByTypeAndFacility() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types/{typeId}/activities", facilityId, typeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements", greaterThan(0)));
    }

    @Test
    void searchActivitiesByTypeAndFacilityNotFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types/{typeId}/activities", facilityId, 9999999)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}