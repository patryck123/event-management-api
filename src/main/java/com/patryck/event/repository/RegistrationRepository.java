package com.patryck.event.repository;
import com.patryck.event.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    boolean existsByEventIdAndAttendeeEmail(Long eventId, String email);
}
