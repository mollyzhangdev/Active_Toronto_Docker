package dev.mollyzhang.activeto.data.entity.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "reference_facility_locationorigin")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceFacilityLocationorigin implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name="FACILITY_ID")
    private Facility facility;

    @Column
    private int locationId;
}
