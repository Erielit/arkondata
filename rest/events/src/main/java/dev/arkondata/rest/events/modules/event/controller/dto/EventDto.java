package dev.arkondata.rest.events.modules.event.controller.dto;

import dev.arkondata.rest.events.modules.event.entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventDto(UUID id, String name, LocalDate start, LocalDate end, Integer maxTickets,
                       LocalDateTime createdAt, Boolean status) {
    public Event toEntity() {
        return new Event(id, name, start, end, maxTickets, createdAt, status);
    }
}
