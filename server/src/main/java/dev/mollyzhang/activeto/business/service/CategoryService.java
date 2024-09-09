package dev.mollyzhang.activeto.business.service;

import dev.mollyzhang.activeto.business.domain.CategoryDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.CategoryParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryDTO findById(CategoryParams params);

    Page<CategoryDTO> findAll(CategoryParams params);

    Page<CategoryDTO> findAllByFacility(CategoryParams params);
}
