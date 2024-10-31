package dev.arkondata.events.modules.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.arkondata.events.modules.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate start;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate end;
    @Column(nullable = false, columnDefinition = "INTEGER")
    private Integer maxTickets;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(columnDefinition = "BOOL")
    private Boolean status;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    @ToString.Exclude
    private Set<Ticket> tickets;

    public Event(UUID id, String name, LocalDate start, LocalDate end, Integer maxTickets, LocalDateTime createdAt, Boolean status) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.maxTickets = maxTickets;
        this.createdAt = createdAt;
        this.status = status == null || status;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Event event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
