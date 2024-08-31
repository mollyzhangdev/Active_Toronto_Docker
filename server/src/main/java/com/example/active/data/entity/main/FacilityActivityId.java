package com.example.active.data.entity.main;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FacilityActivityId implements Serializable {
    @Column(name = "FACILITY_ID", nullable = false)
    private int facilityId;
    @Column(name = "ACTIVITY_ID", nullable = false)
    private int activityId;

    public FacilityActivityId(int facilityId, int activityId) {
        this.facilityId = facilityId;
        this.activityId = activityId;
    }

    public FacilityActivityId() {
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
