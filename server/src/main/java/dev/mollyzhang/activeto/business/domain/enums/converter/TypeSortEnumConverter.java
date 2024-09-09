package dev.mollyzhang.activeto.business.domain.enums.converter;

import dev.mollyzhang.activeto.business.domain.enums.TypeSortEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeSortEnumConverter implements Converter<String, TypeSortEnum> {
    @Override
    public TypeSortEnum convert(String source) {
        return TypeSortEnum.of(source);
    }
}
