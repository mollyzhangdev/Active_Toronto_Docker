package dev.mollyzhang.activeto.business.service;

import dev.mollyzhang.activeto.business.domain.ActivityDTO;
import dev.mollyzhang.activeto.business.domain.paramobject.ActivityParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ActivityService{

    ActivityDTO findById(ActivityParams params);

    Page<ActivityDTO> findAll(ActivityParams params);

    Page<ActivityDTO> findByType(ActivityParams params);

    Page<ActivityDTO> findByFacilityAndType(ActivityParams params);

}