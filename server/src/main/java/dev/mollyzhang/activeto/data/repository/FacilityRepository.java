package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.main.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findAllByCityId(Long cityId);
}
