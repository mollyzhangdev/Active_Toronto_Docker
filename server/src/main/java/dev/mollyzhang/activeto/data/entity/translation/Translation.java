package dev.mollyzhang.activeto.data.entity.translation;

import dev.mollyzhang.activeto.data.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Table(name = "translation")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Translation extends BaseEntity {

    @ElementCollection
    @CollectionTable(
            name="language_translation",
            joinColumns=@JoinColumn(name="TRANSLATION_ID")
    )
    @MapKeyJoinColumn(name="LANGUAGE_ID")
    @MapKeyColumn(name = "LANGUAGE_ID")
    @Column(name = "DESCRIPTION")
    private Map<String, String> translations;
//
//    @Override
//    public String toString() {
//        return "Translation{" +
//                "translations=" + translations.get("en") +
//                '}';
//    }

    //    @OneToMany(mappedBy = "translation", fetch = FetchType.LAZY)
//    private Set<LanguageTranslation> languageTranslations;
}
