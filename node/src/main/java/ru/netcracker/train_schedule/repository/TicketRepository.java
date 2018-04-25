package ru.netcracker.train_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.train_schedule.domain.Ticket;

import java.util.List;

/**
 * Created by aleks on 21.04.2016.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByUserId(Long userId);

}
