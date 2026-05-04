package dev.mollyzhang.activeto.data.entity.translation;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "language")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language{

    @Id
    private String id;

    @Column
    private String title;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

//    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
//    private Set<LanguageTranslation> translations;
}