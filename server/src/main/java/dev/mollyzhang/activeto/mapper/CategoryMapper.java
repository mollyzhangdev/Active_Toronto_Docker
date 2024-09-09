package dev.mollyzhang.activeto.mapper;

import dev.mollyzhang.activeto.business.domain.CategoryDTO;
import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;
import dev.mollyzhang.activeto.data.entity.main.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDTO toDTO(Category category, LanguageFilterEnum language){
        return CategoryDTO.builder()
                .id(category.getId())
                .title(category.getTitleTranslation().getTranslations().get(language.getValue()))
                .build();
    }

    public Page<CategoryDTO> toDTOPage(Page<Category> page, LanguageFilterEnum language){
        return new PageImpl<>(
                page.getContent().stream().map(availability -> toDTO(availability, language)).collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
