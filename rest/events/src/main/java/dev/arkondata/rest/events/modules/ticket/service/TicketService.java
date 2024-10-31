package dev.arkondata.rest.events.modules.ticket.service;

import dev.arkondata.rest.events.core.ApiResponse;
import dev.arkondata.rest.events.core.MessageError;
import dev.arkondata.rest.events.modules.event.entity.Event;
import dev.arkondata.rest.events.modules.event.entity.IEventRepository;
import dev.arkondata.rest.events.modules.ticket.entity.ITicketRepository;
import dev.arkondata.rest.events.modules.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);
    private final ITicketRepository repository;
    private final IEventRepository iEventRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(ApiResponse.ofOk(repository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAllByEvent(UUID id) {
        return new ResponseEntity<>(ApiResponse.ofOk(repository.findAllByEventId(id)), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(UUID id) {
        return new ResponseEntity<>(ApiResponse.ofOk(repository.findById(id).orElse(null)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> sell(UUID id) {
        Optional<Event> foundEvent = iEventRepository.findById(id);
        if (foundEvent.isEmpty())
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.TICKET_NOT_FOUND.name()), HttpStatus.BAD_REQUEST);
        Event event = foundEvent.get();
        if (repository.countByEvent_Id(id) < event.getMaxTickets())
            return new ResponseEntity<>(ApiResponse.ofOk(repository.saveAndFlush(new Ticket(event))), HttpStatus.OK);
        return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.MAX_TICKETS_LIMIT_REACHED.name()), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> redeem(UUID id) {
        Optional<Ticket> foundTicket = repository.findById(id);
        if (foundTicket.isEmpty())
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.TICKET_NOT_FOUND.name()), HttpStatus.BAD_REQUEST);
        Ticket ticket = foundTicket.get();
        ticket.setIsRedeem(true);
        repository.saveAndFlush(ticket);
        return new ResponseEntity<>(ApiResponse.ofOk(true), HttpStatus.BAD_REQUEST);
    }
}
