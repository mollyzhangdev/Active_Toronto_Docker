package com.example.active.business.controller;

import com.example.active.business.domain.AddressDTO;
import com.example.active.business.domain.FacilityDTO;
import com.example.active.business.domain.paramobject.FacilityParams;
import com.example.active.business.service.FacilityService;
import com.example.active.data.entity.main.Address;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacilityController.class)
class FacilityControllerTest {

    @MockBean
    @Qualifier("facilityService")
    FacilityService service;

    @Autowired
    MockMvc mockMvc;

    List<FacilityDTO> facilityDTOList;
    Page<FacilityDTO> facilityDTOPage;
    Page<FacilityDTO> facilityDTOPageNoContent;


    @BeforeEach
    void setUp() {
        FacilityDTO facilityDTO1 = FacilityDTO.builder()
                .id((long)2150)
                .title("Broadlands Community Centre")
                .phone("416-395-7966")
                .email("email@email.com")
                .url("https://www.toronto.ca/data/parks/prd/facilities/complex/7/index.html")
                .latitude(43.745912)
                .longitude(-79.322746)
                .distance(9152254.917828765)
                .address(AddressDTO.builder()
                        .id((long)2150)
                        .street("19  Castlegrove Blvd")
                        .city("North York")
                        .province("Ontario")
                        .postalCode("M3A1K9")
                        .country("Canada")
                        .build()
                ).build();

        FacilityDTO facilityDTO2 = FacilityDTO.builder()
                .id((long)2152)
                .title("Adam Beck Community Centre")
                .phone("416-392-0741")
                .email("email@email.com")
                .url("https://www.toronto.ca/data/parks/prd/facilities/complex/13/index.html")
                .latitude(43.682861)
                .longitude(-79.288852)
                .distance(9148646.451096414)
                .address(AddressDTO.builder()
                        .id((long)2152)
                        .street("79  Lawlor Ave")
                        .city("Toronto and East York")
                        .province("Ontario")
                        .postalCode("M4E3L8")
                        .country("Canada")
                        .build()
                ).build();
        facilityDTOList = new ArrayList<>();
        facilityDTOList.add(facilityDTO1);
        facilityDTOList.add(facilityDTO2);
        facilityDTOPage = new Page<FacilityDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 2;
            }

            @Override
            public <U> Page<U> map(Function<? super FacilityDTO, ? extends U> converter) {
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
                return 2;
            }

            @Override
            public List<FacilityDTO> getContent() {
                return facilityDTOList;
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
            public Iterator<FacilityDTO> iterator() {
                return facilityDTOList.iterator();
            }
        };

        facilityDTOPageNoContent = new Page<FacilityDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super FacilityDTO, ? extends U> converter) {
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
            public List<FacilityDTO> getContent() {
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
            public Iterator<FacilityDTO> iterator() {
                return getContent().iterator();
            }
        };

    }

    @Test
    void findAll() throws Exception {
        given(service.findAll(Mockito.any(FacilityParams.class))).willReturn(facilityDTOPage);
        mockMvc.perform(get("/toronto/facilities?q=community")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(facilityDTOPage.getContent().size())));
    }

    @Test
    void findAllNoneFound() throws Exception {
        given(service.findAll(Mockito.any(FacilityParams.class))).willReturn(facilityDTOPageNoContent);
        mockMvc.perform(get("/toronto/facilities?q=qwer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(facilityDTOPageNoContent.getContent().size())));
    }

    @Test
    void findById() throws Exception {
        given(service.findById(Mockito.any(FacilityParams.class))).willReturn(facilityDTOList.get(0));
        mockMvc.perform(get("/toronto/facilities/{facilityId}", facilityDTOList.get(0).getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(facilityDTOList.get(0).getId().intValue())));
    }

    @Test
    void findByIdNotFound() throws Exception {
        given(service.findById(Mockito.any(FacilityParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/facilities/{facilityId}", facilityDTOList.get(0).getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findByType() throws Exception {
        given(service.findAllByType(Mockito.any(FacilityParams.class))).willReturn(facilityDTOPage);
        mockMvc.perform(get("/toronto/types/{typeId}/facilities", 1235)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(facilityDTOPage.getContent().size())));
    }

    @Test
    void findByTypeNoneFound() throws Exception {
        given(service.findAllByType(Mockito.any(FacilityParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/types/{typeId}", 12345)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}