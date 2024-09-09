package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.translation.LanguageTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<LanguageTranslation, Long> {

}
