package dev.mollyzhang.activeto.data.entity.main;

import dev.mollyzhang.activeto.data.entity.BaseEntity;
import javax.persistence.*;

import dev.mollyzhang.activeto.data.entity.translation.Translation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Table(name = "type")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Type extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "TITLE_TRANSLATION_ID")
    private Translation titleTranslation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Activity> activities;

    @Override
    public String toString() {
        return "Type{" +
                "titleTranslation=" + titleTranslation +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Type type = (Type) o;
        return category.equals(type.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category);
    }
}
