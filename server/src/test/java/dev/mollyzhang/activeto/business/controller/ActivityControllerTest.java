package dev.mollyzhang.activeto.business.controller;

import dev.mollyzhang.activeto.business.domain.ActivityDTO;
import dev.mollyzhang.activeto.business.domain.AddressDTO;
import dev.mollyzhang.activeto.business.domain.FacilityDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.ActivityParams;
import dev.mollyzhang.activeto.business.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.function.Function;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Qualifier("activityService")
    ActivityService service;

    Page<ActivityDTO> activityDTOPage;
    List<ActivityDTO> activityDTOS;

    @BeforeEach
    void setUp() {
        ActivityDTO activity1 = ActivityDTO.builder()
                .id((long)173774)
                .title("Lane Swim")
                .type("Lane Swim")
                .category("Swimming")
                .reservationURL("www.url.com")
                .isAvailable(true)
                .endTime(LocalDateTime.of(2024, 12, 31, 12, 0))
                .startTime(LocalDateTime.now())
                .minAge(84)
                .maxAge(0)
                .lastUpdated(LocalDateTime.of(2024, 8, 31, 18, 23, 41))
                .facility(
                        FacilityDTO.builder()
                                .id((long)2163)
                                .title("Leaside Memorial Gardens Swimming Pool - Indoor Pool")
                                .phone("416-396-2822")
                                .email("email@email.com")
                                .url("https://www.toronto.ca/data/parks/prd/facilities/complex/542/index.html")
                                .address(
                                        AddressDTO.builder()
                                                .id((long)2163)
                                                .street("1073  Millwood Rd")
                                                .city("North York")
                                                .province("Ontario")
                                                .postalCode("M4G1X6")
                                                .country("Canada")
                                                .build()
                                )
                                .longitude(-79.361106)
                                .latitude(43.70198)
                                .distance(9154681.588890603)
                                .build()
                ).build();

        ActivityDTO activity2 = ActivityDTO.builder()
                .id((long)173833)
                .title("Lane Swim")
                .type("Lane Swim")
                .category("Swimming")
                .reservationURL("www.url.com")
                .isAvailable(true)
                .endTime(LocalDateTime.of(2024, 9, 8, 12, 0))
                .startTime(LocalDateTime.of(2024, 9, 8, 17, 30, 0))
                .minAge(84)
                .maxAge(0)
                .lastUpdated(LocalDateTime.of(2024, 8, 31, 18, 23, 41))
                .facility(
                        FacilityDTO.builder()
                                .id((long)2163)
                                .title("Leaside Memorial Gardens Swimming Pool - Indoor Pool")
                                .phone("416-396-2822")
                                .email("email@email.com")
                                .url("https://www.toronto.ca/data/parks/prd/facilities/complex/542/index.html")
                                .address(
                                        AddressDTO.builder()
                                                .id((long)2163)
                                                .street("1073  Millwood Rd")
                                                .city("North York")
                                                .province("Ontario")
                                                .postalCode("M4G1X6")
                                                .country("Canada")
                                                .build()
                                )
                                .longitude(-79.361106)
                                .latitude(43.70198)
                                .distance(9154681.588890603)
                                .build()
                ).build();

        ActivityDTO activity3 = ActivityDTO.builder()
                .id((long)168716)
                .title("Aquatic Fitness: Shallow")
                .type("Aquatic Fitness")
                .category("Swimming")
                .reservationURL("www.url.com")
                .isAvailable(true)
                .endTime(LocalDateTime.of(2024, 9, 11, 8, 0))
                .startTime(LocalDateTime.of(2024, 9, 11, 9, 0, 0))
                .minAge(84)
                .maxAge(0)
                .lastUpdated(LocalDateTime.of(2024, 8, 31, 18, 23, 41))
                .facility(
                        FacilityDTO.builder()
                                .id((long)2163)
                                .title("Leaside Memorial Gardens Swimming Pool - Indoor Pool")
                                .phone("416-396-2822")
                                .email("email@email.com")
                                .url("https://www.toronto.ca/data/parks/prd/facilities/complex/542/index.html")
                                .address(
                                        AddressDTO.builder()
                                                .id((long)2163)
                                                .street("1073  Millwood Rd")
                                                .city("North York")
                                                .province("Ontario")
                                                .postalCode("M4G1X6")
                                                .country("Canada")
                                                .build()
                                )
                                .longitude(-79.361106)
                                .latitude(43.70198)
                                .distance(9154681.588890603)
                                .build()
                ).build();

        activityDTOS = new ArrayList<>();
        activityDTOS.add(activity1);
        activityDTOS.add(activity2);
        activityDTOS.add(activity3);
        activityDTOPage = new Page<ActivityDTO>() {

            @Override
            public Iterator<ActivityDTO> iterator() {
                return activityDTOS.iterator();
            }

            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super ActivityDTO, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 10;
            }

            @Override
            public int getNumberOfElements() {
                return activityDTOS.size();
            }

            @Override
            public List<ActivityDTO> getContent() {
                return activityDTOS;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return Sort.by("title");
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }
        };

        }


    @Test
    void searchActivities() throws Exception {
        given(service.findAll(Mockito.any(ActivityParams.class))).willReturn(activityDTOPage);

        mockMvc.perform(get("/toronto/activities?q=swimming")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith("application/json"))
                        .andExpect(jsonPath("$.numberOfElements", is(activityDTOPage.getNumberOfElements())));

    }

    @Test
    void searchActivitiesNoneFound() throws Exception {
        Page<ActivityDTO> page = new Page<ActivityDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super ActivityDTO, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 10;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<ActivityDTO> getContent() {
                return new ArrayList<>();
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return Sort.by("default");
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<ActivityDTO> iterator() {
                return getContent().iterator();
            }
        };
        given(service.findAll(Mockito.any(ActivityParams.class))).willReturn(page);

        mockMvc.perform(get("/toronto/activities?q=swimming")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(page.getContent().size())));
    }

    @Test
    void getActivityById() throws Exception {
        given(service.findById(Mockito.any(ActivityParams.class))).willReturn(activityDTOS.get(0));
        mockMvc.perform(get("/toronto/activities/{activityId}", activityDTOS.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id", is(activityDTOS.get(0).getId().intValue())));
    }

    @Test
    void getActivityByIdNotFound() throws Exception {
        given(service.findById(Mockito.any(ActivityParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/activities/{activityId}", activityDTOS.get(0).getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void searchActivitiesByType() throws Exception {
        given(service.findByType(Mockito.any(ActivityParams.class))).willReturn(activityDTOPage);
        mockMvc.perform(get("/toronto/types/2173/activities")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.numberOfElements", is(activityDTOPage.getNumberOfElements())));
    }

    @Test
    void searchActivitiesByTypeNotFound() throws Exception {
        given(service.findByType(Mockito.any(ActivityParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/types/2173/activities")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchActivitiesByTypeAndFacility() throws Exception {
        given(service.findByFacilityAndType(Mockito.any(ActivityParams.class))).willReturn(activityDTOPage);
        mockMvc.perform(get("/toronto/facilities/2148/types/2142/activities")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfElements", is(activityDTOPage.getNumberOfElements())));
    }

    @Test
    void searchActivitiesByTypeAndFacilityNotFound() throws Exception {
        given(service.findByFacilityAndType(Mockito.any(ActivityParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/facilities/2148/types/2142/activities")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}