package ru.netcracker.train_schedule.repository;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ru.netcracker.train_schedule.domain.Route;

import java.util.UUID;

/**
 * Created by aleks on 20.04.2016.
 */
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(nativeQuery = true,
    value = "select get(:_station_from_id, :_station_to_id,:_date,:_request_id)")
    void getRoute(@Param("_station_from_id") Integer _station_from_id,
                  @Param("_station_to_id") Integer _station_to_id,
                  @Param("_date") Long _date,
                  @Param("_request_id") String _request_id);
}
