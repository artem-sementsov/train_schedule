package ru.netcracker.train_schedule.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalTime;

/**
 * Created by aleks on 26.04.2016.
 */
public class RouteListStation {

    @JsonProperty("number")
    public String number;

    @JsonProperty("station")
    public String station;

    @JsonProperty("deltetime")
    public LocalTime deltetime;

    @JsonProperty("price")
    public Float price;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public LocalTime getDeltetime() {
        return deltetime;
    }

    public void setDeltetime(LocalTime deltetime) {
        this.deltetime = deltetime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RouteListStation{" +
            "number='" + number + '\'' +
            ", station='" + station + '\'' +
            ", deltetime=" + deltetime +
            ", price=" + price +
            '}';
    }
}
