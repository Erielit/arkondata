package dev.arkondata.rest.events.modules.event.controller;

import dev.arkondata.rest.events.core.ApiResponse;
import dev.arkondata.rest.events.modules.event.controller.dto.EventDto;
import dev.arkondata.rest.events.modules.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEvent(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> create(@RequestBody EventDto dto) {
        return service.save(dto.toEntity());
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> update(@RequestBody EventDto dto) {
        return service.update(dto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable("id") UUID id) {
        return service.deleteEvent(id);
    }
}
