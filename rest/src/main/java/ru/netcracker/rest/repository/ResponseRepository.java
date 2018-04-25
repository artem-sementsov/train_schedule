package ru.netcracker.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.rest.domain.Response;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by aleks on 20.04.2016.
 */
public interface ResponseRepository extends JpaRepository<Response, Long> {

    Optional<Response> findOneById(UUID id);

}
