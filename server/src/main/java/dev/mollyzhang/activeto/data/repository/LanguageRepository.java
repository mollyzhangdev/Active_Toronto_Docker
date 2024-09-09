package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.translation.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
