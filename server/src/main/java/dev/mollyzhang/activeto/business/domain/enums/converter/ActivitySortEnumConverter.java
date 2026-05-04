package dev.mollyzhang.activeto.business.domain.enums.converter;

import dev.mollyzhang.activeto.business.domain.enums.ActivitySortEnum;
import org.springframework.core.convert.converter.Converter;

public class ActivitySortEnumConverter implements Converter<String, ActivitySortEnum> {
    @Override
    public ActivitySortEnum convert(String source) {
        return ActivitySortEnum.of(source);
    }
}