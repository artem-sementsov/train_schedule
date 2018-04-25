package ru.netcracker.train_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.train_schedule.domain.Station;

/**
 * Created by aleks on 20.04.2016.
 */
public interface StationRepository extends JpaRepository<Station, Long> {
}
