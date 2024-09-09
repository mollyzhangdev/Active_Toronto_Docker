package dev.mollyzhang.activeto.data.entity.main;

import dev.mollyzhang.activeto.data.entity.BaseEntity;
import javax.persistence.*;

import dev.mollyzhang.activeto.data.entity.translation.Translation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "activity")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "TITLE_TRANSLATION_ID")
    private Translation titleTranslation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TYPE_ID")
    private Type type;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
    private List<Availability> availabilities;

    @OneToOne(mappedBy = "activity", fetch = FetchType.LAZY)
    private FacilityActivity facilityActivity;
}
