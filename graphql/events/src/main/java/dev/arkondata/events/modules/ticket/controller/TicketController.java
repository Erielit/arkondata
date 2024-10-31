package dev.arkondata.events.modules.ticket.controller;

import dev.arkondata.events.modules.ticket.entity.Ticket;
import dev.arkondata.events.modules.ticket.service.TicketService;
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
public class TicketController {
    private final TicketService service;

    @QueryMapping
    public List<Ticket> tickets() {
        return service.findAll();
    }

    @QueryMapping
    public List<Ticket> ticketsByEvent(@Argument("id") UUID id) {
        return service.findAllByEvent(id);
    }

    @QueryMapping
    public Ticket ticket(UUID id) {
        return service.findById(id);
    }

    @MutationMapping
    public Ticket sellTicket(@Argument("id") UUID id) {
        return service.sell(id);
    }

    @MutationMapping
    public Boolean redeemTicket(@Argument("id") UUID id) {
        return service.redeem(id);
    }
}
