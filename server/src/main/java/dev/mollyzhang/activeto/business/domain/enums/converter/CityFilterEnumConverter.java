package dev.mollyzhang.activeto.business.domain.enums.converter;

import dev.mollyzhang.activeto.business.domain.enums.CityFilterEnum;
import org.springframework.core.convert.converter.Converter;

public class CityFilterEnumConverter implements Converter<String, CityFilterEnum> {
    @Override
    public CityFilterEnum convert(String source) {
        return CityFilterEnum.of(source);
    }
}