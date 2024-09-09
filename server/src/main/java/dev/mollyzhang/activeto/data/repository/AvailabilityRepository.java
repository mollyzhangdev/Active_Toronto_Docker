package dev.mollyzhang.activeto.data.repository;

import dev.mollyzhang.activeto.data.entity.main.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findAllByStartTimeAfterAndEndTimeBefore(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Availability> findAllByStartTimeAfterAndEndTimeBeforeAndIsAvailable(
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );

    List<Availability> findAllByActivityTypeIdAndStartTimeAfterAndEndTimeBefore(
            Long typeId,
            LocalDateTime start,
            LocalDateTime end
    );
    List<Availability> findAllByActivityTypeIdAndStartTimeAfterAndEndTimeBeforeAndIsAvailable(
            Long typeId,
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );

    List<Availability> findAllByFacilityIdAndActivityTypeIdAndStartTimeAfterAndEndTimeBefore(
            Long facilityId,
            Long typeId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Availability> findAllByFacilityIdAndActivityTypeIdAndStartTimeAfterAndEndTimeBeforeAndIsAvailable(
            Long facilityId,
            Long typeId,
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );

    List<Availability> findAllByStartTimeLessThanEqualAndEndTimeAfter(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Availability> findAllByStartTimeLessThanEqualAndEndTimeAfterAndIsAvailable(
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );

    List<Availability> findAllByActivityTypeIdAndStartTimeLessThanEqualAndEndTimeAfter(
            Long typeId,
            LocalDateTime start,
            LocalDateTime end
    );
    List<Availability> findAllByActivityTypeIdAndStartTimeLessThanEqualAndEndTimeAfterAndIsAvailable(
            Long typeId,
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );

    List<Availability> findAllByFacilityIdAndActivityTypeIdAndStartTimeLessThanEqualAndEndTimeAfter(
            Long facilityId,
            Long typeId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Availability> findAllByFacilityIdAndActivityTypeIdAndStartTimeLessThanEqualAndEndTimeAfterAndIsAvailable(
            Long facilityId,
            Long typeId,
            LocalDateTime start,
            LocalDateTime end,
            Boolean isAvailable
    );
}
