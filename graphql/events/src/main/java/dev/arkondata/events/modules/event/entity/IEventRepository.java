package dev.arkondata.events.modules.event.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID> {

    boolean existsByStartAndEndAndName(LocalDate start, LocalDate end, String name);

    boolean existsByStartAndEndAndIdNot(LocalDate start, LocalDate end, UUID id);

    Optional<Event> findByNameAndStartAndEnd(String name, LocalDate start, LocalDate end);
}
