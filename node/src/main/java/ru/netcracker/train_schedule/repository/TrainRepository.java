package ru.netcracker.train_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.train_schedule.domain.Train;

/**
 * Created by Artem on 4/22/2016.
 */
public interface TrainRepository extends JpaRepository<Train, Long> {
}
