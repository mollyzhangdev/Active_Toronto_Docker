package dev.mollyzhang.activeto.business.domain.enums.converter;

import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;
import org.springframework.core.convert.converter.Converter;

public class LanguageFilterEnumConverter implements Converter<String, LanguageFilterEnum> {
    @Override
    public LanguageFilterEnum convert(String source) {
        return LanguageFilterEnum.of(source);
    }
}