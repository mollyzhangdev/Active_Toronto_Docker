package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.main.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByTitle(String title);
}
