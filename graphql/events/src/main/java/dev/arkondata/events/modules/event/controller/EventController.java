package dev.arkondata.events.modules.event.controller;

import dev.arkondata.events.modules.event.controller.dto.EventDto;
import dev.arkondata.events.modules.event.entity.Event;
import dev.arkondata.events.modules.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EventController{
    private final EventService service;

    @QueryMapping
    public List<Event> events() {
        return service.findAll();
    }

    @QueryMapping
    public Event event(@Argument("id") UUID id) {
        return service.findById(id);
    }

    @MutationMapping
    public Event createEvent(@Argument("event") EventDto event) {
        return service.save(event.toEntity());
    }

    @MutationMapping
    public Event updateEvent(@Argument("event") EventDto event) {
        return service.update(event.toEntity());
    }

    @MutationMapping
    public Boolean changeStatus(UUID id) {
        return service.deleteEvent(id);
    }
}
