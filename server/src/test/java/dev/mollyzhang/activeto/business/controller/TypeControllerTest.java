package dev.mollyzhang.activeto.business.controller;

import dev.mollyzhang.activeto.business.domain.TypeDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.TypeParams;
import dev.mollyzhang.activeto.business.service.TypeService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(TypeController.class)
class TypeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Qualifier("typeService")
    TypeService typeService;

    Page<TypeDTO> typeDTOPage, typeDTOPageNoneFound;
    List<TypeDTO> typeDTOList;


    @BeforeEach
    void setUp() {
        TypeDTO typeDTO1 = TypeDTO.builder()
                .id((long)2138)
                .title("Adapted Leisure Swim")
                .category("Swimming")
                .build();

        TypeDTO typeDTO2 = TypeDTO.builder()
                .id((long)2140)
                .title("Humber College Swim")
                .category("Swimming")
                .build();
        typeDTOList = new ArrayList<>();
        typeDTOList.add(typeDTO1);
        typeDTOList.add(typeDTO2);

        typeDTOPage = new Page<TypeDTO>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return getContent().size();
            }

            @Override
            public <U> Page<U> map(Function<? super TypeDTO, ? extends U> converter) {
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
                return getContent().size();
            }

            @Override
            public List<TypeDTO> getContent() {
                return typeDTOList;
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
            public Iterator<TypeDTO> iterator() {
                return getContent().iterator();
            }
        };

        typeDTOPageNoneFound = new Page<TypeDTO>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return getContent().size();
            }

            @Override
            public <U> Page<U> map(Function<? super TypeDTO, ? extends U> converter) {
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
                return getContent().size();
            }

            @Override
            public List<TypeDTO> getContent() {
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
            public Iterator<TypeDTO> iterator() {
                return getContent().iterator();
            }
        };

    }

    @Test
    void searchTypes() throws Exception {
        given(typeService.findAll(Mockito.any(TypeParams.class))).willReturn(typeDTOPage);
        mockMvc.perform(get("/toronto/types?q=swim")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(typeDTOPage.getContent().size())));
    }

    @Test
    void searchTypesNoneFound() throws Exception {
        given(typeService.findAll(Mockito.any(TypeParams.class))).willReturn(typeDTOPageNoneFound);
        mockMvc.perform(get("/toronto/types?q=swim")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(typeDTOPageNoneFound.getContent().size())));
    }

    @Test
    void getTypeById() throws Exception {
        given(typeService.getById(Mockito.any(TypeParams.class))).willReturn(typeDTOList.get(0));
        mockMvc.perform(get("/toronto/types/{typeId}", typeDTOList.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(typeDTOList.get(0).getId().intValue())));
    }

    @Test
    void getTypeByIdNoFound() throws Exception {
        given(typeService.getById(Mockito.any(TypeParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/types/{typeId}", typeDTOList.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByCategory() throws Exception {
        given(typeService.findAllByCategory(Mockito.any(TypeParams.class))).willReturn(typeDTOPage);
        mockMvc.perform(get("/toronto/categories/{categoryId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(typeDTOPage.getContent().size())));
    }

    @Test
    void searchTypesByCategoryNoneFound() throws Exception {
        given(typeService.findAllByCategory(Mockito.any(TypeParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/categories/{categoryId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByFacility() throws Exception {
        given(typeService.findAllByFacility(Mockito.any(TypeParams.class))).willReturn(typeDTOPage);
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(typeDTOPage.getContent().size())));
    }

    @Test
    void searchTypesByFacilityNoneFound() throws Exception {
        given(typeService.findAllByFacility(Mockito.any(TypeParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/facilities/{facilityId}/types", 12345)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchTypesByFacilityAndCategory() throws Exception {
        given(typeService.findAllByFacilityAndCategory(Mockito.any(TypeParams.class))).willReturn(typeDTOPage);
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories/{categoryId}/types", 12345, 23456)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(typeDTOPage.getContent().size())));
    }

    @Test
    void searchTypesByFacilityAndCategoryNoneFound() throws Exception {
        given(typeService.findAllByFacilityAndCategory(Mockito.any(TypeParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories/{categoryId}/types", 12345, 23456)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}