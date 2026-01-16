package dev.mollyzhang.activeto.business.service.implementation;

import dev.mollyzhang.activeto.business.domain.CategoryDTO;

import dev.mollyzhang.activeto.business.domain.paramobject.CategoryParams;
import dev.mollyzhang.activeto.business.service.CategoryService;
import dev.mollyzhang.activeto.data.entity.main.Category;
import dev.mollyzhang.activeto.data.entity.main.City;
import dev.mollyzhang.activeto.data.entity.main.Facility;
import dev.mollyzhang.activeto.data.repository.CategoryRepository;
import dev.mollyzhang.activeto.data.repository.CityRepository;
import dev.mollyzhang.activeto.data.repository.FacilityRepository;
import dev.mollyzhang.activeto.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static dev.mollyzhang.activeto.util.Utility.MAX_STRING_EDIT_DISTANCE;
import static dev.mollyzhang.activeto.util.Utility.stringDistanceScore;

@Service
@Qualifier("categoryService")
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryMapper mapper;

    @Override
    public CategoryDTO findById(CategoryParams params) {
        City city = cityRepository.findByTitle(params.getCity()).orElseThrow();
        Category category = categoryRepository.findById(params.getCategoryId()).orElseThrow();
        if(!category.getCity().getTitle().equalsIgnoreCase(city.getTitle())){
            throw new NoSuchElementException("Category not found");
        }
        return mapper.toDTO(category, params.getLanguage());
    }

    @Override
    public Page<CategoryDTO> findAll(CategoryParams params) {
        City city = cityRepository.findByTitle(params.getCity()).orElseThrow();
        List<CategoryDTO> categories = city.getCategories()
                .stream()
                .map(category -> mapper.toDTO(category,params.getLanguage()))
                .filter(categoryDTO -> stringDistanceScore(params.getQuery(), categoryDTO.getTitle()) < MAX_STRING_EDIT_DISTANCE)
                .sorted(Comparator.comparing(CategoryDTO::getTitle))
                .sorted(Comparator.comparing(categoryDTO -> stringDistanceScore(params.getQuery(), categoryDTO.getTitle())))
                .collect(Collectors.toList());
        return listToPage(categories, params.getPageNumber(), params.getPageSize());
    }

    @Override
    public Page<CategoryDTO> findAllByFacility(CategoryParams params) {
        City city = cityRepository.findByTitle(params.getCity()).orElseThrow();
        Facility facility = facilityRepository.findById(params.getFacilityId()).orElseThrow();
        if(!facility.getCity().getId().equals(city.getId())){
            throw new NoSuchElementException("Facility not found");
        }
        List<CategoryDTO> categories = facility.getFacilityActivities()
                .stream()
                .map(facilityActivity -> facilityActivity.getActivity().getType().getCategory())
                .distinct()
                .map(category -> mapper.toDTO(category,params.getLanguage()))
                .filter(categoryDTO -> stringDistanceScore(params.getQuery(), categoryDTO.getTitle()) < MAX_STRING_EDIT_DISTANCE)
                .sorted(Comparator.comparing(CategoryDTO::getTitle))
                .sorted(Comparator.comparing(categoryDTO -> stringDistanceScore(params.getQuery(), categoryDTO.getTitle())))
                .collect(Collectors.toList());
        return listToPage(categories, params.getPageNumber(), params.getPageSize());
    }

    private Page<CategoryDTO> listToPage(List<CategoryDTO> categories, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("title"));
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), categories.size());
        try{
            return new PageImpl<>(categories.subList(start, end), pageable, categories.size());
        }catch (IllegalArgumentException e){
            return new PageImpl<>(List.of(), pageable, categories.size());
        }
    }
}
