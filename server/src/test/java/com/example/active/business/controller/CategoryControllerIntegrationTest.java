package com.example.active.business.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    int categoryId, facilityId;

    @BeforeEach
    void setUp() {
        categoryId = 214;
        facilityId = 2153;
    }

    @Test
    void searchAllCategories() throws Exception {
        mockMvc.perform(get("/toronto/categories?q=swimming")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(1)));
    }

    @Test
    void searchAllCategoriesNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/categories?q=category")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(0)));
    }

    @Test
    void getCategoryById() throws Exception {
        mockMvc.perform(get("/toronto/categories/{categoryId}", categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(categoryId)));
    }

    @Test
    void getCategoryByIdNotFound() throws  Exception {
        mockMvc.perform(get("/toronto/categories/{categoryId}", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCategoriesByFacility() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories", facilityId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", greaterThan(0)));
    }

    @Test
    void  getCategoriesByFacilityNoneFound() throws Exception {
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}