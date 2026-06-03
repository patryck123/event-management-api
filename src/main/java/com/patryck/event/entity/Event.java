package com.patryck.event.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "events") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(nullable = false) private String location;
    @Column(nullable = false) private LocalDateTime startDateTime;
    @Column(nullable = false) private LocalDateTime endDateTime;
    @Column(nullable = false) private Integer maxAttendees;
    @Builder.Default private Integer currentAttendees = 0;
    @Column(precision = 8, scale = 2) @Builder.Default private BigDecimal ticketPrice = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private EventCategory category;
    @Enumerated(EnumType.STRING) @Builder.Default private EventStatus status = EventStatus.UPCOMING;
    @Column(nullable = false) private String organizer;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
