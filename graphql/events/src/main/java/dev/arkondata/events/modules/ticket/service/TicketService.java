package dev.arkondata.events.modules.ticket.service;

import dev.arkondata.events.core.MessageError;
import dev.arkondata.events.exceptions.CustomException;
import dev.arkondata.events.exceptions.CustomGlobalExceptionResolver;
import dev.arkondata.events.modules.event.entity.Event;
import dev.arkondata.events.modules.event.entity.IEventRepository;
import dev.arkondata.events.modules.ticket.entity.ITicketRepository;
import dev.arkondata.events.modules.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
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
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ticket> findAllByEvent(UUID id) {
        return repository.findAllByEventId(id);
    }

    @Transactional(readOnly = true)
    public Ticket findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomException(MessageError.TICKET_NOT_FOUND.name()));
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public Ticket sell(UUID id) {
        Optional<Event> foundEvent = iEventRepository.findById(id);
        if (foundEvent.isEmpty()) throw new CustomException(MessageError.TICKET_NOT_FOUND.name());
        Event event = foundEvent.get();
        if (repository.countByEvent_Id(id) < event.getMaxTickets())
            return repository.saveAndFlush(new Ticket(event));
        throw new CustomException(MessageError.MAX_TICKETS_LIMIT_REACHED.name());
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public Boolean redeem(UUID id) {
        Optional<Ticket> foundTicket = repository.findById(id);
        if (foundTicket.isEmpty())
            throw new CustomException(MessageError.TICKET_NOT_FOUND.name());
        Ticket ticket = foundTicket.get();
        ticket.setIsRedeem(true);
        repository.saveAndFlush(ticket);
        return true;
    }
}
