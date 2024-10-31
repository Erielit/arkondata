package dev.arkondata.events.modules.event.service;

import dev.arkondata.events.core.MessageError;
import dev.arkondata.events.modules.event.entity.Event;
import dev.arkondata.events.modules.event.entity.IEventRepository;
import dev.arkondata.events.modules.ticket.entity.ITicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final IEventRepository repository;
    private final ITicketRepository iTicketRepository;

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Event findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(MessageError.EVENT_NOT_FOUND.name()));
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public Event save(Event event) {
        if (event.getStart().isBefore(LocalDate.now()))
            throw new RuntimeException(MessageError.START_EVENT_MUST_NOT_BE_BEFORE_TODAY.name());
        if (event.getStart().isAfter(event.getEnd()))
            throw new RuntimeException(MessageError.END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT.name());
        if (repository.existsByStartAndEndAndName(event.getStart(), event.getEnd(), event.getName()))
            throw new RuntimeException(MessageError.DUPLICATED_EVENT.name());
        return repository.saveAndFlush(event);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class, RuntimeException.class})
    public Event update(Event event) {
        if (iTicketRepository.countByEvent_Id(event.getId()) > event.getMaxTickets())
            throw new RuntimeException(MessageError.EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD.name());
        if (!repository.existsById(event.getId()))
            throw new RuntimeException(MessageError.EVENT_NOT_FOUND.name());
        if (repository.existsByStartAndEndAndIdNot(event.getStart(), event.getEnd(), event.getId()))
            throw new RuntimeException(MessageError.DUPLICATED_EVENT.name());
        return repository.saveAndFlush(event);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public Boolean deleteEvent(UUID id) {
        Optional<Event> foundEvent = repository.findById(id);
        if (foundEvent.isEmpty())
            throw new RuntimeException(MessageError.EVENT_NOT_FOUND.name());
        if (iTicketRepository.countByEvent_Id(id) > 0)
            throw new RuntimeException(MessageError.EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD.name());
        if (foundEvent.get().getEnd().isBefore(LocalDate.now()))
            throw new RuntimeException(MessageError.DELETE_EVENT_NOT_ALLOWED_NOT_FINISH_YET.name());
        Event event = foundEvent.get();
        event.setStatus(!event.getStatus());
        repository.saveAndFlush(event);
        return true;
    }
}
