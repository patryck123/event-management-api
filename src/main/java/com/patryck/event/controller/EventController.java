package com.patryck.event.controller;
import com.patryck.event.entity.*;
import com.patryck.event.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/events") @RequiredArgsConstructor
@Tag(name = "Eventos", description = "Gestão de eventos e inscrições")
public class EventController {
    private final EventRepository eventRepo;
    private final RegistrationRepository regRepo;
    @PostMapping public ResponseEntity<Event> create(@RequestBody Event e) { return ResponseEntity.status(HttpStatus.CREATED).body(eventRepo.save(e)); }
    @GetMapping public ResponseEntity<List<Event>> findAll() { return ResponseEntity.ok(eventRepo.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Event> findById(@PathVariable Long id) { return eventRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @GetMapping("/status/{status}") public ResponseEntity<List<Event>> byStatus(@PathVariable EventStatus status) { return ResponseEntity.ok(eventRepo.findByStatus(status)); }
    @GetMapping("/category/{cat}") public ResponseEntity<List<Event>> byCategory(@PathVariable EventCategory cat) { return ResponseEntity.ok(eventRepo.findByCategory(cat)); }
    @PostMapping("/{id}/register") @Transactional @Operation(summary = "Inscrever participante no evento")
    public ResponseEntity<?> register(@PathVariable Long id, @RequestBody Registration reg) {
        Event event = eventRepo.findById(id).orElse(null);
        if (event == null) return ResponseEntity.notFound().build();
        if (event.getCurrentAttendees() >= event.getMaxAttendees()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Evento lotado");
        if (regRepo.existsByEventIdAndAttendeeEmail(id, reg.getAttendeeEmail())) return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já inscrito");
        event.setCurrentAttendees(event.getCurrentAttendees() + 1); eventRepo.save(event);
        reg.setEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(regRepo.save(reg));
    }
    @GetMapping("/{id}/registrations") @Operation(summary = "Lista de inscritos") public ResponseEntity<List<Registration>> registrations(@PathVariable Long id) { return ResponseEntity.ok(regRepo.findByEventId(id)); }
}
