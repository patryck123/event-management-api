package com.patryck.event.repository;
import com.patryck.event.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
    List<Event> findByCategory(EventCategory category);
    List<Event> findByNameContainingIgnoreCase(String name);
}
