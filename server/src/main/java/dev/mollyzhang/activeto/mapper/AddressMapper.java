package dev.mollyzhang.activeto.mapper;

import dev.mollyzhang.activeto.business.domain.AddressDTO;
import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;
import dev.mollyzhang.activeto.data.entity.main.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDTO toDTO(Address address, LanguageFilterEnum language){
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreetTranslation().getTranslations().get(language.getValue()))
                .city(address.getCity())
                .province(address.getProvince())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
