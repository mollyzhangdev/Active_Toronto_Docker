package dev.mollyzhang.activeto.mapper;

import dev.mollyzhang.activeto.business.domain.FacilityDTO;
import dev.mollyzhang.activeto.business.domain.enums.LanguageFilterEnum;
import dev.mollyzhang.activeto.data.entity.main.Facility;
import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {
    @Autowired
    private AddressMapper addressMapper;

    public FacilityDTO toDTO(Facility facility, LanguageFilterEnum language, Double latitude, Double longitude){
        double distance = SloppyMath.haversinMeters(latitude, longitude, facility.getAddress().getLatitude(), facility.getAddress().getLongitude());
        return FacilityDTO.builder()
                .id(facility.getId())
                .phone(facility.getPhone())
                .title(facility.getTitleTranslation().getTranslations().get(language.getValue()))
                .url(facility.getUrl())
                .address(addressMapper.toDTO(facility.getAddress(),language))
                .email("email@email.com")
                .latitude(facility.getAddress().getLatitude())
                .longitude(facility.getAddress().getLongitude())
                .distance(distance)
                .build();
    }
}
