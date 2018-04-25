package ru.netcracker.train_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.train_schedule.domain.Flight;

/**
 * Created by aleks on 21.04.2016.
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
