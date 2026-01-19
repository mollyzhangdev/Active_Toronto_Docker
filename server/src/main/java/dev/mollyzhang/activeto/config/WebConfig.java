package dev.mollyzhang.activeto.config;

import dev.mollyzhang.activeto.business.domain.enums.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ActivitySortEnumConverter());
        registry.addConverter(new LanguageFilterEnumConverter());
        registry.addConverter(new FacilitySortEnumConverter());
        registry.addConverter(new CityFilterEnumConverter());
        registry.addConverter(new TypeSortEnumConverter());
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://activeto.mollyzhang.dev") // frontend URL
                .allowedMethods("*");
    }
}