package dev.mollyzhang.activeto.data.entity.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "facility_activity")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityActivity{


    @EmbeddedId
    private FacilityActivityId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FACILITY_ID", referencedColumnName = "id", insertable=false, updatable=false)
    private Facility facility;

    @OneToOne
    @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "id", insertable=false, updatable=false)
    private Activity activity;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;


}
