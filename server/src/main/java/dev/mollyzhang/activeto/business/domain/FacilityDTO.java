package dev.mollyzhang.activeto.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO implements Comparator<FacilityDTO>{
    private Long id;
    private String title;
    private String phone;
    private String email;
    private String url;
    private AddressDTO address;
    private Double longitude;
    private Double latitude;
    private Double distance;

    @Override
    public int compare(FacilityDTO o1, FacilityDTO o2) {
        return 0;
    }
}
