package dev.mollyzhang.activeto.data.entity.main;

import dev.mollyzhang.activeto.data.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name = "city")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseEntity {

    @Column
    private String title;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Facility> facilities;

    @Override
    public String toString() {
        return "City{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return title.equals(city.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
