package ru.netcracker.train_schedule.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  Flight (конкретный маршрут)
 */

@Entity
@Table(name = "flight")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "departure_time")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime departureTime;

    @Column(name = "number_seats_occupied")
    private int numberSeatsOccupied;

    @Column(name = "delay")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime delay;

    @ManyToOne
    @JoinColumn(name = "station_to_id", nullable = false)
    private Station stationTo;

    @ManyToOne
    @JoinColumn(name = "station_from_id", nullable = false)
    private Station stationFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getNumberSeatsOccupied() {
        return numberSeatsOccupied;
    }

    public void setNumberSeatsOccupied(int numberSeatsOccupied) {
        this.numberSeatsOccupied = numberSeatsOccupied;
    }

    public LocalTime getDelay() {
        return delay;
    }

    public void setDelay(LocalTime delay) {
        this.delay = delay;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Flight{" +
            "id=" + id +
            ", departureTime=" + departureTime +
            ", numberSeatsOccupied=" + numberSeatsOccupied +
            ", delay=" + delay +
            ", stationTo=" + stationTo +
            ", stationFrom=" + stationFrom +
            '}';
    }
}
