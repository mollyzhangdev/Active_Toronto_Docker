package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.main.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByCategoryCityId(Long cityId);
}