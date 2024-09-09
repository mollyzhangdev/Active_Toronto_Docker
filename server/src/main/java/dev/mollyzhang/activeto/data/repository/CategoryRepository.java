package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.main.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCityId(Long cityId);
}
