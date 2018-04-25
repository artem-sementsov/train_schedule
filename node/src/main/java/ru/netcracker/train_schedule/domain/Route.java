package ru.netcracker.train_schedule.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Route
 */

@Entity
@Table(name = "station")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedStoredProcedureQuery(name = "getRoute", procedureName = "get", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_station_from_id", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_station_to_id", type = Integer.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_date", type = Long.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "_request_id", type = Integer.class)})
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    private Long id;

    @Column(name = "list_station")
    @Convert(converter = RouteConverter.class)
    private RouteListStation listStation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RouteListStation getListStation() {
        return listStation;
    }

    public void setListStation(RouteListStation listStation) {
        this.listStation = listStation;
    }

    @Override
    public String toString() {
        return "Route{" +
            "id=" + id +
            ", listStation=" + listStation +
            '}';
    }
}
