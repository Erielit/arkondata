package dev.arkondata.rest.events.modules.ticket.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findAllByEventId(UUID id);

    long countByEvent_Id(UUID id);
}
