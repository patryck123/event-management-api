package com.patryck.event.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "registrations", uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "attendee_email"}))
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "event_id") private Event event;
    @Column(nullable = false) private String attendeeName;
    @Column(nullable = false) private String attendeeEmail;
    @Column(nullable = false, updatable = false) private LocalDateTime registeredAt;
    @PrePersist protected void onCreate() { registeredAt = LocalDateTime.now(); }
}
