package com.example.active.data.entity.main;

import javax.persistence.*;

import com.example.active.data.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "availability")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Availability extends BaseEntity {

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "max_age")
    private int maxAge;

    @Column(name = "min_age")
    private int minAge;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}

