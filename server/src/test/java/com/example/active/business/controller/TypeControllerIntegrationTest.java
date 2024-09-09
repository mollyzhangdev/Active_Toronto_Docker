package com.example.active.business.controller;

import com.example.active.business.domain.paramobject.TypeParams;
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
class TypeControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    int typeId, categoryId, facilityId;

    @BeforeEach
    void setUp() {
        categoryId = 214;
        typeId = 2141;
        facilityId = 2153;
    }

    @Test
    void searchTypes() throws Exception {
        mockMvc.perform(get("/toronto/types?q=swim")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void searchTypesNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/types?q=asdadgasdfgasdgasdg")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(0)));
    }

    @Test
    void getTypeById() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}", typeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(typeId)));
    }

    @Test
    void getTypeByIdNoFound() throws Exception {
        mockMvc.perform(get("/toronto/types/{typeId}", 233523)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByCategory() throws Exception {
        mockMvc.perform(get("/toronto/categories/{categoryId}/types", categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void searchTypesByCategoryNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/categories/{categoryId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByFacility() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types", facilityId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void searchTypesByFacilityNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByFacilityAndCategory() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories/{categoryId}/types", facilityId, categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void searchTypesByFacilityAndCategoryNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories/{categoryId}/types", 12345, 23456)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}