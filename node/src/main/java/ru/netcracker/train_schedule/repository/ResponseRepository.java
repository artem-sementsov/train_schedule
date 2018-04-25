package ru.netcracker.train_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.train_schedule.domain.Response;

/**
 * Created by aleks on 20.04.2016.
 */
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
