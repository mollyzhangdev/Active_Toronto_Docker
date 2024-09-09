package dev.mollyzhang.activeto.business.controller;

import dev.mollyzhang.activeto.business.domain.CategoryDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.CategoryParams;
import dev.mollyzhang.activeto.business.service.CategoryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @Qualifier("categoryService")
    CategoryService service;

    Page<CategoryDTO> categoryDTOPage;
    List<CategoryDTO> categoryDTOList;

    @BeforeEach
    void setUp() {
        CategoryDTO categoryDTO1 = CategoryDTO.builder()
                .id((long)209)
                .title("Arts")
                .build();

        CategoryDTO categoryDTO2 = CategoryDTO.builder()
                .id((long)210)
                .title("Swimming")
                .build();

        CategoryDTO categoryDTO3 = CategoryDTO.builder()
                .id((long)211)
                .title("Skating")
                .build();

        categoryDTOList = new ArrayList<>();
        categoryDTOList.add(categoryDTO1);
        categoryDTOList.add(categoryDTO2);
        categoryDTOList.add(categoryDTO3);

        categoryDTOPage = new Page<CategoryDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return categoryDTOList.size();
            }

            @Override
            public <U> Page<U> map(Function<? super CategoryDTO, ? extends U> converter) {
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
                return categoryDTOList.size();
            }

            @Override
            public List<CategoryDTO> getContent() {
                return categoryDTOList;
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
            public Iterator<CategoryDTO> iterator() {
                return categoryDTOList.iterator();
            }
        };
    }

    @Test
    void searchCategories() throws Exception {
        Page<CategoryDTO> page = new Page<CategoryDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super CategoryDTO, ? extends U> converter) {
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
                return 1;
            }

            @Override
            public List<CategoryDTO> getContent() {
                List<CategoryDTO> list = new ArrayList<>();
                list.add(categoryDTOList.get(1));
                return list;
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
            public Iterator<CategoryDTO> iterator() {
                return getContent().iterator();
            }
        };
        given(service.findAll(Mockito.any(CategoryParams.class))).willReturn(page);
        mockMvc.perform(get("/toronto/categories?q=swimming")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(page.getContent().size())));
    }

    @Test
    void searchCategoriesNoneFound() throws Exception {
        Page<CategoryDTO> page = new Page<CategoryDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super CategoryDTO, ? extends U> converter) {
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
            public List<CategoryDTO> getContent() {
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
            public Iterator<CategoryDTO> iterator() {
                return getContent().iterator();
            }
        };
        given(service.findAll(Mockito.any(CategoryParams.class))).willReturn(page);
        mockMvc.perform(get("/toronto/categories?q=category")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(page.getContent().size())));

    }

    @Test
    void getCategoryById() throws Exception {
        given(service.findById(Mockito.any(CategoryParams.class))).willReturn(categoryDTOList.get(0));
        mockMvc.perform(get("/toronto/categories/{categoryId}", categoryDTOList.get(0).getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(categoryDTOList.get(0).getId().intValue())));
    }

    @Test
    void getCategoryByIdNotFound() throws  Exception {
        given(service.findById(Mockito.any(CategoryParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/categories/{categoryId}", 12345)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCategoriesByFacility() throws Exception {
        given(service.findAllByFacility(Mockito.any(CategoryParams.class))).willReturn(categoryDTOPage);
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories", 12345)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(categoryDTOList.size())));
    }

    @Test
    void  getCategoriesByFacilityNoneFound() throws Exception {
        given(service.findAllByFacility(Mockito.any(CategoryParams.class))).willThrow(new NoSuchElementException());
        mockMvc.perform(get("/toronto/facilities/{facilityId}/categories", 12345)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}