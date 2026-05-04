package dev.mollyzhang.activeto.business.service;

import dev.mollyzhang.activeto.business.domain.TypeDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.TypeParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface TypeService {
    TypeDTO getById(TypeParams params);

    Page<TypeDTO> findAll(TypeParams params);

    Page<TypeDTO> findAllByCategory(TypeParams params);

    Page<TypeDTO> findAllByFacility(TypeParams params);

    Page<TypeDTO> findAllByFacilityAndCategory(TypeParams params);
}
