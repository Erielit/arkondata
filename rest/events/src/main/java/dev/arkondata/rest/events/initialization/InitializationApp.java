package dev.arkondata.rest.events.initialization;


import dev.arkondata.rest.events.modules.event.entity.Event;
import dev.arkondata.rest.events.modules.event.entity.IEventRepository;
import dev.arkondata.rest.events.modules.ticket.entity.ITicketRepository;
import dev.arkondata.rest.events.modules.ticket.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Configuration
@Transactional
@RequiredArgsConstructor
public class InitializationApp implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationApp.class);
    private final IEventRepository iEventRepository;
    private final ITicketRepository iTicketRepository;

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public void run(String... args) throws Exception {
        UUID id = UUID.fromString("0e93b55c-2513-4deb-ada1-4c66fe7e44d4");
        Event event = new Event();
        event.setId(id);
        event.setStatus(true);
        event.setName("Event One");
        event.setStart(LocalDate.parse("2024-10-30"));
        event.setEnd(LocalDate.parse("2024-11-30"));
        event.setCreatedAt(LocalDateTime.now());
        event.setMaxTickets(100);
        event = getOrSaveEvent(event);
        // The flag value of 0 is used to indicate that the ticket has been registered once.
        if (iTicketRepository.countByEvent_Id(event.getId()) == 0) {
            Ticket ticket = new Ticket();
            ticket.setId(event.getId());
            ticket.setStatus(true);
            ticket.setIsRedeem(false);
            ticket.setCreatedAt(LocalDateTime.now());
            ticket.setEvent(event);
            ticket = getOrSaveTicket(ticket);
            LOGGER.info("Created initial data event {} ticket {}", event.getId(), ticket.getId());
        }
    }

    public Event getOrSaveEvent(Event event) {
        Optional<Event> foundEvent = iEventRepository.findByNameAndStartAndEnd(event.getName(), event.getStart(), event.getEnd());
        return foundEvent.orElseGet(() -> iEventRepository.saveAndFlush(event));
    }

    public Ticket getOrSaveTicket(Ticket ticket) {
        return iTicketRepository.findById(ticket.getEvent().getId()).orElseGet(() -> iTicketRepository.saveAndFlush(ticket));
    }
}
