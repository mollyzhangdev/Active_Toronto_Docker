package dev.mollyzhang.activeto.business.domain.enums.converter;

import dev.mollyzhang.activeto.business.domain.enums.FacilitySortEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacilitySortEnumConverter implements Converter<String, FacilitySortEnum> {
    @Override
    public FacilitySortEnum convert(String source) {
        return FacilitySortEnum.of(source);
    }
}