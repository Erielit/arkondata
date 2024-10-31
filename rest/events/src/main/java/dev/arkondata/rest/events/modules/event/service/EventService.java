package dev.arkondata.rest.events.modules.event.service;

import dev.arkondata.rest.events.core.ApiResponse;
import dev.arkondata.rest.events.core.MessageError;
import dev.arkondata.rest.events.modules.event.entity.Event;
import dev.arkondata.rest.events.modules.event.entity.IEventRepository;
import dev.arkondata.rest.events.modules.ticket.entity.ITicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final IEventRepository repository;
    private final ITicketRepository iTicketRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(ApiResponse.ofOk(repository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(UUID id) {
        return new ResponseEntity<>(ApiResponse.ofOk(repository.findById(id).orElse(null)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> save(Event event) {
        if (event.getStart().isBefore(LocalDate.now()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.START_EVENT_MUST_NOT_BE_BEFORE_TODAY.name()), HttpStatus.BAD_REQUEST);
        if (event.getEnd().isBefore(event.getStart()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT.name()), HttpStatus.BAD_REQUEST);
        if (repository.existsByStartAndEndAndName(event.getStart(), event.getEnd(), event.getName()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.DUPLICATED_EVENT.name()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ApiResponse.ofOk(repository.saveAndFlush(event)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class, RuntimeException.class})
    public ResponseEntity<ApiResponse> update(Event event) {
        if (event.getStart().isBefore(LocalDate.now()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.START_EVENT_MUST_NOT_BE_BEFORE_TODAY.name()), HttpStatus.BAD_REQUEST);
        if (event.getEnd().isBefore(event.getStart()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.END_EVENT_MUST_NOT_BE_BEFORE_START_EVENT.name()), HttpStatus.BAD_REQUEST);
        if (iTicketRepository.countByEvent_Id(event.getId()) > event.getMaxTickets())
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.EVENT_UPDATE_NOT_ALLOWED_MAX_TICKETS_SOLD.name()), HttpStatus.BAD_REQUEST);
        if (!repository.existsById(event.getId()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.EVENT_NOT_FOUND.name()), HttpStatus.BAD_REQUEST);
        if (repository.existsByStartAndEndAndIdNot(event.getStart(), event.getEnd(), event.getId()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.DUPLICATED_EVENT.name()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ApiResponse.ofOk(repository.saveAndFlush(event)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> deleteEvent(UUID id) {
        Optional<Event> foundEvent = repository.findById(id);
        if (foundEvent.isEmpty())
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.EVENT_NOT_FOUND.name()), HttpStatus.BAD_REQUEST);
        if (iTicketRepository.countByEvent_Id(id) > 0)
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.EVENT_DELETE_NOT_ALLOWED_TICKETS_ALREADY_SOLD.name()), HttpStatus.BAD_REQUEST);
        if (foundEvent.get().getEnd().isBefore(LocalDate.now()))
            return new ResponseEntity<>(ApiResponse.ofError(HttpStatus.BAD_REQUEST, MessageError.DELETE_EVENT_NOT_ALLOWED_NOT_FINISH_YET.name()), HttpStatus.BAD_REQUEST);
        Event event = foundEvent.get();
        event.setStatus(!event.getStatus());
        repository.saveAndFlush(event);
        return new ResponseEntity<>(ApiResponse.ofOk(true), HttpStatus.OK);
    }
}
