package dev.mollyzhang.activeto.business.service;

import dev.mollyzhang.activeto.business.domain.FacilityDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.FacilityParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface FacilityService {

    FacilityDTO findById(FacilityParams params);

    Page<FacilityDTO> findAll(FacilityParams params);

    Page<FacilityDTO> findAllByType(FacilityParams params);
}
