package dto;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/**
 * FlightDTO
 */
public class FlightDTO {

    private DateTime departureTime;

    private int numberSeatsOccupied;

    private LocalTime delay;

    private Long stationToId;

    private Long stationFromId;

    private Long routeId;

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

    public Long getStationToId() {
        return stationToId;
    }

    public void setStationToId(Long stationToId) {
        this.stationToId = stationToId;
    }

    public Long getStationFromId() {
        return stationFromId;
    }

    public void setStationFromId(Long stationFromId) {
        this.stationFromId = stationFromId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public FlightDTO() {
    }

    public FlightDTO(DateTime departureTime, int numberSeatsOccupied, LocalTime delay, Long stationToId,
                     Long stationFromId, Long routeId) {
        this.departureTime = departureTime;
        this.numberSeatsOccupied = numberSeatsOccupied;
        this.delay = delay;
        this.stationToId = stationToId;
        this.stationFromId = stationFromId;
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "departureTime=" + departureTime +
                ", numberSeatsOccupied=" + numberSeatsOccupied +
                ", delay=" + delay +
                ", stationToId=" + stationToId +
                ", stationFromId=" + stationFromId +
                ", routeId=" + routeId +
                '}';
    }
}
