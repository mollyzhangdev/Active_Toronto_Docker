package dev.mollyzhang.activeto.mapper;

import dev.mollyzhang.activeto.business.domain.TypeDTO;
import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;
import dev.mollyzhang.activeto.data.entity.main.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {
    public TypeDTO toDTO(Type type, LanguageFilterEnum language){
        return TypeDTO.builder()
                .id(type.getId())
                .title(type.getTitleTranslation().getTranslations().get(language.getValue()))
                .category(type.getCategory().getTitleTranslation().getTranslations().get(language.getValue()))
                .build();
    }
}
