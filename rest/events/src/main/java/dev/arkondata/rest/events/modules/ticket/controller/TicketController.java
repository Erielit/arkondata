package dev.arkondata.rest.events.modules.ticket.controller;

import dev.arkondata.rest.events.core.ApiResponse;
import dev.arkondata.rest.events.modules.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class TicketController {
    private final TicketService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<ApiResponse> getAllByEvent(@PathVariable("id") UUID id) {
        return service.findAllByEvent(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAll(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> sellTicket(@PathVariable("id") UUID id) {
        return service.sell(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> redeemTicket(@PathVariable("id") UUID id) {
        return service.redeem(id);
    }
}
