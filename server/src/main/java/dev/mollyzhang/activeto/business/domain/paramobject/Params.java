package dev.mollyzhang.activeto.business.domain.paramobject;

import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;

public abstract class Params {
    public Params() {
    }
    private LanguageFilterEnum language;
    private String city;

    public LanguageFilterEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageFilterEnum language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
